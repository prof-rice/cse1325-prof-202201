package shelter;

import java.util.ArrayList;

public class Shelter {
    String name;
    ArrayList<Animal> animals;
    public Shelter(String name) {
        this.name = name;
        animals = new ArrayList<>();
    }
    public String name() {
        return name;
    }
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }
    public int numAnimals() {
        return animals.size();
    }
    public Animal getAnimal(int index) {
        return animals.get(index);
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Animal a : animals) {
            result.append((first ? "" : "\n") + a);
            first = false;
        }
        return result.toString();
    }
}
