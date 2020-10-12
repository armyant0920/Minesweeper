public class Test {
private  static int x=1;

    public void setX(int x) {
        this.x = x;
    }

    public static void main(String[] args) {

        Test t=new Test();
        t.setX(100);
        x=2;
        t.print();

    }
    public void print(){
        System.out.println(x);



    }
}
