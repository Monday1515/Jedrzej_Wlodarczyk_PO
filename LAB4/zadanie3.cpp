#include <iostream>
#include <string>

using namespace std;

class Pracownik{
    protected:
        string stanowisko;
        float wynagrodzenie;
    public:
        Pracownik(string stanowisko, float wynagrodzenie){
            this->stanowisko = stanowisko;
            this ->wynagrodzenie = wynagrodzenie;
        }

        virtual void pokazDane() const = 0;

};

class Nauczyciel : public Pracownik{
    public:
        Nauczyciel(string stanowisko, float wynagrodzenie) : Pracownik(stanowisko, wynagrodzenie){}
        
        void pokazDane() const override{
            cout << "Stanowisko: " << stanowisko << endl;
            cout << "Wynagrodzenie: " << wynagrodzenie << endl;
        }
};

class Administracja : public Pracownik{
    public:
        Administracja(string stanowisko, float wynagrodzenie) : Pracownik(stanowisko, wynagrodzenie){}
        
        void pokazDane() const override{
            cout << "Stanowisko: " << stanowisko << endl;
            cout << "Wynagrodzenie: " << wynagrodzenie << endl;
        }
};


int main(){
    Nauczyciel* nauczyciel = new Nauczyciel("chemia", 5300.25);
    nauczyciel->pokazDane();

    cout << endl;

    Administracja* administracja = new Administracja("dyrektor", 7843.84);
    administracja->pokazDane();
}