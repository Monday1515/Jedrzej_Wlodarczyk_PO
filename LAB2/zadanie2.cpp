#include <iostream>

using namespace std;


class Tablica{
    public:
    int *tablica;
    int rozmiar;

    Tablica(){
        rozmiar = 10;
        tablica = new int[rozmiar];
    }
    Tablica(int rozmiar){
        tablica = new int[rozmiar];
    }
    ~Tablica(){
        cout << "Destruktor tablicy: tablica[" << rozmiar << "]" << endl;  
        delete[] tablica; 
    }

};

int main(){
    Tablica tab1;
    Tablica tab2(3);
}