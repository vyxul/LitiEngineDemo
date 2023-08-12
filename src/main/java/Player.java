import abilities.Jump;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import de.gurkenlabs.litiengine.physics.Collision;
import de.gurkenlabs.litiengine.physics.IMovementController;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@EntityInfo(width = 18, height = 18)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
public class Player extends Creature implements IUpdateable {
    public static final int MAX_ADDITIONAL_JUMPS = 2;

    private static Player instance;
    private final Jump jump;
    private int consecutiveJumps;

    public static Player instance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    private Player() {
        super("gurknukem");

        // setup the player's abilities
        this.jump = new Jump(this);
    }

    @Override
    public void update() {
        // reset the number of consecutive jumps when touching the ground
        if (this.isTouchingGround()) {
            this.consecutiveJumps = 0;
        }
    }

    @Override
    protected IMovementController createMovementController() {
        // setup movement controller
        return new PlatformingMovementController<>(this);
    }

    /**
     * Checkes where this instance can currently jump and then performs the Jump ability.
     *
     * Note that the name of this needs to be equal to {@Link PlatformingMovementController#JUMP} in order for the
     * controller to be able to use this method.
     * Another option is to explicitly specify the Action.name() attribute on the annotation.
     */
    @Action(description = "This performs the jump ability for the player's entity.")
    public void jump() {
        if (this.consecutiveJumps >= MAX_ADDITIONAL_JUMPS || !this.jump.canCast()) {
            return;
        }

        this.jump.cast();
        this.consecutiveJumps++;
    }

    private boolean isTouchingGround() {
        //The idea of this ground check is to extend the current collision box by one pixel and see if
        // a) and see if it collides with any static collision box
        Rectangle2D groundCheck = new Rectangle2D.Double(this.getCollisionBox().getX(),
                this.getCollisionBox().getY(), this.getCollisionBoxWidth(), this.getCollisionBoxHeight() + 1);
        // b) it collides with the map's boundaries
        if (groundCheck.getMaxY() > Game.physics().getBounds().getMaxY()) {
            return true;
        }

        return Game.physics().collides(groundCheck, Collision.STATIC);
    }
}
