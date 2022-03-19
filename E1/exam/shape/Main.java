public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[] {
            new Rectangle(3, 4),
            new Square(3.5),
            new Circle(4.2)
        };
        for(Shape s : shapes) System.out.println(s.getArea());
    }
}
