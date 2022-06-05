
public class Vegetable extends Crop implements Comparable {

    private String cultivatedRegion;
    private Object croopKeeper;
    private int price;

    public Vegetable(String name, int weight, String cultivatedSeason, String cultivatedRegion, int price, Object croopKeeper) {
        super(name, weight, cultivatedSeason);
        this.cultivatedRegion = cultivatedRegion;
        this.croopKeeper = croopKeeper;
        this.price = price;

    }
    
    @Override
    public int totalPrice() {
        return weight*price;
    }

    @Override
    public String toString() {
        return name + ", vegetable, " + weight + ", " + cultivatedRegion;
    }

    @Override
    String consumeIt() {
        return "vegetables are cooked";
    }

    @Override
    public void addIfSame(Object o) {
        if (compareTo(o) == 0) {
            Vegetable v = (Vegetable) o;
            this.weight = this.weight + v.weight;
            o = this;
        }
    }

    @Override
    public void storeIt() throws Exception{
        throw new CanNotBeStoredException();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Vegetable) {
            Vegetable v = (Vegetable) o;

            if (v.name.equals(this.name)) {
                return 0;
            } else {
                return (this.weight - v.weight);
            }

        } else {
            throw new UnsupportedOperationException("Vegetable can only be compared to vegetable!");
        }
    }

    //GETTERS
    public String getCultivatedRegion() {
        return cultivatedRegion;
    }

    public Object getCroopKeeper() {
        return croopKeeper;
    }

    public String getName() {
        return name;
    }

    public String getCultivatedSeason() {
        return cultivatedSeason;
    }

    public int getWeight() {
        return weight;
    }

}
