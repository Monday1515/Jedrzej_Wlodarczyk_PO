import java.util.ArrayList;  // poprawnie

class User {
    private String name;
    private int age;
    private String email;
    
    public User(String imie, int wiek, String email) {
        this.name = imie;
        this.age = wiek;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getEmail() {
        return email;
    }
}


class UserSaver {
    public void saveUser(User user, ArrayList arrayList) {
        arrayList.add(user);
        System.out.println("Dodano uzytkownika: " + user.getName() + " " + user.getAge() + " " + user.getEmail());
    }
}

public class zad1 {
    public static void main(String[] args) {
       ArrayList<String> users = new ArrayList<String>();

       User user = new User("Piotr", 20, "piotr@example.com");
       UserSaver userSaver = new UserSaver();

       userSaver.saveUser(user, users);

    }
}
