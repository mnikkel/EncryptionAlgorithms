abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {
    double a;
    double b;
    double c;

    Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    double getPerimeter() {
        return a + b + c;
    }

    double getArea() {
        return (Math.sqrt((a + b + c) * (-a + b + c) * (a - b + c) * (a + b - c))) / 4;
    }
}

class Rectangle extends Shape {
    double a;
    double b;

    Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    double getPerimeter() {
        return 2 * (a + b);
    }

    double getArea() {
        return a * b;
    }
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    double getArea() {
        return Math.PI * radius * radius;
    }
}
