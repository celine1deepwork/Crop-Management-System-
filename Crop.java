
import java.io.Serializable;


public abstract class Crop implements Serializable{
    protected String name;
    protected String cultivatedSeason;
    protected int weight;
    
    public Crop(String name, int weight, String cultivatedSeason) {
        this.name = name;
        this.cultivatedSeason = cultivatedSeason;
        this.weight = weight;
    }
    
    public abstract String toString();
    abstract String consumeIt();
    abstract void addIfSame(Object o);
    abstract void storeIt() throws Exception;
    abstract int totalPrice();
    
}
