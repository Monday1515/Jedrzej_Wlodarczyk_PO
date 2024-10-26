#include <iostream>
#include <string>
#include <vector>


using namespace std;

class Parrot{
    public:
    string phrase;
    vector<string> phrases;

    Parrot(string phrase){
        this->phrase = phrase;
        phrases.push_back(phrase);
    }

    void addPhrase(string phrase){
        phrases.push_back(phrase);
    }

    void say(int repetitions = 1){
        for (int i=0; i<repetitions; ++i){
            int randomIndex = rand() % phrases.size();
            cout << phrases[randomIndex] << endl;
        }
    }
};

int main(){
    Parrot parrot("Hello world!");
    parrot.addPhrase("Hi");
    parrot.addPhrase("Cześć");
    parrot.addPhrase("Thank you");
    parrot.say(2);
}