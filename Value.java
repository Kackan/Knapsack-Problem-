public class Value {
    private int id;
    private int value;
    private int weight;

    public Value(int id, int value, int weight) {
        this.id = id;
        this.value = value;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", value=" + value +
                ", weight=" + weight +
                '}';
    }
}
