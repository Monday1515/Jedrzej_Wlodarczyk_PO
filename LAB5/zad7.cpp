#include <iostream>

using namespace std;

class Silnikowy{
    public:
        void przyspiesz(){
            cout << "Przyspieszenie w silnikowy" << endl;
        }
};

class Elektryczny{
    public:
         void ladowanie(){
            cout << "Laduje elektryczny" << endl;
         }
};

class Hybryda : public Silnikowy, public Elektryczny{
    public:
        void przyspiesz(){
            Silnikowy::przyspiesz();
            Elektryczny::ladowanie();
        }

};

int main(){
    Hybryda* hybryda = new Hybryda();
    hybryda->przyspiesz();
    delete hybryda;
}