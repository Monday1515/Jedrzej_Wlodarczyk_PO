#include <iostream>    // przejrzałam wszystkie rozwiązanie, w ogólności - sa poprawne

using namespace std;

class Pojazd{
    public:
        virtual void przyspiesz() const{
            cout << "Pojazd przyspiesza" << endl;
        }
};

class Silnikowy : virtual public Pojazd{
    public:
        void przyspiesz() const override{
            cout << "Silnikowy przyspiesza" << endl;
        }
};

class Elektryczny : virtual public Pojazd{
    public:
        void przyspiesz() const override{
            cout << "Samochod elektryczny przyspiesza" << endl;
        }
};

class Hybryda : public Silnikowy, public Elektryczny{
    public:
        void przyspiesz() const override{
            cout << "Hybrydowy przyspiesza" << endl;
        }
};

int main(){
    Hybryda* hybryda = new Hybryda();
    hybryda->przyspiesz();

    delete hybryda;
}
