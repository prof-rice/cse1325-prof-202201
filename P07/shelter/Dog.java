package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Dog extends Animal {
    public Dog(DogBreed breed, String name, Gender gender, int age) {
        super(name, gender, age);
        this.breed = breed;
    }
    public Dog() {
        this(DogBreed.Mix, "Default", Gender.female, 0);
    }
    public Dog(BufferedReader br) throws IOException {
        super(br);
        breed = DogBreed.valueOf(br.readLine());
    }
    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
        bw.write(breed.name() + '\n');
    }
    @Override
    public String family() {
        return "dog";
    }
    @Override
    public String breed() {
        return breed.name();
    }
    @Override
    public String toString() {
        return super.toString() + " " + breed.name() + " dog)";
    }
    private DogBreed breed;
}
