package Java2;

public class Q9_woodenTable extends Q9_table {
    @Override
    public void stressTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("passes the stress test");
    }

    @Override
    public void fireTest() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("failed the fire test");
    }

    @Override
    public String tableType() {
        //To change body of implemented methods use File | Settings | File Templates.
        String s = "This is a wooden Table";
        return s;
    }
}