package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Objects;

public class GuineaPig extends Animal {
    public GuineaPig(GuineaPigBreed breed, String name, Gender gender, int age) {
        super(name, gender, age);
        this.breed = breed;
    }
    public GuineaPig() {
        this(GuineaPigBreed.Mix, "Default", Gender.female, 0);
    }
    public GuineaPig(BufferedReader br) throws IOException {
        super(br);
        breed = GuineaPigBreed.valueOf(br.readLine());
    }
    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
        bw.write(breed.name() + '\n');
    }
    @Override
    public void create(Object breed, String name, Gender gender, int age) {
        this.breed = (GuineaPigBreed) breed;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    @Override
    public String family() {
        return "guineapig";
    }
    @Override
    public String breed() {
        return breed.name();
    }
    @Override
    public String toString() {
        return super.toString() + " " + breed.name() + " guinea pig)";
    }
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        GuineaPig gp = (GuineaPig) o;
        return breed == gp.breed
            && name.equals(gp.name)
            && gender == gp.gender
            && age == gp.age;
    }
    @Override
    public int hashCode() {
        return Objects.hash(breed, name, gender, age);
    }
    private GuineaPigBreed breed;
}
