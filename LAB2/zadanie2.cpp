#include <iostream> 

using namespace std;


class Tablica{
    private:
    int *tablica;
    int rozmiar;

    public:
    Tablica(){
        rozmiar = 10;
        tablica = new int[rozmiar];
    }
    Tablica(int size){
        this->rozmiar = size;
        tablica = new int[rozmiar];
    }
    ~Tablica(){
        cout << "Destruktor tablicy: tablica[" << rozmiar << "]" << endl;  
        delete[] tablica; 
    }

    void addElement(int index, int value){
        if (index>=0 && index<rozmiar){
            tablica[index] = value;
        }
    }

    void printElements(){
        for (int i=0; i<rozmiar; i++){
            cout << i+1 << ": " << tablica[i] << endl;
        }
    }

};

int main(){ 
    Tablica* tab1 = new Tablica();
    Tablica tab2(3);
    
    tab1->addElement(0,10);
    tab1->addElement(1,20);
    tab1->addElement(2,30);
    tab1->addElement(3,40);
    tab1->addElement(4,50);
    tab1->addElement(5,60);
    tab1->addElement(6,70);
    tab1->addElement(7,80);
    tab1->addElement(8,90);
    tab1->addElement(9,100);

    tab1->printElements();
    delete tab1;

    tab2.addElement(0,11);
    tab2.addElement(1,22);
    tab2.addElement(2,33);

    tab2.printElements();

}
