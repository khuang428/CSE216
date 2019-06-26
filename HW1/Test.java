import java.util.ArrayList;

public class Test{
    public static void main(String[]args){
        try {
            OldLandline bob = new OldLandline("Bob", 7771234321L);
            Phone joe = new OldLandline("Joe", 8886451298L);
            Phone explictica = new Landline("Explictica", 6668462839L);
            OldLandline strahd = new Landline("Strahd", 7989523451L);
            Phone lila = new SmartPhone("Lila", 5554329578L,6,4,15);
            Computer isaac = new SmartPhone("Isaac", 6543098711L,5,2,14);
            SmartPhone cali = new SmartPhone("Cali", 2349874398L,4,2,13);
            Computer grog = new Laptop(17,16,20,"Lenovo","Grog");
            Laptop felicia = new Laptop(15,8, 30,"Dell","Felicia");


            ArrayList<Phone> phoneList = new ArrayList<>();
            phoneList.add(bob);
            phoneList.add(joe);
            phoneList.add(explictica);
            phoneList.add(strahd);
            phoneList.add(lila);
            phoneList.add(cali);
            //apparent type is computer so this won't work :o
            //phoneList.add(isaac);

            ArrayList<Computer> compList = new ArrayList<>();
            //apparent type is phone so this won't work :o
            //compList.add(lila);
            compList.add(isaac);
            compList.add(cali);
            compList.add(felicia);
            compList.add(grog);

            System.out.println("Phone Number Comparator");
            System.out.println("----------------------------------------------------------------");
            phoneList.sort(new Orderings.PhoneNumberComparator());
            for(Phone p:phoneList){
                System.out.println(p.getOwner()+ ": " + p.number().getNumber());
            }

            System.out.println();
            System.out.println("Phone Owner Comparator");
            System.out.println("----------------------------------------------------------------");
            phoneList.sort(new Orderings.PhoneOwnerComparator());
            for(Phone p:phoneList){
                System.out.println(p.getOwner());
            }

            System.out.println();
            System.out.println("Screen Size Comparator");
            System.out.println("----------------------------------------------------------------");
            compList.sort(new Orderings.ScreenSizeComparator());
            for(Computer c:compList){
                if(c instanceof SmartPhone){
                    System.out.print(((SmartPhone) c).getOwner());
                }else{
                    System.out.print(((Laptop) c).getHostname());
                }
                System.out.println(": " + c.getScreenSize());
            }

            System.out.println();
            System.out.println("Brand Comparator");
            System.out.println("----------------------------------------------------------------");
            compList.sort(new Orderings.BrandComparator());
            for(Computer c:compList) {
                if (c instanceof SmartPhone) {
                    System.out.println(((SmartPhone) c).getOwner());
                }else{
                    System.out.println(((Laptop) c).getHostname() + ": " + ((Laptop) c).getBrand());
                }
            }

            System.out.println();
            System.out.println("RAM Comparator");
            System.out.println("----------------------------------------------------------------");
            compList.sort(new Orderings.RAMComparator());
            for(Computer c:compList){
                if(c instanceof SmartPhone){
                    System.out.print(((SmartPhone) c).getOwner());
                }else{
                    System.out.print(((Laptop) c).getHostname());
                }
                System.out.println(": " + c.getRAM());
            }

            System.out.println();
            System.out.println("One Call Only");
            System.out.println("----------------------------------------------------------------");
            bob.call(joe);
            bob.call(explictica); //Can only call one person!
            explictica.call(joe);
            explictica.call(bob);
            joe.end();
            joe.end(); //shouldn't be able to happen, but testing that this won't cause problems

            System.out.println();
            System.out.println("Leaving and Reading Messages");
            System.out.println("----------------------------------------------------------------");
            bob.call(explictica);
            strahd.call(explictica);
            bob.end();
            joe.call(explictica);
            bob.call(explictica);
            ((Landline) explictica).readMessages();

            System.out.println();
            System.out.println("Reading by MSG_STATUS");
            System.out.println("----------------------------------------------------------------");

            bob.call(explictica);
            joe.end();
            strahd.call(explictica);
            bob.call(explictica);
            explictica.end();
            ((Landline) explictica).readMessages(Landline.MSG_STATUS.UNREAD);

            System.out.println();
            System.out.println("Reading All Messages");
            System.out.println("----------------------------------------------------------------");
            explictica.call(bob);
            joe.call(explictica);
            bob.end();
            ((Landline) explictica).readMessages();

            System.out.println();
            System.out.println("Testing SmartPhone Calling");
            System.out.println("----------------------------------------------------------------");
            bob.call(lila);
            cali.call(lila);
            cali.call(joe);
            explictica.call(cali);
            lila.end();
            cali.end();
            ((SmartPhone) lila).readMessages();

            System.out.println();
            System.out.println("Installing A Game");
            System.out.println("----------------------------------------------------------------");
            ((SmartPhone) lila).playGame("Candy Cronch");
            ((SmartPhone) lila).installGame("Candy Cronch");
            ((SmartPhone) lila).installGame("Candy Cronch");
            ((SmartPhone) lila).playGame("Candy Cronch");

            System.out.println();
            System.out.println("Installing 6 Games");
            System.out.println("----------------------------------------------------------------");
            cali.installGame("Floppy Birb");
            cali.installGame("Floppy Birb"); // making sure this doesn't take up another slot
            cali.installGame("Text Adventurer");
            cali.installGame("Pay To Win Card Game");
            cali.installGame("tAP cHaMPiOns");
            cali.installGame("Interactive Book");
            cali.installGame("Chop Chop Kitchen");

            System.out.println();
            System.out.println("Testing Games on a Laptop");
            System.out.println("----------------------------------------------------------------");
            felicia.installGame("Destruction");
            felicia.playGame("Destructo");
            grog.playGame("Destruction");
            grog.installGame("Happy Fun Times");
            grog.playGame("Happy Fun Times");
            felicia.installGame("Destructo");
            felicia.playGame("Destruction");
            felicia.installGame("Story Racer");
            felicia.installGame("Farming Simulator 2025");
            felicia.installGame("Disneygame Redone");
            felicia.installGame("Free Battle Royale With Paid Cosmetics");



            //Testing for the exceptions
            //OldLandline bobbert = new OldLandline("Bob", -7771234321L);
            //OldLandline bobbert = new OldLandline("Bob", -123456789);
            //.setState("noff");

            Landline.closeScanner();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}