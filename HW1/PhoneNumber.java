public class PhoneNumber{
    private final long NUMBER;

    public PhoneNumber(long n)throws IllegalArgumentException{
        String num = Long.toString(n);
        if(num.length() != 10 || n < 0){
            throw new IllegalArgumentException("Invalid phone number");
        }
        NUMBER = n;
    }

    public long getNumber() {
        return NUMBER;
    }
}