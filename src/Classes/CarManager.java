package Classes;

import Com.Brand;
import Com.BrandList;
import Com.CarList;
import Com.Menu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.options.Options;

public class CarManager {

    public static void main(String[] args) throws Exception {

        ArrayList<String> options = new ArrayList<String>();
        options.add("List all brands");
        options.add("Add a new brand");
        options.add("Search a brand based on its ID");
        options.add("Update a brand");
        options.add("Save brands to the file, named brands.txt");
        options.add("List all cars in ascending order of brand names");
        options.add("List cars based on a part of an input brand name");
        options.add("Add a car");
        options.add("Remove a car based on its ID");
        options.add("Update a car based on its ID");
        options.add("Save cars to file, named cars.txt");
        options.add("Exit");
        int option;

// loading brand data       
        BrandList brandList = new BrandList();
        String pathBrand = "F:\\Final_Project\\src\\CarPrj\\File brands.txt";
        
        try {
            if (brandList.loadFromFile(pathBrand)) {
                System.out.println("Load Brand data successfully");
            }
        } catch (Exception e) {
            System.out.println("Load failed");
        }
// loading car data

        CarList carList = new CarList(brandList);
        String pathCar = "F:\\Final_Project\\src\\CarPrj\\File cars.txt";
        
        try {
            if (carList.loadFromFile(pathCar)) {
                System.out.println("Load Car data successfully");
                
            }
        } catch (Exception e) {
            System.out.println("Load failed");
        }
        do {
            System.out.println("************************* Car manager program **************************");
            option = Menu.int_getChoice(options);
            switch (option) {
                case 1:
                    brandList.listBrand();
                    break;
                case 2:
                    Brand newBrand = new Brand();
                    brandList.addBrand(newBrand);
                    break;
                case 3:

                    Scanner box = new Scanner(System.in);
                    System.out.println("Search brand's ID: ");
                    String idSearch = box.nextLine();
                    int indexcheck = brandList.searchID(idSearch);
                    if (indexcheck != -1) {
                        System.out.println("This ID:" + idSearch + " is located at index " + (indexcheck+1));
                    } else {
                        System.out.println("This ID:" + idSearch + " is not found");
                    }

                    break;
                case 4:
                    brandList.updateBrand();
                    break;

                case 5:

                    brandList.saveToFile("F:\\Final_Project\\src\\brands.txt");

                    break;
                case 6:
                    System.out.println("Listing in ascending order of brand name: ");
                    carList.listAscendingBrands();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    carList.removeCar();
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    carList.saveToFile("F:\\Final_Project\\src\\cars.txt");
                    break;
                case 12:
                    System.out.println("Good bye!!!");
                    break;
                default:
                    System.out.println("Please enter option in range 1-12");
            }

        } while (option != 12 || option < 0 || option > 12 );

    }

}
