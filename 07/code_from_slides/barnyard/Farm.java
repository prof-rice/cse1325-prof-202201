import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Farm {
    public static void main(String[] args) {
        // The double-brackets create an "anonymous inner class",
        // which we sometimes use to create an immutable arraylist
        // initialized in place. 
        // Otherwise, just declare and initialize in separate statements.
        ArrayList<Critter> critters = new ArrayList<> () {{
            add(new Cow(13));
            add(new Dog(11));
            add(new Dog(9));
            add(new Cow(7));
            add(new Chicken(5));
            add(new Dog(3));
            add(new Chicken(2));

        }};
        
        TimeUnit ms = TimeUnit.MILLISECONDS;

        System.out.println("W E L C O M E   T O   T H E   B A R N Y A R D !");
        for (int i=0; i<120; ++i) {
            for (Critter c: critters) { 
                 c.count(); 
                 c.speak();
             }
            try {
                ms.sleep(50L);
            } catch (InterruptedException e) { 
            }
        }
    }
}