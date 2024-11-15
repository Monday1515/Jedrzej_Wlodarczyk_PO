#include <iostream> //prosze skorygowac to rozwiazanie

using namespace std;


class Tablica{
    public:
    int *tablica; // jak poprzednio
    int rozmiar;

    Tablica(){
        rozmiar = 10;
        tablica = new int[rozmiar];
    }
    Tablica(int rozmiar){
        tablica = new int[rozmiar];
        // ustalenie pola rozmiar?
    }
    ~Tablica(){
        cout << "Destruktor tablicy: tablica[" << rozmiar << "]" << endl;  
        delete[] tablica; 
    }

};

int main(){ // // Prosze tworzyc obiekty takze w pamieci dyamicznej
    Tablica tab1;
    Tablica tab2(3);
    // Wypelnienie tablicy liczbami?
    // wyprowadzenie zawartosci tablicy?
}
