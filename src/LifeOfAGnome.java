public class LifeOfAGnome extends ChildOfAliceAndBob implements Runnable{
    public LifeOfAGnome(String num) {
        super("Gnome_" + num);
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
