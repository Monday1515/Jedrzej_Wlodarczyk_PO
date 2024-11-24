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
};

class Samochod : private Pojazd{
    public:
        Samochod(string marka, string model) : Pojazd(marka,model){}

        void displayInfo(){
            cout << "Marka: " << marka << " Model: " << model << endl;
        }
};

int main(){
    Samochod* samochod = new Samochod("Fiat","Punto");
    samochod->displayInfo();
    delete samochod;
}