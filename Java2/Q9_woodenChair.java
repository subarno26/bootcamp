package Java2;

public class Q9_woodenChair extends Q9_Chair {
    @Override
    public void stressTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("pases the stress test");
    }
    public String chairType(){
        return "Wooden Chair";
    }
    @Override
    public void fireTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("FAILS Fire Test");
    }
}
