package Com;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static <E> int int_getChoice(ArrayList options) {

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "-" + options.get(i));

        }
        int response;
        do {

            System.out.println("Choose 1-" + options.size() + ": ");
            Scanner scan = new Scanner(System.in);
            response = Integer.parseInt(scan.nextLine());
            return response;
        } while (response < 1 || response > options.size());
    }

    public static <E> E ref_getChoice(ArrayList<E> options) {
        int response;
        do {
            response = int_getChoice(options);
        } while (response < 0 || response > options.size());
        return options.get(response - 1);   // trừ đi 1 vì index của option bắt đầu từ 0

    }

}
