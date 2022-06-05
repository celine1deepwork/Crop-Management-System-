
import java.io.Serializable;
import java.util.ArrayList;

public class Store implements CropKeeper,Serializable {

    private ArrayList<Fruit> fruitList = new ArrayList<>();
    private String name;
    private int maxCapacityArea;
    private int usedCapacityArea;
    private int KGperSquareMeter = 10;
    private int ID = 5000;
    private static int counter = 0;

    public Store(String name, int maxCapacityArea, int usedCapacityArea, int KGperSquareMeter) {
        this.name = name;
        this.maxCapacityArea = maxCapacityArea;
        this.usedCapacityArea = usedCapacityArea;
        this.KGperSquareMeter = KGperSquareMeter;
        ID += counter;
        counter++;
    }

    public Store(String name, int maxCapacityArea, int KGperSquareMeter) {
        this.name = name;
        this.maxCapacityArea = maxCapacityArea;
        this.KGperSquareMeter = KGperSquareMeter;
        this.usedCapacityArea = 0;
        ID += counter;
        counter++;
    }

    public int availableCapacity() {
        return maxCapacityArea - usedCapacityArea;
    }

    public boolean canBeStored(Fruit f) {
        if (availableCapacity() >= f.weight) {
            return true;
        }
        return false;
    }

    public void importCrop(Fruit f) throws Exception{
        if (availableCapacity() >= f.weight) {
            fruitList.add(f);
            usedCapacityArea = usedCapacityArea + f.weight;
        } else {
            throw new CanNotBeStoredException();
        }
    }

    public void exportCrop(Fruit f) throws Exception {
        if (fruitList.contains(f)) {
            fruitList.remove(f);
            usedCapacityArea = usedCapacityArea - f.weight;
        } else {
            throw new FruitNotFoundException();
        }
    }

    @Override
    public void howToStore() {
        System.out.println("-fruits in large refrigerated cooler rooms");
        System.out.println("-vegetables in sheds, not listed");
    }
    
    public void info() {
        System.out.println("Name : " + name + " - ID : " + ID);
        howToStore();
        for (int i = 0; i < fruitList.size(); i++) {
            System.out.println("*" + fruitList.get(i).toString() + " - " + fruitList.get(i).consumeIt());
        }
    }
    
    public String toString() {
        return "(Store name : " + name + " - id : " + ID + " - available capacity : " + availableCapacity() +")";
    }

    //GETTERS
    public ArrayList<Fruit> getFruitList() {
        return fruitList;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacityArea() {
        return maxCapacityArea;
    }

    public int getUsedCapacityArea() {
        return usedCapacityArea;
    }

    public int getKGperSquareMeter() {
        return KGperSquareMeter;
    }

    public int getID() {
        return ID;
    }

}
