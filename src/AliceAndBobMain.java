public class AliceAndBobMain {

    public static void main(String[] args) throws InterruptedException {

        Runnable runner = new HelloWorld();

        // Thread gets passed an object with a run() method to execute, and a string for its name.
        Thread firstThread = new Thread(runner, "Thread 1");
        Thread secondThread = new Thread(runner, "Thread 2");

        // .start() has the thread execute the - overridden - run method
        firstThread.start();
        secondThread.start();

        // .join() means that the main thread will wait for these two threads to finish.
        firstThread.join();
        secondThread.join();

    }
}

class HelloWorld implements Runnable{

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("Hello World " + t.getName());
        System.out.println("Bye World " + t.getName());

    }
}
