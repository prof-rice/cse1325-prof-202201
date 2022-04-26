package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Objects;

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
    public void create(Object breed, String name, Gender gender, int age) {
        this.breed = (DogBreed) breed;
        this.name = name;
        this.gender = gender;
        this.age = age;
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
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return breed == dog.breed
            && name.equals(dog.name)
            && gender == dog.gender
            && age == dog.age;
    }
    @Override
    public int hashCode() {
        return Objects.hash(breed, name, gender, age);
    }
    private DogBreed breed;
}
