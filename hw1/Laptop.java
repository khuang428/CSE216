import java.util.HashSet;

public class Laptop implements Computer{
    private int screenSize;
    private int ram;
    private int processorSpeed;
    private State state;
    private String brand;
    private String hostname;
    private HashSet<String> games = new HashSet<>();

    public Laptop(int ss, int r, int ps, String b, String n){
        screenSize = ss;
        ram = r;
        processorSpeed = ps;
        state = State.OFF;
        brand = b;
        hostname = n + "'s " + brand + " laptop";
    }

    public int getScreenSize(){
        return screenSize;
    }

    public int getRAM(){
        return ram;
    }

    public int getProcessorSpeeed(){
        return processorSpeed;
    }

    public State getState(){
        return state;
    }

    public String getBrand() {
        return brand;
    }

    public String getHostname() {
        return hostname;
    }

    public void setState(String to) throws IllegalArgumentException {
        switch (to.toLowerCase()) {
            case ("on"):
                state = State.ON;
                break;
            case ("off"):
                state = State.OFF;
                break;
            default:
                throw new IllegalArgumentException("Not a valid state");
        }
    }

    public void installGame(String gameName){
        if(hasGame(gameName)){
            return;
        }
        System.out.println("Installing " + gameName + " on " + getHostname() + ".");
        games.add(gameName);
    }

    public boolean hasGame(String gameName){
        return games.contains(gameName);
    }

    public void playGame(String gameName){
        if(!games.contains(gameName)){
            System.out.println(getHostname() + " does not have " + gameName + ".");
        }else{
            System.out.println(getHostname() + " is running " + gameName + ".");
        }
    }
}
