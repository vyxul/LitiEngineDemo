import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.GameListener;

public class GameRunner {
    public static void main(String args[]) {
        Game.init(args);
        Game.start();

        Game.addGameListener(new GameListener() {
            @Override
            public void started() {
                GameListener.super.started();
                System.out.println("Game started");
            }

            @Override
            public void initialized(String... args) {
                GameListener.super.initialized(args);
                System.out.println("Game initialized");
            }

            @Override
            public boolean terminating() {
                System.out.println("Game terminating");
                return GameListener.super.terminating();
            }

            @Override
            public void terminated() {
                GameListener.super.terminated();
                System.out.println("Game terminated");
            }
        });
    }
}
