public class ChromosomRoulette {
    private double start;
    private double end;
    private Chromosom chromosom;

    public ChromosomRoulette(double start, double end, Chromosom chromosom) {
        this.start = start;
        this.end = end;
        this.chromosom = chromosom;
    }


    public ChromosomRoulette() {
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public Chromosom getChromosom() {
        return chromosom;
    }

    public void setChromosom(Chromosom chromosom) {
        this.chromosom = chromosom;
    }

    @Override
    public String toString() {
        return "ChromosomRoulette{" +
                "start=" + start +
                ", end=" + end +
                ", chromosom=" + chromosom +
                '}';
    }
}





