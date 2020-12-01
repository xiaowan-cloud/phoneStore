public class HelloWord implements Runnable{

    @Override
    public void run() {
        if(Thread.currentThread().getName()=="Hello "){
            System.out.print(Thread.currentThread().getName());
        }else {
            try {
                System.out.print("必须先输出hello ");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(Thread.currentThread().getName());
        }

    }

    public static void main(String[] args) {
        Runnable r1 = new HelloWord();
        Runnable r2=new HelloWord();

        Thread t1=new Thread(r1);
        Thread t2=new Thread(r2);
        t1.setName("Hello ");
        t2.setName("World");
        t1.start();
        t2.start();
    }
}
