package Java2;

class A {
    void print(){
        System.out.println("message");
    }
}
class B {
    public static void main(String[] args) {
        A obj = new A();
        obj.print();
    }
}
