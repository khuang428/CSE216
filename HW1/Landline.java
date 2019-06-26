import java.util.Scanner;
import java.util.ArrayList;

public class Landline extends OldLandline{

    enum MSG_STATUS{READ, UNREAD}
    private ArrayList<Message> messages;
    private static Scanner s = new Scanner(System.in); //otherwise if created and closed in the receive method it won't open another System.in Scanner

    public Landline(String o, long n){
        super(o,n);
        messages = new ArrayList<>();
    }

    private class Message{
        private String message;
        private String sender;
        private MSG_STATUS read;

        public Message(String m, String s){
            message = m;
            sender = s;
            read = MSG_STATUS.UNREAD;
        }

        public String getMessage(){
            return message;
        }

        public String getSender(){
            return sender;
        }

        public MSG_STATUS getStatus(){
            return read;
        }

        public void setRead(){
            read = MSG_STATUS.READ;
        }

    }

    public static void closeScanner(){
        s.close();
    }

    @Override
    public void receive(Phone from) {
        if (isBusy()) {
            System.out.println(from.getOwner() + " is unable to call " + getOwner() + ".");
            System.out.print("Does " + from.getOwner() + " want to leave a message? [y/n] ");
            boolean valIn = false;
            while(!valIn) {
                String yn = s.nextLine().toLowerCase();
                switch (yn) {
                    case ("y"):
                        System.out.print("Input message: ");
                        String m = s.nextLine();
                        messages.add(new Message(m, from.getOwner()));
                        System.out.println(from.getOwner() + " left a message for " + getOwner() + ".");
                        valIn = true;
                        break;
                    case ("n"):
                        valIn = true;
                        break;
                    default:
                        System.out.print("Please try again. [y/n]");
                }
            }
        }else{
            setInCall(true);
            setCallWith(from);
            System.out.println(from.getOwner() + " is on the phone with " + getOwner() + ".");
        }
    }

    public void readMessages(){
        System.out.println();
        System.out.println("Messages for " + getOwner());
        System.out.println("----------------------------------------------------------------");
        for(Message m : messages){
            System.out.println(m.getSender() + ": " + m.getMessage());
            m.setRead();
        }
    }

    public void readMessages(MSG_STATUS status){
        System.out.println();
        System.out.println("Messages for " + getOwner());
        System.out.println("----------------------------------------------------------------");
        for(Message m : messages){
            if(m.getStatus() == status){
                System.out.println(m.getSender() + ": " + m.getMessage());
                m.setRead();
            }
        }
    }
}
