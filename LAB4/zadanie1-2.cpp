#include <iostream>
#include <string>

using namespace std;

class Osoba{
    private:
        string imie;
        string nazwisko;
        int wiek;
        string email;
        string telefon;
    public:
        Osoba(){

        }

        string getImie(){
            return imie;
        }
        string getNazwisko(){
            return nazwisko;
        }
        int getWiek(){
            return wiek;
        }

        string getEmail(){
            return email;
        }
        string getTelefon(){
            return telefon;
        }

        void setImie(string name){
            imie = name;
        }
        void setNazwiko(string lastName){
            nazwisko = lastName;
        }
        void setWiek(int age){
            wiek = age;
        }

        void setEmali(string mail){
            bool result = false;
            for(int i=0; i<mail.size(); i++){
                if (mail[i] == '@'){
                    result = true;
                    email = mail;
                    break;
                }
            }
            if (result == false){
                cout << "Bledny adres mailowy" << endl;
            }
        }

        void setTelefon(string phoneNumber){
            if (phoneNumber.size() == 9){
                telefon = phoneNumber;
            } else{
                cout << "Bledny numer telefonu" << endl;
            }
        }

};


int main(){
    Osoba* osoba = new Osoba();

    osoba->setImie("Adam");
    osoba->setNazwiko("Nowak");
    osoba->setWiek(27);

    cout << "Imie: " << osoba->getImie() << endl;
    cout << "Nazwisko: " << osoba->getNazwisko() << endl;
    cout << "Wiek: " << osoba->getWiek() << endl;

    cout << endl;
    osoba->setEmali("example");
    osoba->setEmali("example@pl");
    osoba->setTelefon("12345678");
    osoba->setTelefon("123456789");

    cout << "Email: " << osoba->getEmail() << endl;
    cout << "Telefon: " << osoba->getTelefon() << endl; 

}