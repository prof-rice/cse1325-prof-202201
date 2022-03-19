import java.util.Scanner;
import java.util.ArrayList;

public class VendingMachine {
    private ArrayList<Juice> products = new ArrayList<>();
    public void add(Juice juice) {products.add(juice);}
    public void printMenu() {
        for(int i=0; i<products.size(); ++i) {
            System.out.println(i + ") " + products.get(i));
        }
    }
    public void buy(int productIndex) {products.remove(productIndex);}
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        VendingMachine vm = new VendingMachine();

        /*
        vm.add(new Juice(Fruit.apple,  8));
        vm.add(new Juice(Fruit.grape,  6));
        vm.add(new Juice(Fruit.orange, 12));

        System.out.println("Welcome to Juicetopia\n=====================\n");
        vm.printMenu();

        System.out.print("Selection? ");
        vm.buy(in.nextInt());
        System.out.println("Dispensing... Thank you!\n\nProduct remaining:\n");
        vm.printMenu();
        
        */

        while(true) {
            System.out.println();
            vm.printMenu();
            System.out.println();

            System.out.print("(a)dd to stock (b)uy (q)uit? ");
            try {
                switch(in.nextLine().charAt(0)) {
                    case 'a' -> {
                        try {
                            System.out.print("Fruit? ");
                            Fruit f = Fruit.valueOf(in.nextLine());
                            System.out.print("Ounces? ");
                            int o = in.nextInt(); 
                            in.nextLine(); // clear the newline
                            vm.add(new Juice(f, o));
                        
                        } catch(IllegalArgumentException e) {
                            System.err.println("##### Invalid input: " + e.getMessage());
                        }
                    }
                    case 'b' -> {
                        try {
                            System.out.print("Item index?");
                            int q = in.nextInt();
                            in.nextLine(); // clear the newline
                            vm.buy(q);
                        } catch(java.lang.IndexOutOfBoundsException e) {
                            System.err.println("##### Invalid selection: " + e.getMessage());
                        }
                    }
                    case 'q' -> System.exit(0);
                    default -> System.err.println("##### Invalid command");
                }
            } catch(Exception e) {
                System.err.println("##### Invalid input");
            }
          }
      }
 }
