package Com;

import Com.Car;
import Com.BrandList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarList extends ArrayList<Car> {

    BrandList brandList;
    private String carID;

    public CarList(BrandList bList) {
        this.brandList = bList;
    }

    public boolean loadFromFile(String filename) {

        File f = new File(filename);

        if (!f.isFile()) {
            return false;
        } else {

            try {
                FileReader fileReader = new FileReader(f);
                BufferedReader reader = new BufferedReader(fileReader);
                String line = reader.readLine();

                while (line != null) {
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");
                    String carID = tokenizer.nextToken().trim();
                    String carBrandID = tokenizer.nextToken().trim();

                    int pos = brandList.searchID(carBrandID);
                    {
                        if (pos != -1) {
                            Brand brand = brandList.get(pos);

                            String color = tokenizer.nextToken().trim();
                            String frameID = tokenizer.nextToken().trim();
                            String engineID = tokenizer.nextToken().trim();
                            Car car = new Car(carID, brand, color, frameID, engineID);
                            this.add(car);
                            line = reader.readLine();
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;

        }
    }
    //    public boolean saveToFile(List<Car> listCar) {
    //        try {
    //            
    //            String path = "D:\\Assignment_OOP\\saveCar_Data.txt";
    //            File f = new File(path);
    //            FileOutputStream fos = new FileOutputStream(f, true);
    //            ObjectOutputStream oos = new ObjectOutputStream(fos);
    //            for (Car car : listCar) {
    //                oos.writeObject(car);
    //                oos.close();
    //                fos.close();
    //                
    //            }
    //            
    //        } catch (Exception e) {
    //            System.out.println("Error: Cannot write data to the file");
    //            e.getStackTrace();
    //        }
    //        return true;
    //    }

    public boolean saveToFile(String str) {

        File f = new File(str);
        boolean checkSavingFile = false;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            if (f.isFile() && f.canWrite()) {
                fw = new FileWriter(f);
                pw = new PrintWriter(fw);
                for (int i=0;i<this.size();i++) {
                    pw.println(this.get(i).toString_Car());
                    
                }
            }
            checkSavingFile = true;
            System.out.println("Saving file to the path: " + str + " successfully");
            fw.close();
            pw.close();

        } catch (IOException e) {
            e.getStackTrace();
            checkSavingFile = false;

        }finally {
        if (pw != null) {
            pw.close();
        }
        if (fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return checkSavingFile;
    }
    }

    public int searchID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().equalsIgnoreCase(ID)) {
                return i;
            }

        }
        return -1;
    }

    public int searchFrame(String fID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().equalsIgnoreCase(fID)) {
                return i;
            }
        }
        return -1;

    }

    public int searchEngine(String eID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEngineID().equalsIgnoreCase(eID)) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {

        // check car ID duplicated
        Car newCar = new Car();

        Scanner box = new Scanner(System.in);
        System.out.println("Input added Car'ID: ");
        String carIDNew = box.nextLine();
        int pos = searchID(carIDNew.trim());
        if (pos >= 0) {
            System.out.println("Error: Car's ID is already existed!");
            System.out.println("Try again!");

        } else {
            newCar.setCarID(carIDNew);
            Brand brand = (Brand) Menu.ref_getChoice(brandList);
            newCar.setBrand(brand);
            boolean isBlankColor;
            String newColor;
            do {
                isBlankColor = false;
                System.out.println("Input Car'color: ");
                newColor = box.nextLine().trim();

                if (newColor.isEmpty()) {
                    isBlankColor = true;
                    System.out.println("Error: Car'color cannot be a blank");
                    System.out.println("Try again!");

                }

            } while (isBlankColor == true);

            // set color         
            newCar.setColor(newColor);

            String formatFrame = "^F\\d{5}$";
            String newFrameID;
            do {
                System.out.println("Input Car's frame ID ( FrameID must be in this form 'F00000' ): ");
                newFrameID = box.nextLine();

                if (!newFrameID.trim().matches(formatFrame)) {
                    System.out.println("Invalid Frame ID! The Frame ID must start with 'F' and have 5 following characters (with the remaining characters being digits).");
                    System.out.println("Try again!");

                } else if (this.searchFrame(newFrameID.trim()) >= 0) {
                    System.out.println("Error: FrameId is already existed!");
                    System.out.println("Try again!");
                } else {
                    System.out.println("valid FrameID");
                }
            } while (!newFrameID.trim().matches(formatFrame) || this.searchFrame(newFrameID.trim()) >= 0);
            // set frameID         
            newCar.setFrameID(newFrameID.trim());

            String formatEngine = "^E\\d{5}$";
            String newEngineID;
            do {
                System.out.println("Input Car's engine ID ( EngineID must be in this form 'E00000' ): ");
                newEngineID = box.nextLine();
                if (!newEngineID.trim().matches(formatEngine)) {
                    System.out.println("Invalid Engine ID! The Engine ID must start with 'E' and have 5 following characters (with the remaining characters being digits).");
                    System.out.println("Try again!");
                } else if (this.searchEngine(newEngineID.trim()) >= 0) {
                    System.out.println("Error: EngineID is already existed!");
                    System.out.println("Try again!");
                } else {
                    System.out.println("valid EngineID");

                }

            } while (!newEngineID.trim().matches(formatEngine) || this.searchEngine(newEngineID.trim()) >= 0);
            // set EngineID
            newCar.setEngineID(newEngineID.trim());

            this.add(newCar);

            System.out.println("Add a new Car with car ID:" + carIDNew + "  successfully");

        }

    }

    public void printBasedBrandName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input a part of brand name: ");
        String aPartOfBrandName = scan.nextLine();
        System.out.println("Result: ");
        int count = 0;
        for (int i = 0; i < this.size(); i++) {
            String seacrh = aPartOfBrandName.trim().toLowerCase();
            if (this.get(i).getBrand().getBrandName().toLowerCase().contains(seacrh)) {
                System.out.println("<" + this.get(i).getCarID() + "," + this.get(i).getBrand().getBrandID() + ": " + this.get(i).getBrand().getBrandName() + "," + this.get(i).getColor() + "," + this.get(i).getFrameID() + "," + this.get(i).getEngineID() + ">");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected");
        }

    }

    public boolean removeCar() {
        if (this.isEmpty()) {
            System.out.println("Empty list. It cannot be removed");
        } else {

            Scanner scan = new Scanner(System.in);
            System.out.println("Input a car ID to remove: ");
            String removeCarID = scan.nextLine();
            int pos = searchID(removeCarID.trim());
            if (pos < 0) {
                System.out.println("Car'ID does not exist");
            } else {
                this.remove(pos);
                System.out.println("Remove Car with ID:" + removeCarID + " successfully");
            }

        }
        return true;
    }

    public boolean updateCar() {
        Scanner box = new Scanner(System.in);
        System.out.println("Input Car'ID update: ");
        String carIDUpdate = box.nextLine();
        int pos = searchID(carIDUpdate);
        if (pos < 0) {
            System.out.println("Not Found");
            return false;
        } else {

            Brand b = Menu.ref_getChoice(brandList);
            //          System.out.println("Check");
            boolean isBlankColor;
            String newColor;
            do {
                isBlankColor = false;
                System.out.println("Input Car'color: ");
                newColor = box.nextLine().trim();

                if (newColor.isEmpty()) {
                    isBlankColor = true;
                    System.out.println("Error: Car'color cannot be a blank");
                    System.out.println("Try again!");

                }

            } while (isBlankColor == true);

            // update color         
            this.get(pos).setColor(newColor);

            String formatFrame = "^F\\d{5}$";
            String newFrameID;
            do {
                System.out.println("Input Car's frame ID ( FrameID must be in this form 'F00000'): ");
                newFrameID = box.nextLine();

                if (!newFrameID.trim().matches(formatFrame)) {
                    System.out.println("Invalid Frame ID! The Frame ID must start with 'F' and have 5 following characters (with the remaining characters being digits).");
                    System.out.println("Try again!");

                } else if (this.searchFrame(newFrameID.trim()) >= 0) {
                    System.out.println("Error: FrameId is already existed!");
                    System.out.println("Try again!");
                } else {
                    System.out.println("valid FrameID");
                }
            } while (!newFrameID.trim().matches(formatFrame) || this.searchFrame(newFrameID.trim()) >= 0);
            // update frameID         
            this.get(pos).setFrameID(newFrameID.trim());

            String formatEngine = "^E\\d{5}$";
            String newEngineID;
            do {
                System.out.println("Input Car's engine ID ( EngineID must be in this form 'E00000'): ");
                newEngineID = box.nextLine();
                if (!newEngineID.trim().matches(formatEngine)) {
                    System.out.println("Invalid Engine ID! The Engine ID must start with 'E' and have 5 following characters (with the remaining characters being digits).");
                    System.out.println("Try again!");
                } else if (this.searchEngine(newEngineID.trim()) >= 0) {
                    System.out.println("Error: EngineID is already existed!");
                    System.out.println("Try again!");
                } else {
                    System.out.println("valid EngineID");

                }

            } while (!newEngineID.trim().matches(formatEngine) || this.searchEngine(newEngineID.trim()) >= 0);
            // update EngineID
            this.get(pos).setEngineID(newEngineID.trim());

        }
        return true;
    }

//    public void listAscendingBrands() {
//
//        this.sort(new Comparator<Car>() {
//            @Override
//            public int compare(Car o1, Car o2) {
//                return o1.getBrand().getBrandName().compareToIgnoreCase(o2.getBrand().getBrandName());
//            }
//
//        });
//
//        for (int i = 0; i < this.size(); i++) {
//            System.out.println(this.get(i).screenString());
//        }
//
//    }
    public void listAscendingBrands() {

        this.sort(new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                int compare = o1.getBrand().getBrandName().compareToIgnoreCase(o2.getBrand().getBrandName());
                if (compare != 0) {
                    return compare;
                } else {
                    return o1.getCarID().compareToIgnoreCase(o2.getCarID());
                }
            }

        });

        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i).screenString());
        }

    }

}
