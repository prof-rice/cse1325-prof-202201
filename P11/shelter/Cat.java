package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Objects;

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
    public void create(Object breed, String name, Gender gender, int age) {
        this.breed = (CatBreed) breed;
        this.name = name;
        this.gender = gender;
        this.age = age;
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
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return breed == cat.breed
            && name.equals(cat.name)
            && gender == cat.gender
            && age == cat.age;
    }
    @Override
    public int hashCode() {
        return Objects.hash(breed, name, gender, age);
    }
    private CatBreed breed;
}
