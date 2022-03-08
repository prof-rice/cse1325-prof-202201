import shelter.Shelter;
import shelter.Animal;
import shelter.Dog;
import shelter.DogBreed;
import shelter.Cat;
import shelter.CatBreed;
import shelter.Gender;

import java.util.Scanner;

public class MainCli {
    private Scanner in = new Scanner(System.in);

    private String breed;
    private String name;
    private Gender gender;
    private int age;
    private void getAnimal(String family) {
        System.out.print("Select " + family + " breed: ");
        breed = in.nextLine();
        System.out.print(breed + "'s name? ");
        name = in.nextLine();
        for(Gender g : Gender.values())
            System.out.println(g.name());
        System.out.print("Select gender: ");
        gender = Gender.valueOf(in.nextLine());
        System.out.print("Age? ");
        age = in.nextInt(); in.nextLine();
    }
    public void cli() {
        Shelter shelter = new Shelter("Mavs Animal Shelter");
        char choice = 'x';
        while(choice != 'Q') {
            System.out.println("\n\n" + shelter.name() + "\n\nMain Menu\n=========\n"
                + "(L)ist Animals\nNew (D)og\nNew (C)at\n(Q)uit\n\nChoice?");
            choice = Character.toUpperCase(in.nextLine().charAt(0));
            try {
                switch(choice) {
                    case 'L' -> {
                        System.out.println(shelter);
                    }
                    case 'D' -> {
                        for(DogBreed b : DogBreed.values())
                            System.out.println(b.name());
                        getAnimal("dog");
                        shelter.addAnimal(
                            new Dog(DogBreed.valueOf(breed), name, gender, age)
                        );
                   }
                   case 'C' -> {
                        for(CatBreed c : CatBreed.values())
                            System.out.println(c.name());
                        getAnimal("cat");
                        shelter.addAnimal(
                            new Cat(CatBreed.valueOf(breed), name, gender, age)
                        );
                    }
                    case 'Q' -> { }
                    default -> System.err.println("#### Invalid Command\n");
                }
            } catch(Exception e) {
                System.err.println("Invalid input: " + e.getMessage());
            }
        }
    }
}
