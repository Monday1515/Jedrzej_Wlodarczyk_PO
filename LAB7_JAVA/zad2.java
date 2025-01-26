import java.util.Scanner; // poprawnie

interface Discount {
   double calculate(double price); 
}

class RegularDiscount implements Discount {
     @Override
     public double calculate(double price) {
         return 0.9*price;
     }
}


class SeasonalDiscount implements Discount {
    @Override
    public double calculate(double price) {
        return 0.8*price;
    }
}

public class zad2 {
    public static void main(String[] args) {
        RegularDiscount regularDiscount = new RegularDiscount();
        SeasonalDiscount seasonalDiscount = new SeasonalDiscount();


        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj cene:");
        double price = scanner.nextDouble();

        System.out.println("Cena po zniżce regularnej:");
        System.out.println(regularDiscount.calculate(price));

        System.out.println("Cena po zniżce sezonowej:");
        System.out.println(seasonalDiscount.calculate(price));

    }
}
