#include <iostream>  // rozwiazanie poprawne
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

        virtual ~Pojazd(){} // jakies ciało?
};

class Samochod : public Pojazd{
    public:
        void przyspiesz() override{
            cout << "Samochod przyspiesza" << endl;
        }
        void zatrzymaj() override{
            cout << "Samochod sie zatrzymuje" << endl;
        } // szkoda, że nie zdefiniował Pan destruktorów w klasach pochodnych
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
    pojazdy.push_back(make_unique<Samochod>()); // obiekt Pojazd tez mozna utworzyc
    pojazdy.push_back(make_unique<Rower>());
    pojazdy.push_back(make_unique<Motocykl>());

    for(int i=0; i<pojazdy.size(); i++){
        pojazdy[i]->przyspiesz();
        pojazdy[i]->zatrzymaj();
    }
}
