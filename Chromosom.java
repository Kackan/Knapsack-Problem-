public class Chromosom {

    private String name;
    private String binaryVal;
    private double percentValueOfFitFitness;
    private int sum;
    private int weight;

    public Chromosom() {
    }

    public Chromosom(Chromosom chromosom){
        this.name=chromosom.name;
        this.binaryVal=chromosom.binaryVal;
    }

    public Chromosom(String name, String binaryVal) {
        this.name = name;
        this.binaryVal=binaryVal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBinaryVal() {
        return binaryVal;
    }

    public void setBinaryVal(String binaryVal) {
        this.binaryVal = binaryVal;
    }

    public double getPercentValueOfFitFitness() {
        return percentValueOfFitFitness;
    }

    public void setPercentValueOfFitFitness(double percentValueOfFitFitness) {
        this.percentValueOfFitFitness = percentValueOfFitFitness;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Chromosom{" +
                "name='" + name + '\'' +
                ", binaryVal='" + binaryVal + '\'' +
                ", percentValueOfFitFitness=" + percentValueOfFitFitness +
                ", sum=" + sum +
                ", weight=" + weight +
                '}';
    }
}
