public class Juice {
    private Fruit fruit;
    private int ounces;
    public Juice(Fruit fruit, int ounces) {
        if(ounces <= 0) throw new IllegalArgumentException("ounces must be positive");
        this.fruit = fruit;
        this.ounces = ounces;
    }
    @Override
    public String toString() {
        return "" + ounces + " ounce " + fruit + " juice";
    }
}
