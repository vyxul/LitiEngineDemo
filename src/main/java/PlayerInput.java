import de.gurkenlabs.litiengine.input.Input;

import java.awt.event.KeyEvent;

public class PlayerInput {
    private PlayerInput() {
    }

    public static void init() {
        // make the game exit upon pressing ESCAPE
        // by default there is no suck keybinding and the windows needs to be shutdown otherwise, e.g. ALT-F4 on Windows
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
    }
}
