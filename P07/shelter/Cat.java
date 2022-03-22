package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Cat extends Animal {
    public Cat(CatBreed breed, String name, Gender gender, int age) {
        super(name, gender, age);
        this.breed = breed;
    }
    public Cat() {
        this(CatBreed.Mix, "Default", Gender.female, 0);
    }
    public Cat(BufferedReader br) throws IOException {
        super(br);
        breed = CatBreed.valueOf(br.readLine());
    }
    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
        bw.write(breed.name() + '\n');
    }
    @Override
    public String family() {
        return "cat";
    }
    @Override
    public String breed() {
        return breed.name();
    }
    @Override
    public String toString() {
        return super.toString() + " " + breed.name() + " cat)";
    }
    private CatBreed breed;
}
