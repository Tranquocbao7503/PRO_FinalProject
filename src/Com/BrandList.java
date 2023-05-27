package Com;

import Com.Brand;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.print.Printer;

public class BrandList extends ArrayList<Brand> {

    public BrandList() {
    }

    // dọc dữ liệu từ file brandList
    public boolean loadFromFile(String filename) {

        File f = new File(filename);

        if (f.isFile()) {
            try {
                FileReader fileReader = new FileReader(f);
                BufferedReader reader = new BufferedReader(fileReader);
                String line = reader.readLine();

                while (line != null) {
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");
                    String id = tokenizer.nextToken().trim();
                    String brandName = tokenizer.nextToken().trim();
                    String twoLastAttribute = tokenizer.nextToken().trim();
                    StringTokenizer tokenizer1 = new StringTokenizer(twoLastAttribute, ":");
                    String soundName = tokenizer1.nextToken().trim();

                    double price = Double.parseDouble(tokenizer1.nextToken().trim());
                    Brand brand = new Brand(id, brandName, soundName, price);

                    this.add(brand);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;

        }

    }

    // tìm kiếm brand theo chuỗi
    public int searchID(String brandID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equalsIgnoreCase(brandID.trim())) {

                return i;
            }

        }
        return -1;
    }

    public boolean saveToFile(String str) {

        File f = new File(str);
        boolean checkSavingFile;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            if (f.isFile() && f.canWrite()) {
                fw = new FileWriter(f);
                pw = new PrintWriter(fw);
                for (Brand brand : this) {
                    pw.println(brand);

                }

            }

            checkSavingFile = true;
            System.out.println("Saving file to the path: " + str + " successfully");
            fw.close();
            pw.close();

        } catch (IOException e) {
            e.getStackTrace();
            checkSavingFile = false;

        }
        return checkSavingFile;
    }

    public Brand getUserChoice() {

        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(this);

    }

    // check xem lần lượt Id, name, price liệu có hợp lệ. chương trình sẽ thoát nếu nhập ko ID trùng, name trùng, price <0
    public void add_update(Brand brandAdd) {
        Scanner scan = new Scanner(System.in);

        
        do {
            System.out.println("Input ID: ");
            brandAdd.setBrandID(scan.nextLine().trim());
            if (brandAdd.getBrandID().isEmpty()) {
                System.out.println("Error: Brand ID cannot be blank");
                System.out.println("Try again!");
            } else if (searchID(brandAdd.getBrandID()) >= 0) {

                System.out.println("Error: BrandID cannot be duplicated!");
                System.out.println("Try again!");
            }
        } while (searchID(brandAdd.getBrandID()) >= 0 || brandAdd.getBrandID().isEmpty());
        
        // check blank name
        boolean isBlankName = false;
        do {
            isBlankName = false;
            System.out.println("Input brand'name: ");
            brandAdd.setBrandName(scan.nextLine().trim());

            if (brandAdd.getBrandName().isEmpty()) {
                isBlankName = true;
                System.out.println("Error: Brand name cannot be a blank");
                System.out.println("Try again!");

            }

        } while (isBlankName == true);

        // check blank sound
        boolean isBlankSound;
        do {
            isBlankSound = false;
            System.out.println("Input brand'sound: ");
            brandAdd.setBrandSound(scan.nextLine().trim());

            if (brandAdd.getBrandSound().isEmpty()) {
                isBlankSound = true;
                System.out.println("Error: Brand sound cannot be a blank");
                System.out.println("Try again!");

            }

        } while (isBlankSound == true);

        // check positive price
        boolean isPositivePrice;
        do {
            isPositivePrice = true;
            System.out.println("Input brand'price: ");
            brandAdd.setPrice(Double.parseDouble(scan.nextLine()));
            if (brandAdd.getPrice() <= 0) {
                isPositivePrice = false;
                System.out.println("Error:  Brand price must be a positive number");
                System.out.println("Try again!");

            }

        } while (isPositivePrice == false);
    }

    public void addBrand(Brand brandAdd) {
        Scanner box = new Scanner(System.in);
        System.out.println("Add a new brand: ");
        // add a brand
        add_update(brandAdd);
        this.add(brandAdd);
    }

    public void updateBrand() {
        Scanner box = new Scanner(System.in);
        System.out.println("Update a brand with its ID: ");
        String ID = box.nextLine();

        int pos = this.searchID(ID);
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            System.out.println("ID is valid ");
            System.out.println("Input updating data ");

            // update brandName;
            boolean isBlankName = false;
            String newBrandName;
            do {
                isBlankName = false;
                System.out.println("Input updated brand name: ");
                newBrandName = box.nextLine().trim();
                if (newBrandName.isEmpty()) {
                    isBlankName = true;
                    System.out.println("Brand Name cannot be blank!!");
                    System.out.println("Try again!");
                }

            } while (isBlankName == true);

            this.get(pos).setBrandName(newBrandName);

            //update soundBrand
            boolean isBlankSound = false;
            String newSoundBrand;
            do {
                isBlankSound = false;
                System.out.println("Input updated brand sound: ");
                newSoundBrand = box.nextLine().trim();
                if (newSoundBrand.isEmpty()) {
                    isBlankSound = true;
                    System.out.println("Brand Sound cannot be blank!!");
                    System.out.println("Try again!");
                }
            } while (isBlankSound == true);

            this.get(pos).setBrandSound(newSoundBrand);

            // price > 0
            boolean isPositivePrice;
            double newPrice;
            do {
                isPositivePrice = true;
                System.out.println("Input brand'price: ");
                newPrice = Double.parseDouble(box.nextLine());
                if (newPrice <= 0) {
                    isPositivePrice = false;
                    System.out.println("Error:  Brand price must be a positive number");
                    System.out.println("Try again!");

                }

            } while (isPositivePrice == false);
            this.get(pos).setPrice(newPrice);
        }
    }

    public void listBrand() {
        System.out.println("List all brand: ");
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
    }

}
