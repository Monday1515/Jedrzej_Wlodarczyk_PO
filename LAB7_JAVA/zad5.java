interface MessageService {   // poprawnie
   void sendMessage(String message);
}

class EmailService implements MessageService {
    @Override
    public void sendMessage(String message){
        System.out.println("Wysłano wiadomość: " + message);
    }
}

class Notification {
    private MessageService messageService;
   
    public Notification(MessageService messageService) {
       this.messageService = messageService;
    }

    public void notify(String message) {
       messageService.sendMessage(mesage);
    }
}

public class zad5 {
    public static void main(String[] args) {
        MessageService emailService = new EmailService();
        Notification notification = new Notification(emailService); 
        notification.notify("Witaj w SOLID!");
    }
}
