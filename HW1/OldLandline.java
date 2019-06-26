public class OldLandline implements Phone{
    private String owner;
    private PhoneNumber number;
    private Phone callWith;
    private boolean inCall;

    public OldLandline(String o, long n){
        owner = o;
        number = new PhoneNumber(n);
        inCall = false;
    }

    public String getOwner(){
        return owner;
    }

    public PhoneNumber number(){
        return number;
    }

    public boolean isBusy(){
        return inCall;
    }

    public void setInCall(boolean ic){
        inCall = ic;
    }

    public void setCallWith(Phone cw){
        callWith = cw;
    }

    public void call(Phone phone){
        if(isBusy()){
            System.out.println(getOwner() + " cannot make multiple calls at once.");
            return;
        }
        if(!phone.isBusy()) {
            setInCall(true);
            setCallWith(phone);
        }
        phone.receive(this);
    }

    public void end(){
        if(callWith != null) {
            callWith.receiveEndSignal(this);
            setInCall(false);
            setCallWith(null);
        }
    }

    public void receive(Phone from){
        if(isBusy()){
            System.out.println(from.getOwner() + " is unable to call " + getOwner() + ".");
        }else{
            setInCall(true);
            setCallWith(from);
            System.out.println(from.getOwner() + " is on the phone with " + getOwner() + ".");
        }
    }

    public void receiveEndSignal(Phone from){
        System.out.println(from.getOwner() + " has ended the call with " + getOwner() + ".");
        setInCall(false);
        setCallWith(null);
    }
}