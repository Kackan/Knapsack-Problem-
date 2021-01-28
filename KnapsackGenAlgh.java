import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KnapsackGenAlgh {
    public static Table table = new Table();
    public static ArrayList<Chromosom>chromosoms=new ArrayList<>();
    public static int MAX_FIT_FUNCTION = 0;
    public static ArrayList<Chromosom> MAX_FIT_FUNCTION_CHROMOSOMES = null;
    public static int COUNTER = 10;
    public static int ITERATION_COUNTER = 0;
    public static void main(String[] args) {
        table.addValue(new Value(0,12,5));
        table.addValue(new Value(1,4,6));
        table.addValue(new Value(2,6,13));
        table.addValue(new Value(3,6,14));
        table.addValue(new Value(4,12,7));
        table.addValue(new Value(5,5,18));
        table.addValue(new Value(6,16,23));
        table.addValue(new Value(7,12,1));
        table.addValue(new Value(8,12,15));
        table.addValue(new Value(9,3,16));


        int maxWeight=79;
    double pk=0.8;
    double pm=0.2;

        int j=0;
        while(j<6){
            int r=(int) (Math.random()*1024);
            String a= Integer.toBinaryString(r);
            String b= compensate(a);
            chromosoms.add(new Chromosom("Ch"+j, b));
            j++;
        }

      chromosoms.forEach(c -> calculationOfFitFitness(c,maxWeight));

        System.out.println("Populacja początkowa");
        percentValueOfFitFunction(chromosoms);
        chromosoms.forEach(c-> System.out.println(c));


        MAX_FIT_FUNCTION_CHROMOSOMES=chromosoms;
        MAX_FIT_FUNCTION=calculationOfMaxFitFunction(chromosoms);


        System.out.println("Ostateczna populacja");

        ArrayList<Chromosom> init = chromosoms;

        while(COUNTER > 0 && ITERATION_COUNTER < 100000){
            init=start(init,maxWeight,pk,pm);

            ITERATION_COUNTER ++;

            if(MAX_FIT_FUNCTION < calculationOfMaxFitFunction(init)){
                MAX_FIT_FUNCTION=calculationOfMaxFitFunction(init);
                MAX_FIT_FUNCTION_CHROMOSOMES=init;
                COUNTER = 6;
            }else if (MAX_FIT_FUNCTION == calculationOfMaxFitFunction(init))
            {
                COUNTER--;
            }


        }



        System.out.println("ILOŚĆ OPERACJI: "+ITERATION_COUNTER);
        System.out.println("Maksymalna wartość funkcji przystosowania: "+MAX_FIT_FUNCTION);
        System.out.println("Chromosomy maksymalnej funkcji przystosowania:");
        MAX_FIT_FUNCTION_CHROMOSOMES.forEach(f -> System.out.println(f));

    }

    public static int calculationOfMaxFitFunction(ArrayList<Chromosom>chromosoms){
        int sum = 0;
        for(Chromosom chr : chromosoms){
            sum+=chr.getSum();
        }
        return sum;
    }

    public static ArrayList<Chromosom>start (ArrayList<Chromosom>chromosoms, int maxWeigt, double pk, double pm){
        ArrayList<Chromosom> init = init(chromosoms, maxWeigt, pk, pm);
        return init;
    }

    public static ArrayList<Chromosom> init( ArrayList<Chromosom> chromosoms, int maxWeight, double pk, double pm){

        ArrayList<Chromosom> chromosomsAfterRoulette = rouletteOperation(chromosoms);
        chromosomsAfterRoulette.forEach(c-> calculationOfFitFitness(c,maxWeight));

        ArrayList<Chromosom> newChromListAfterMiscegenation = miscegenation(new Chromosom(chromosomsAfterRoulette.get(0)), new Chromosom(chromosomsAfterRoulette.get(1)), pk);
        ArrayList<Chromosom> miscegenation1 = miscegenation(new Chromosom(chromosomsAfterRoulette.get(2)), new Chromosom(chromosomsAfterRoulette.get(3)), pk);
        ArrayList<Chromosom> miscegenation2 = miscegenation(new Chromosom(chromosomsAfterRoulette.get(4)), new Chromosom(chromosomsAfterRoulette.get(5)), pk);
        newChromListAfterMiscegenation.addAll(miscegenation1);
        newChromListAfterMiscegenation.addAll(miscegenation2);


        ArrayList<Chromosom> mutating = mutating(newChromListAfterMiscegenation, pm);



        mutating.forEach(c -> calculationOfFitFitness(c,maxWeight));
        percentValueOfFitFunction(mutating);

        int i = 0;
        for(Chromosom chr : mutating){
            chr.setName("Ch"+i);
            i++;
        }

        return mutating;
    }

    public static String compensate(String a) {
        if(a.length()<10){
            String replace="";

            int dif=10-a.length();

            for(int i=0; i< dif; i++){
                replace+="0";
            }

            return replace.concat(a);
        }
        return a;
    }

    public static void calculationOfFitFitness(Chromosom chromosom, int maxWeight){

        int sum = 0;
        int weight=0;
        for(int i=0; i<10; i++){
            if(chromosom.getBinaryVal().charAt(i)=='1'){
                sum+=table.getValue(i).getValue();
                weight+=table.getValue(i).getWeight();
            }
        }

        if(weight <= maxWeight){
            chromosom.setWeight(weight);
            chromosom.setSum(sum);
        }else{
            Chromosom chromosom1 = negateBit(chromosom);
            calculationOfFitFitness(chromosom1, maxWeight);
        }

    }

    public static Chromosom negateBit(Chromosom chromosom){
        ArrayList<Integer>arr=new ArrayList<>();
        char[] chars = chromosom.getBinaryVal().toCharArray();
        for(int i=0; i<10; i++){
            if(chromosom.getBinaryVal().charAt(i)=='1'){
                arr.add(i);
            }
        }
        Collections.shuffle(arr);
        int n = arr.get(0);
        chars[n]='0';
        String binaryVal= String.valueOf(chars);
        chromosom.setBinaryVal(binaryVal);
        return chromosom;
    }

    public static ArrayList<Chromosom> rouletteOperation(ArrayList<Chromosom> chromosomList) {
        ArrayList<ChromosomRoulette>chromRoulette=new ArrayList<>();

        double percent=0;
        for(Chromosom chr: chromosomList){
            ChromosomRoulette chrom=new ChromosomRoulette();
            chromRoulette.add(chrom);
            chrom.setStart(percent);
            chrom.setChromosom(chr);
            percent+=chr.getPercentValueOfFitFitness();
            chrom.setEnd(percent);
        }


        double[]shotArray= new double[6];
        for(int i=0; i<chromosomList.size(); i++){
            shotArray[i]=Math.random()*100;
        }


        ArrayList<Chromosom>newChromList=new ArrayList<>();

        chromRoulette.forEach(chr->{
            for(int i=0; i<shotArray.length; i++){

;

                if(chr.getStart()<=shotArray[i] && shotArray[i]<=chr.getEnd())
                {

                    newChromList.add(chr.getChromosom());
                }
            }
        });

        return newChromList;
    }

    public static void percentValueOfFitFunction(ArrayList<Chromosom> chromosomes){
        double sumOfFitFunction = 0;
        for(Chromosom chromosome : chromosomes){
            sumOfFitFunction+=chromosome.getSum();
        }

        for(Chromosom chromosome : chromosomes){
            double sum = chromosome.getSum();
            chromosome.setPercentValueOfFitFitness((sum * 100)/sumOfFitFunction);
        }
    }

    public static ArrayList<Chromosom> miscegenation(Chromosom chromosome, Chromosom chromosome1, double pk) {
        double v = Math.random() * 1;



        if(v<pk){
            int lokus =(int) (Math.random()*8)+1;


            String alfa=chromosome.getBinaryVal();
            char[] chars = alfa.toCharArray();
            String beta=chromosome1.getBinaryVal();
            char[] chars1 = beta.toCharArray();


            for(int i= lokus; i<alfa.length(); i++)
            {
                char a=chars[i];
                char b=chars1[i];
                chars[i]=b;
                chars1[i]=a;
            }

            chromosome.setBinaryVal(String.valueOf(chars));
            chromosome1.setBinaryVal(String.valueOf(chars1));

            ArrayList<Chromosom>newChromosomeList=new ArrayList<>();
            newChromosomeList.add(chromosome);
            newChromosomeList.add(chromosome1);
            return newChromosomeList;

        }else{
            ArrayList<Chromosom>oldChromosomeList=new ArrayList<>();
            oldChromosomeList.add(chromosome);
            oldChromosomeList.add(chromosome1);

            return oldChromosomeList;
        }
    }

    public static ArrayList<Chromosom> mutating(ArrayList<Chromosom> chromosomes, double pm){
        ArrayList<Chromosom>chromosmAfterMutating=new ArrayList<>();

        chromosomes.forEach(chr -> {
            int lokus = (int) (Math.random() * (10));

            double c = Math.random()*1;
            if(c<pm) {


                char[] chars = chr.getBinaryVal().toCharArray();
                if (chars[lokus] == '1') {
                    chars[lokus] = '0';
                    chr.setBinaryVal(String.valueOf(chars));


                    chromosmAfterMutating.add(chr);
                } else {
                    chars[lokus] = '1';
                    chr.setBinaryVal(String.valueOf(chars));


                    chromosmAfterMutating.add(chr);
                }
            }else{
                chromosmAfterMutating.add(chr);
            }
        });

        return chromosmAfterMutating;
    }


}

