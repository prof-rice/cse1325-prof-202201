package shelter;

public class Cat extends Animal {
    public Cat(CatBreed breed, String name, Gender gender, int age) {
        super(name, gender, age);
        this.breed = breed;
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
