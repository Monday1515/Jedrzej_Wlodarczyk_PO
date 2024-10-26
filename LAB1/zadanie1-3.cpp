#include <iostream>
#include <string>

using namespace std;

class Car{
    public:
    string make;
    string model;
    int year;
    int currentYear = 2024;

    Car(string make, string model, int year){
        this->make = make;
        this->model = model;
        this->year = year;
    }

    void displayInfo(){
        cout << "Brand: " << make << endl;
        cout << "Model: " << model << endl;
        cout << "Year of car production: " << year << endl;
    }
    
    int getCarAge(){
        return currentYear - year;
    }

    bool isSameCar(Car otherCar){
        if(otherCar.make == make && otherCar.model == model && otherCar.year == year){
            return true;
        } else{
            return false;
        }
    }

};



int main(){
    Car car1("Toyota","Corolla",2020);
    car1.displayInfo();
    cout << "Car age: " << car1.getCarAge() << endl;

    Car car2("Honda","Civic",2019);
    Car car3("Toyota","Corolla",2020);

    if (car1.isSameCar(car2) == true){
        cout << "The same cars" << endl;
    } else{
        cout << "Not the same cars" << endl;
    }
    
    if (car1.isSameCar(car3) == true){
        cout << "The same cars" << endl;
    } else{
        cout << "Not the same cars" << endl;
    }
    
}