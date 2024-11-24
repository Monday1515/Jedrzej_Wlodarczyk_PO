#include <iostream>

using namespace std;

class Pojazd{
    public:
        Pojazd(){}
        virtual void przyspiesz() const = 0;
        virtual void zatrzymaj() const = 0;
};

class Samochod : public Pojazd{
    public:
        Samochod(){}
        void przyspiesz() const override{
            cout << "Samochod przyspiesza" << endl;
        }
        void zatrzymaj() const override{
            cout << "Samochod sie zatrzymuje" << endl;
        }
};

class Rower : public Pojazd{
    public:
        Rower(){}
        void przyspiesz() const override{
            cout << "Rower przyspiesza" << endl;
        }
        void zatrzymaj() const override{
            cout << "Rower sie zatrzymuje" << endl;
        }
};

int main(){
    Samochod* samochod = new Samochod();
    Rower* rower = new Rower();

    samochod->przyspiesz();
    rower->przyspiesz();
    samochod->zatrzymaj();
    rower->zatrzymaj();

    delete samochod;
    delete rower;
}