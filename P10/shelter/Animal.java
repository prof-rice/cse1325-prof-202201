package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Animal {
    public Animal(String name, Gender gender, int age) {
        if(age<0) throw new IllegalArgumentException(name + ": Age must be positive");
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    public Animal(BufferedReader br) throws IOException {
        name = br.readLine();
        gender = Gender.valueOf(br.readLine());
        age = Integer.parseInt(br.readLine());
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(gender.name() + '\n');
        bw.write("" + age + '\n');
    }
    public abstract void create(Object breed, String name, Gender gender, int age);
    public abstract String family();
    public abstract String breed();
    public String name() {return name;}
    public Gender gender() {return gender;}
    public int age() {return age;}
    public String toString() {
        return name + " (" + age + " year old " + gender;
    }
    protected String name;
    protected Gender gender;
    protected int age;
}
