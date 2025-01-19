interface AreaCounter{
    double getArea();
}

class Rectangle implements AreaCounter{
    private double width;
    private double height;
    

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
       this.height = height;
    }

    @Override
    public double getArea() {
        return width*height;
    }
}

class Square extends Rectangle {
    private double height;    

    public void setHeight(double height) {
       this.height = height;
    }

    @Override
    public double getArea() {
        return height*height;
    }
}

public class zad3 {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(3);
        rectangle.setWidth(5);

        Square square = new Square();
        square.setHeight(6);

        System.out.println("Pole prostokata: " + rectangle.getArea());
        System.out.println("Pole kwadratu: " + square.getArea());
    }
}