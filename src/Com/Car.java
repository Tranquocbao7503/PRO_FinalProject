package Com;

import Com.Brand;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Car {
//data mem

    String carID;
    Brand brand;
    String color;
    String frameID;
    String engineID;
// get,set

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    // constructor
    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String toString_Car() {

//        try {
//            String path = "D:\\Assignment_OOP\\cars.txt";
//            File f = new File(path);
//            FileWriter fw = new FileWriter(f);
//            PrintWriter pw = new PrintWriter(fw);
        return "<" + this.carID + "," + this.getBrand().getBrandID() + "," + this.color + "," + this.frameID + "," + this.engineID + ">";
//            pw.println(format);
//            return "Write data to" + path + " successfully";
//        } catch (IOException e) {

//        return format;
    }



public String screenString() {

        String str = "<" + this.brand.toString() + "\n" + this.carID + "," + this.color + "," + this.frameID + "," + this.engineID + ">";
        return str;
    }

    public void compare(List<Car> listCar) {
        listCar.sort(new Comparator<Car>() {
            @Override
        public int compare(Car o1, Car o2) {
                int compareBrandName = o1.getBrand().brandName.compareToIgnoreCase(o2.getBrand().brandName);
                if (compareBrandName == 0) {
                    int compareID = o1.getBrand().getBrandID().compareToIgnoreCase(o2.getBrand().getBrandID());
                    return compareID;
                } else {
                    return compareBrandName;
                }
            }

        });

    }
}

 
