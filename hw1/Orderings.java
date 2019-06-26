import java.util.Comparator;

public class Orderings {
    public static class PhoneNumberComparator implements Comparator<Phone>{
        public int compare(Phone p1, Phone p2){
            if(p1.number().getNumber() < p2.number().getNumber()){
                return -1;
            }else if(p1.number().getNumber() > p2.number().getNumber()){
                return 1;
            }else {
                return 0;
            }
        }
    }

    public static class PhoneOwnerComparator implements Comparator<Phone>{
        public int compare(Phone p1, Phone p2){
            return p1.getOwner().compareToIgnoreCase(p2.getOwner());
        }
    }

    public static class ScreenSizeComparator implements Comparator<Computer>{
        public int compare(Computer c1, Computer c2){
            if(c1.getScreenSize() < c2.getScreenSize()){
                return -1;
            }else if(c1.getScreenSize() > c2.getScreenSize()){
                return 1;
            }else{
                return 0;
            }
        }
    }

    public static class BrandComparator implements Comparator<Computer>{
        public int compare(Computer c1, Computer c2){
            if(c1 instanceof SmartPhone && c2 instanceof SmartPhone){
                return 0;
            }else if(c1 instanceof SmartPhone){
                return -1;
            }else if(c2 instanceof SmartPhone){
                return 1;
            }else{
                return ((Laptop)c1).getBrand().compareToIgnoreCase(((Laptop)c2).getBrand());
            }
        }
    }

    public static class RAMComparator implements Comparator<Computer>{
        public int compare(Computer c1, Computer c2){
            if(c1.getRAM() < c2.getRAM()){
                return -1;
            }else if(c1.getRAM() > c2.getRAM()){
                return 1;
            }else{
                return 0;
            }
        }
    }

}
