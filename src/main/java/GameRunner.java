import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.GameListener;
import de.gurkenlabs.litiengine.resources.Resources;
import screens.IngameScreen;

public class GameRunner {
    public static void main(String args[]) {
        // set meta info about the game
        Game.info().setName("Gurk Nukem");
        Game.info().setSubTitle("");
        Game.info().setVersion("v0.0.1");
        Game.info().setWebsite("https://github.com/vyxul/LitiEngineDemo");
        Game.info().setDescription("An example 2D platformer with shooter elements made in the LitiEngine");

        // init the game infrastructure
        Game.init(args);

        // set the icon for the game
        // this has to be done after initialization because the ScreenManager will not be present otherwise
        Game.window().setIcon(Resources.images().get("sprites/icon.png"));
        Game.graphics().setBaseRenderScale(4f);
        Game.screens().add(new IngameScreen());

        // load data from the utiLITI game file
        Resources.load("game.litidata");

        PlayerInput.init();
        GurkNukemLogic.init();

        // load the first level
        // resources for the map were implicitly loaded from the game file
        Game.world().loadEnvironment("level1");
        Game.start();
    }
}
