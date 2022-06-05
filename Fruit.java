
public class Fruit extends Crop implements Comparable {

    private String taste;
    private int price;
    private Object croopKeeper;

    public Fruit(String name, int weight, String cultivatedSeason, String taste, int price, Object croopKeeper) {
        super(name, weight, cultivatedSeason);
        this.taste = taste;
        this.price = price;
        this.croopKeeper = croopKeeper;
    }
    
    @Override
    public int totalPrice() {
        return weight*price;
    }
 
    @Override
    public String toString() {
        return name + ", fruit, " + weight + ", " + cultivatedSeason + ", " + taste + ", " + price + ", " + croopKeeper.toString();
    }

    @Override
    String consumeIt() {
        return "fruits are consumed raw";
    }

    @Override
    public void addIfSame(Object o) {
        if (compareTo(o) == 0) {
            Fruit f = (Fruit) o;
            this.weight = this.weight + f.weight;
            o = this;
        }
    }

    @Override
    public void storeIt() throws Exception{
        if (croopKeeper instanceof Store) {
            Store store = (Store) croopKeeper;
            store.importCrop(this);
        }
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Fruit) {
            Fruit f = (Fruit) o;

            if (f.name.equals(this.name)) {
                return 0;
            } else {
                return (this.weight - f.weight);
            }

        } else {
            throw new UnsupportedOperationException("Fruit can only be compared to fruit!");
        }
    }

    //GETTERS
    public String getTaste() {
        return taste;
    }

    public int getPrice() {
        return price;
    }

    public Object getCroopKeeper() {
        return croopKeeper;
    }
    
    public void setCroopKeeper(Object o) {
        this.croopKeeper = o;
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
