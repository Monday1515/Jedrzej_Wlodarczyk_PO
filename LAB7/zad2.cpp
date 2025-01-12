#include <iostream>  // poprawnie; ale, dlaczego rozwiązał Pan tylko dwa zadania z pięciu?

using namespace std;

class Discount {
public:
    virtual double calculateDiscount(double price) const = 0;
};


class RegularDiscount : public Discount {
public:
    double calculateDiscount(double price) const override{
        return price * 0.9;
    }
};


class SeasonalDiscount : public Discount {
public:
    double calculateDiscount(double price) const override{
        return price * 0.8;
    }
};

int main() {
    double price;
    
    cout << "Podaj cene: ";
    cin >> price;

   RegularDiscount regularDiscount;
   SeasonalDiscount seasonalDiscount;

   cout << "Cena z regularnym rabatem: " << regularDiscount.calculateDiscount(price) << endl;
   cout << "Cena z sezonowym rabatem: " << seasonalDiscount.calculateDiscount(price) << endl;

}
