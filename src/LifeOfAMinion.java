public class LifeOfAMinion extends ChildOfAliceAndBob implements Runnable{
    public LifeOfAMinion(String num) {
        super("Minion_" + num);
    }

    @Override
    public void run() {
        try {
            wakeUp();
            getLunchFromAlice();
            goToWork();
            goHome();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
