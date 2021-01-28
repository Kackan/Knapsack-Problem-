import java.util.ArrayList;

public class Table {

    public Table() {
    }

    ArrayList<Value> values = new ArrayList<>();

    public void addValue(Value value){
        values.add(value);
    }

    public Value getValue(int i)
    {
        return values.stream().filter(v -> v.getId()==i).findFirst().get();
    }

    public ArrayList<Value> getValues() {
        return values;
    }

    public void setValues(ArrayList<Value> values) {
        this.values = values;
    }
}
