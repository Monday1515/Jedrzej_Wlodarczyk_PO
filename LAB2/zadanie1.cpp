#include <iostream>
#include <string>

using namespace std;


class Car{
    public:
    string marka; // atrybuty zwyczajowo przechowuje sie w polach o dostepie private/protected
    string model;
    int rokProdukcji;

    Car(string marka, string model, int rokProdukcji){
        this->marka = marka;
        this->model = model;
        this->rokProdukcji = rokProdukcji;
    }
    Car(){
        marka = "Toyota";
        model = "Corolla";
        rokProdukcji = 2020;
    }
    ~Car(){
        cout << "Destruktor wywołany dla " << marka << " " << model << endl; // informacja o likwidacji obiektu?
    }
};

int main(){
    Car car1;
    Car car2("Fiat","Punto",2014);
}
