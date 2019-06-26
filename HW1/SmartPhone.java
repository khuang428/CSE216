import java.util.HashSet;

public class SmartPhone extends Landline implements Computer{
    private int screenSize;
    private int ram;
    private int processorSpeed;
    private State state;
    private HashSet<String> games = new HashSet<>();

    public SmartPhone(String o, long n, int ss, int r, int ps){
        super(o,n);
        screenSize = ss;
        ram = r;
        processorSpeed = ps;
        state = State.OFF;
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

    public void setState(String to) throws IllegalArgumentException{
        switch(to.toLowerCase()) {
            case("on"):
                state = State.ON;
                break;
            case("off"):
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
        if(games.size() == 5){
            System.out.println("There's no space for " + gameName + ".");
        }else{
            System.out.println("Installing " + gameName + " on " + getOwner() + "'s smart phone.");
            games.add(gameName);
        }
    }

    public boolean hasGame(String gameName){
        return games.contains(gameName);
    }

    public void playGame(String gameName){
        if(!games.contains(gameName)){
            System.out.println(getOwner() + "'s smart phone does not have " + gameName + ".");
        }else{
            System.out.println(getOwner() + " is playing " + gameName + ".");
        }
    }
}
