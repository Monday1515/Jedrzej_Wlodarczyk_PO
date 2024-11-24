#include <iostream>

using namespace std;

class Pojazd{
    public:
        virtual void przyspiesz() const{
            cout << "Pojazd przyspiesza" << endl;
        }
};

class Samochod : public Pojazd{
    public:
        void przyspiesz() const override{
            cout << "Samochod przyspiesza" << endl;
        }
};

class ElektrycznySamochod : public Samochod{
    public:
        void przyspiesz() const override{
            cout << "Samochod elektryczny przyspiesza" << endl;
        }
};

int main(){
    ElektrycznySamochod* elektryczny = new ElektrycznySamochod();
    elektryczny->przyspiesz();
}