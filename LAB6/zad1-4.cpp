#include <iostream>
#include <vector>
#include <memory>

using namespace std;

class Pojazd{
    public:
        virtual void przyspiesz(){
            cout << "Pojazd przyspiesza" << endl;
        }
        virtual void zatrzymaj(){
            cout << "Pojazd sie zatrzymuje" << endl;
        }

        virtual ~Pojazd(){}
};

class Samochod : public Pojazd{
    public:
        void przyspiesz() override{
            cout << "Samochod przyspiesza" << endl;
        }
        void zatrzymaj() override{
            cout << "Samochod sie zatrzymuje" << endl;
        }
};

class Rower : public Pojazd{
    public:
        void przyspiesz() override{
            cout << "Rower przyspiesza" << endl;
        }
        void zatrzymaj() override{
            cout << "Rower sie zatrzymuje" << endl;
        }
};

class Motocykl : public Pojazd{
    public:
        void przyspiesz() override{
            cout << "Motocykl przyspiesza" << endl;
        }
        void zatrzymaj() override{
            cout << "Motocykl sie zatrzymuje" << endl;
        }
};

int main(){
    Samochod* samochod = new Samochod();
    Rower* rower = new Rower();
    Motocykl* motocykl = new Motocykl();

    vector<std::unique_ptr<Pojazd>> pojazdy;
    pojazdy.push_back(make_unique<Samochod>());
    pojazdy.push_back(make_unique<Rower>());
    pojazdy.push_back(make_unique<Motocykl>());

    for(int i=0; i<pojazdy.size(); i++){
        pojazdy[i]->przyspiesz();
        pojazdy[i]->zatrzymaj();
    }
}