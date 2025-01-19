interface Printer {
    void drukuj();
}

interface Scanner {
    void skanuj();
}

class MultiFunctionMachine implements Printer, Scanner {
    private String nazwa;
    MultiFunctionMachine(String nazwa){
        this.nazwa = nazwa;
    }

    @Override
    public void drukuj() {
        System.out.println(nazwa + " drukuje!");
    }

    @Override
    public void skanuj() {
        System.out.println(nazwa + " skanuje!");
    }
}

public class zad4{
    public static void main(String[] args) {
        MultiFunctionMachine multiFunctionMachine = new MultiFunctionMachine("Samsung");
        multiFunctionMachine.drukuj();
        multiFunctionMachine.skanuj();
    }
}