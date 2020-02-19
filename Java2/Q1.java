package Java2;
//1. Create Java classes having suitable attributes for Library management system.
// Use OOPs concepts in your design.Also try to use interfaces and abstract classes.
public class Q1 {
    public static void main(String[] args) {
        Q1_student s1 = new Q1_student("Subarno", 6, 7 , "IT");
        s1.issueBook(8,"introduction to java");
        s1.getDetails();
        s1.issueBook(9,"Programming with C++");
        s1.getDetails();

    }
}
