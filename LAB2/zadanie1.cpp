#include <iostream>
#include <string>

using namespace std;


class Car{
    private:
    string marka; 
    string model;
    int rokProdukcji;
    
    public:
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
        cout << "Destruktor wywołany dla " << marka << " " << model << endl;
    }
};

int main(){
    Car car1;
    Car car2("Fiat","Punto",2014);
}
