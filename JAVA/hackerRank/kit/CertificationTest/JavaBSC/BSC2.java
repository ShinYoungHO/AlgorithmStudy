package hackerRank.kit.CertificationTest.JavaBSC;

public class BSC2 {
}

class Shape{
    int l,b;
    Shape(int l, int b){
        this.l = l;
        this.b = b;
    }
    void area(){
        System.out.println(l+" "+b);
    }
}
class Rectangle extends Shape{
    Rectangle(int l, int b){
        super(l, b);
    }

    @Override
    void area(){
        System.out.println(l*b);
    }
}