#include <iostream>
#include <string>
#include <memory>

using namespace std;


class Vehicle {
    protected:
        string model;
    public:
        Vehicle(const string& model) : model(model){}
        virtual void showInfo() const = 0;
        virtual ~Vehicle() = default;

};

class Car : public Vehicle {
    public:
        Car(const string& model) : Vehicle(model){}
        void showInfo() const override{
            cout << "Samochod: " << model << endl;
        }
};

class Bike : public Vehicle {
    public:
        Bike(const string& model) : Vehicle(model){}
        void showInfo() const override{
            cout << "Rower: " << model << endl;
        }
};

class VehicleFactory {
    public:
        static unique_ptr<Vehicle> newVehicle(const string& type, const string& model){
            if(type == "Car"){
                cout << "Utworzylem auto" << endl;
                return make_unique<Car>(model);
            }else if (type == "Bike"){
                cout << "Utworzylem rower" << endl;
                return make_unique<Bike>(model);
            }
            
        }
};

int main() {
   unique_ptr<Vehicle> car = VehicleFactory::newVehicle("Car","Fiat Punto");
   car->showInfo();
   unique_ptr<Vehicle> bike = VehicleFactory::newVehicle("Bike","Giant Escape 3");
   bike->showInfo();

}