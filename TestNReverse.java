public class TestNReverse {
    public static void main(String[] args){
        LinkedList list = new LinkedList();

        list.addAValue("1");
        list.addAValue("2");
        list.addAValue("3");
        list.addAValue("4");
        list.addAValue("5");
        list.addAValue("6");
        list.addAValue("7");

        System.out.println("original list: " + list.showValues());
        list.nReverse(3);
        System.out.println("nReverse(3) list: " + list.showValues());

    }
}
