
import java.io.Serializable;
import java.util.ArrayList;

public class Supplier implements CropKeeper,Serializable {

    private ArrayList<Crop> cropList = new ArrayList<>();
    private String name;
    private int budget;
    private int ID = 1000;
    private static int counter = 0;

    public Supplier(String name, int budget) {
        this.name = name;
        this.budget = budget;
        ID += counter;
        counter++;
    }

    public void buyCrop(Crop c, Store s) throws Exception {
        if (c instanceof Fruit) {
            Fruit f = (Fruit) c;
            if (s.getFruitList().contains(f)) {
                if (budget >= c.totalPrice()) {
                    cropList.add(c);
                    budget = budget - f.totalPrice();
                    s.exportCrop(f);
                }else {
                    throw new SupplierHasNotEnoughMoneyException();
                }
            } else {
                throw new FruitNotAvailableException();
            }
        }
    }

    public void sellCrop(Crop c, Store s) throws Exception {
        if (c instanceof Fruit) {
            Fruit f = (Fruit) c;
            if (cropList.contains(c) && s.canBeStored(f)) {
                cropList.remove(f);
                budget = budget + f.totalPrice();
                s.importCrop(f);
            } else {
                throw new FruitNotFoundException();
            }
        }
    }

    @Override
    public void howToStore() {
        System.out.println("-fruits in big refrigerators");
        System.out.println("-vegetables in the field booths");
    }

    public void info() {
        System.out.println("Name : " + name + " - ID : " + ID);
        howToStore();
        for (int i = 0; i < cropList.size(); i++) {
            System.out.println("*" + cropList.get(i).toString() + " - " + cropList.get(i).consumeIt());
        }
    }

    public String toString() {
        return "(Supplier name : " + name + " with id : " + ID + ")";
    }

    //GETTERS
    public ArrayList<Crop> getCropList() {
        return cropList;
    }

    public String getName() {
        return name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int newBudget) {
        this.budget = newBudget;
    }

    public int getID() {
        return ID;
    }

}
