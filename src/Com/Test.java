package Com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Test {

    public static void main(String[] args) {
        System.out.println("HEllo");
        File f = new File("D:\\Assignment_OOP\\Filebrands.txt");
        List<Brand> brandList = new ArrayList<>();
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

                    brandList.add(brand);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("List all brand: ");
        for (Brand brand : brandList) {
            System.out.println(brand);
        }

    }

   
}
