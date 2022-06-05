
import java.io.*;
import java.util.*;

public class TestClass {

    public static void main(String[] args) throws Exception {
        ArrayList<Supplier> supplierList = new ArrayList<>();
        ArrayList<Store> storeList = new ArrayList<>();
        ArrayList<Crop> cropList = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);

        File f_supplier = new File("Suppliers.txt");
        FileInputStream fis_supplier = new FileInputStream(f_supplier);
        ObjectInputStream ois_supplier = new ObjectInputStream(fis_supplier);
        for (int i = 0; i < fis_supplier.available(); i++) { 
            supplierList.add((Supplier) ois_supplier.readObject());
        }
        ois_supplier.close();

        File f_store = new File("Stores.txt");
        FileInputStream fis_store = new FileInputStream(f_store);
        ObjectInputStream ois_store = new ObjectInputStream(fis_store);
        for (int i = 0; i < fis_store.available(); i++) {
            storeList.add((Store) ois_store.readObject());
        }
        ois_store.close();

        File f_crop = new File("Crops.txt");
        FileInputStream fis_crop = new FileInputStream(f_crop);
        ObjectInputStream ois_crop = new ObjectInputStream(fis_crop);
        for (int i = 0; i < fis_crop.available(); i++) {
            cropList.add((Crop) ois_crop.readObject());
        }
        ois_crop.close();
        
        //meyve ayarlaması
        supplierList.get(0).getCropList().add(cropList.get(0));
        storeList.get(1).getFruitList().add((Fruit) cropList.get(1));
        supplierList.get(0).getCropList().add(cropList.get(2));
        supplierList.get(3).getCropList().add(cropList.get(3));
        supplierList.get(3).getCropList().add(cropList.get(4));
        supplierList.get(0).getCropList().add(cropList.get(5));
        supplierList.get(2).getCropList().add(cropList.get(6));
        storeList.get(0).getFruitList().add((Fruit) cropList.get(7));
        supplierList.get(2).getCropList().add(cropList.get(8));
        storeList.get(0).getFruitList().add((Fruit) cropList.get(9));     

        int choice = 0;
        while (true) {
            System.out.println("");
            System.out.println("1)Display all suppliers");
            System.out.println("2)Display all stores");
            System.out.println("3)Buy a fruit crop for a supplier");
            System.out.println("4)Sell a fruit crop of a supplier");
            System.out.println("5)Remove a fruit from a store");
            System.out.println("6)Remove a crop from a supplier");
            System.out.println("7)Add crop");
            System.out.println("8)Show remaining budget of a supplier");
            System.out.println("9)Show remaining capacity of a store");
            System.out.println("0)Quit");
            System.out.print("Your choice : ");
            choice = scan.nextInt();
            System.out.println("");

            String name1, name2;
            int supplierIndex = -1, storeIndex = -1, cropIndex = -1;
            switch (choice) {
                case 1: //DISPLAY SUPPLIERS&STORES******************************
                    displaySupplierList(supplierList);
                    break;
                case 2:
                    displayStoreList(storeList);
                    break;

                case 3: //BUY A CROP********************************************
                    //supplier name list
                    for (int i = 0; i < supplierList.size(); i++) {
                        System.out.println("Supplier name : " + supplierList.get(i).getName() + " / Budget : " + supplierList.get(i).getBudget());
                    }

                    System.out.print("Buyer(Supplier) name : ");
                    name1 = scan2.nextLine();

                    //supplier check
                    for (int i = 0; i < supplierList.size(); i++) {
                        if (name1.equals(supplierList.get(i).getName())) {
                            supplierIndex = i;
                        }
                    }

                    //available crops with store id's
                    System.out.println("");
                    for (int i = 0; i < cropList.size(); i++) {
                        if (cropList.get(i) instanceof Fruit) {
                            Fruit f = (Fruit) cropList.get(i);
                            if (f.getCroopKeeper() instanceof Store) {
                                System.out.println("*" + f.toString());
                            }
                        }
                    }

                    //buying phase
                    System.out.println("");
                    System.out.println("Enter the name of seller and name of crop in order");
                    name1 = scan2.nextLine();
                    name2 = scan2.nextLine();

                    for (int i = 0; i < storeList.size(); i++) { //store check
                        if (name1.equals(storeList.get(i).getName())) {
                            storeIndex = i;
                        }
                    }

                    for (int i = 0; i < cropList.size(); i++) { //crop check
                        if (cropList.get(i).name.equals(name2)) {
                            cropIndex = i;
                        }
                    }

                    if (supplierList.get(supplierIndex).getBudget() >= cropList.get(cropIndex).totalPrice()) {
                        supplierList.get(supplierIndex).buyCrop(cropList.get(cropIndex), storeList.get(storeIndex)); //satın alma
                        ((Fruit) cropList.get(cropIndex)).setCroopKeeper(supplierList.get(supplierIndex)); //crop un croopkeeperını güncelleme
                        System.out.println("Transaction successful!");
                        System.out.println("");
                    }

                    break;

                case 4: //SELL A CROP*******************************************
                    //supplier name list
                    for (int i = 0; i < supplierList.size(); i++) {
                        System.out.println("Supplier name : " + supplierList.get(i).getName() + " / Budget : " + supplierList.get(i).getBudget());
                    }

                    //supplier check
                    System.out.print("Seller(Supplier) name : ");
                    name1 = scan2.nextLine();

                    //supplier check
                    for (int i = 0; i < supplierList.size(); i++) {
                        if (name1.equals(supplierList.get(i).getName())) {
                            supplierIndex = i;
                        }
                    }

                    //show available fruits
                    System.out.println("");
                    for (int i = 0; i < supplierList.get(supplierIndex).getCropList().size(); i++) {
                        if (supplierList.get(supplierIndex).getCropList().get(i) instanceof Fruit) {
                            Fruit f = (Fruit) supplierList.get(supplierIndex).getCropList().get(i);
                            System.out.println("*" + f.toString());
                        }
                    }

                    //show available stores
                    System.out.println("");
                    for (int i = 0; i < storeList.size(); i++) {
                        System.out.println(storeList.get(i).toString());
                    }

                    //selling phase
                    System.out.println("");
                    System.out.println("Please enter the fruit name and store name(buyer) to in order");
                    name1 = scan2.nextLine();
                    name2 = scan2.nextLine();

                    for (int i = 0; i < cropList.size(); i++) {
                        if (name1.equals(cropList.get(i).name)) {
                            cropIndex = i;
                        }
                    }

                    for (int i = 0; i < storeList.size(); i++) {
                        if (name2.equals(storeList.get(i).getName())) {
                            storeIndex = i;
                        }
                    }

                    if (storeList.get(storeIndex).availableCapacity() >= cropList.get(cropIndex).weight) {
                        supplierList.get(supplierIndex).sellCrop(cropList.get(cropIndex), storeList.get(storeIndex)); //satma
                        ((Fruit) cropList.get(cropIndex)).setCroopKeeper(storeList.get(storeIndex)); // crop un croopkeeperını güncelleme
                        System.out.println("Transaction successful!");
                        System.out.println("");
                    } else {
                        System.out.println("Store's capacity does not enough to import this fruit!");
                    }

                    break;

                case 5: //REMOVE A FRUIT FROM A STORE***************************
                    //store name list
                    for (int i = 0; i < storeList.size(); i++) {
                        System.out.println(storeList.get(i).toString());
                    }

                    System.out.println("Enter the name of store that you want to remove its fruit : ");
                    name1 = scan2.nextLine();

                    //store check
                    for (int i = 0; i < storeList.size(); i++) {
                        if (name1.equals(storeList.get(i).getName())) {
                            storeIndex = i;
                        }
                    }

                    //show store's fruits
                    System.out.println("");
                    for (int i = 0; i < storeList.get(storeIndex).getFruitList().size(); i++) {
                        System.out.println("*" + storeList.get(storeIndex).getFruitList().get(i).toString());
                    }

                    //remove phase
                    System.out.println("");
                    System.out.println("Enter the name of fruit that you want to remove");
                    name2 = scan2.nextLine();

                    for (int i = 0; i < storeList.get(storeIndex).getFruitList().size(); i++) {
                        if (name2.equals(storeList.get(storeIndex).getFruitList().get(i).getName())) {
                            storeList.get(storeIndex).getFruitList().remove(i);
                            cropList.remove(i);
                            System.out.println("The fruit has been removed!");
                        }
                    }

                    break;

                case 6: //REMOVE A CROP FROM A SUPPLIER*************************
                    //supplier name list
                    for (int i = 0; i < supplierList.size(); i++) {
                        System.out.println(supplierList.get(i).toString());
                    }

                    System.out.println("Enter the name of supplier that you want to remove its crop");
                    name1 = scan2.nextLine();

                    //supplier check
                    for (int i = 0; i < supplierList.size(); i++) {
                        if (name1.equals(supplierList.get(i).getName())) {
                            supplierIndex = i;
                            System.out.println("The crop has been removed!");
                        }
                    }

                    //show supplier's crops
                    System.out.println("");
                    for (int i = 0; i < supplierList.get(supplierIndex).getCropList().size(); i++) {
                        System.out.println("*" + supplierList.get(supplierIndex).getCropList().get(i).toString());
                    }

                    //remove phase
                    System.out.println("Enter the name of crop");
                    name2 = scan2.nextLine();

                    for (int i = 0; i < supplierList.get(supplierIndex).getCropList().size(); i++) {
                        if (supplierList.get(supplierIndex).getCropList().get(i).name.equals(name2)) {
                            supplierList.get(supplierIndex).getCropList().remove(i);
                            cropList.remove(i);
                            System.out.println("The crop has been removed!");
                        }
                    }
                    break;

                case 7: //ADD A CROP********************************************
                    //fruit or veg?
                    char c;
                    System.out.println("Fruit or Vegetable? (<F> or <V>)");
                    c = scan.next().charAt(0);

                    if (c == 'F') { //if Fruit

                        String name, cS, taste, cropKeeper;
                        int weight, price;

                        System.out.println("Enter the followings orderly");
                        System.out.println("Name,cultivated season,taste,weight,price");
                        name = scan2.nextLine();
                        cS = scan2.nextLine();
                        taste = scan2.nextLine();
                        weight = scan2.nextInt();
                        price = scan2.nextInt();

                        System.out.println("Name of the croop keeper");
                        cropKeeper = scan3.nextLine();
                        for (int i = 0; i < storeList.size(); i++) {
                            if (storeList.get(i).getName().equals(cropKeeper)) {
                                cropIndex = i;
                                Crop newCrop = new Fruit(name, weight, cS, taste, price, storeList.get(i));
                                cropList.add(newCrop);
                                storeList.get(i).getFruitList().add((Fruit) newCrop);
                            }
                        }
                        for (int j = 0; j < supplierList.size(); j++) {
                            if (supplierList.get(j).getName().equals(cropKeeper)) {
                                cropIndex = j;
                                Crop newCrop = new Fruit(name, weight, cS, taste, price, supplierList.get(j));
                                cropList.add(newCrop);
                                supplierList.get(j).getCropList().add(newCrop);
                            }
                        }
                    } else if (c == 'V') { // if Vegetable

                        String name, cS, cR, cropKeeper;
                        int weight, price;

                        System.out.println("Enter the followings orderly");
                        System.out.println("Name,cultivated season,cultivated region,weight,price");
                        name = scan2.nextLine();
                        cS = scan2.nextLine();
                        cR = scan2.nextLine();
                        weight = scan2.nextInt();
                        price = scan2.nextInt();

                        System.out.println("Name of the croop keeper(only supplier)");
                        cropKeeper = scan3.nextLine();
                        for (int j = 0; j < supplierList.size(); j++) {
                            if (supplierList.get(j).getName().equals(cropKeeper)) {
                                cropIndex = j;
                                Crop newCrop = new Vegetable(name, weight, cS, cR, price, supplierList.get(j));
                                cropList.add(newCrop);
                                supplierList.get(j).getCropList().add(newCrop);
                            }
                        }

                    } else {
                        System.out.println("Unvalid choice!");
                    }
                    break;

                case 8: //DISPLAY SUPPLIER'S BUDGETS****************************
                    for (int i = 0; i < supplierList.size(); i++) {
                        System.out.println(supplierList.get(i).getName() + "'s budget : " + supplierList.get(i).getBudget());
                    }
                    break;

                case 9: //DISPLAY STORE'S AVAILABLE CAPACITY********************
                    for (int i = 0; i < storeList.size(); i++) {
                        System.out.println(storeList.get(i).getName() + "'s remaining capacity : " + storeList.get(i).availableCapacity());
                    }
                    break;

                case 0: //QUIT**************************************************
                    System.out.println("Shutting down...");
                    return;

                default:
                    System.out.println("Enter a valid number!");
                    break;
            }
        }

    }

    public static void displayStoreList(ArrayList<Store> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).info();
            System.out.println("");
        }
    }

    public static void displaySupplierList(ArrayList<Supplier> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).info();
            System.out.println("");
        }
    }
}
