#include <iostream>
#include <string>
#include <vector>

using namespace std;

class User {
private:
    string imie;
    string nazwisko;
    string email;
    int wiek;

public:
    User(const string& imie, const string& nazwisko, const string& email, int wiek) 
        : imie(imie), nazwisko(nazwisko), email(email), wiek(wiek) {}

    string getImie() const{
        return imie;
    }
    string getNazwisko() const{
        return nazwisko;
    }
    string getEmail() const{
        return email;
    }
    int getWiek() const{
        return wiek;
    }
};

// Klasa odpowiedzialna za zapis użytkownika
class UserSaver {
private:
    vector<User> uzytkownicy;
public:
    void saveUser(const User& user) {
        uzytkownicy.push_back(user);

        cout << "Zapisano: " << endl;
        cout << "Imie: " << user.getImie() << endl;
        cout << "Nazwisko: " << user.getNazwisko() << endl;
        cout << "Email: " << user.getEmail() << endl;
        cout << "Wiek: " << user.getWiek() << endl;
    }
};

int main() {
    User user("Jan", "Kowalski", "jankowalski@example.com", 24);

    UserSaver saver;
    saver.saveUser(user);

    return 0;
}
