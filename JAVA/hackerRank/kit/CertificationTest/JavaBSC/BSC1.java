package hackerRank.kit.CertificationTest.JavaBSC;

abstract class Calculator {
    abstract int add(int a, int b);
}


// my code
public class BSC1 {
    class Adder extends Calculator{
        @Override
        public int add(int a, int b){
            return a+b;
        }
    }
}
