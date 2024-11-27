#include <iostream>
#include <string>

using namespace std;

class Pojazd{
    protected:
        string marka;
        string model;
    public:
        Pojazd(string marka, string model){
            this->marka = marka;
            this->model = model;
        }
        Pojazd(){}

        virtual void przyspiesz() = 0;  

        virtual void zatrzymaj() = 0;

};

// class Samochod : private Pojazd - dziedziczenie prywatne
class Samochod : protected Pojazd{ //dziedziczenie chronione
    public:
        Samochod(string marka, string model) : Pojazd(marka, model){
            cout << "Samochod: " << marka << " " << model << endl;
        }

        virtual void przyspiesz(){
            cout << "Samochod przyspiesza" << endl;
        }

        virtual void zatrzymaj(){
            cout << "Samochod sie zatrzymuje" << endl;
        }
};

class Rower : public Pojazd{
    public:
        Rower(){}

        virtual void przyspiesz(){
            cout << "Rower przyspiesza" << endl;
        }
        virtual void zatrzymaj(){
            cout << "Rower sie zatrzymuje" << endl;
        }
};

int main(){
    Samochod* samochod = new Samochod("Fiat","Punto");
    samochod->przyspiesz();
    samochod->zatrzymaj();

    Rower* rower = new Rower();
    rower->przyspiesz();
    rower->zatrzymaj();

    delete samochod;
    delete rower;
}