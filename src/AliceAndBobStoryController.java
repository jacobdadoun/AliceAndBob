import java.util.*;
import java.util.concurrent.SynchronousQueue;

public class AliceAndBobStoryController implements Runnable{


    private static Thread[] minions;
    private static Thread[] gnomes;
    private static Thread alice;
    private static Thread bobThread;

    static public List<ChildOfAliceAndBob> listOfChars = Collections.synchronizedList(new ArrayList<>());
    static public int numberOfChildrenLeftHome;
    static public AbstractQueue<LifeOfAGnome> gnomesArrivedHome = new SynchronousQueue<>();


    public AliceAndBobStoryController(int numOfMinions, int numOfGnomes){
        minions = new Thread[numOfMinions];
        gnomes = new Thread[numOfGnomes];
        alice = new Thread(new LifeOfAlice());
    }

    @Override
    public void run() {
        try {
            startTheMorning();

            minionsGetsHome();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void minionsGetsHome() {

    }

    public void startTheMorning() throws InterruptedException {
        for(int i = 0; i < minions.length; i++){
            if(i < gnomes.length){
                gnomes[i] = new Thread(new LifeOfAGnome(Integer.toString(i)));
            }
            minions[i] = new Thread(new LifeOfAMinion(Integer.toString(i)));
        }

        for(int i = 0; i < minions.length; i++){
            if(i < gnomes.length){
                gnomes[i].start();
            }
            minions[i].start();
        }



        LifeOfAlice.pauseAlice();

        if (numberOfChildrenLeftHome == 0){
            bobThread = LifeOfAlice.yellAtBobToWakeUp();
            bobThread.start(); // Bob starts his day
        }

    }

    private void endOfTheDay(){
        try{
            for(int i = 0; i < 10; i++){
                if(i < 7){
                    gnomes[i].join();
                }
                minions[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        AliceAndBobStoryController newStory = new AliceAndBobStoryController(10, 7);

        try {
            newStory.startTheMorning();

            System.out.println("|  CHILD  |  STATUS  | AT HOME  |");
            for (ChildOfAliceAndBob child : listOfChars) {
                System.out.printf(" %s  + %s  + %s %n%n", child.getNameOfChild(), child.getStatus(), child.isHome());
            }

            //...
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
