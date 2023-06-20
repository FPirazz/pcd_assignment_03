package ex2.actorLogic.msgs.brush;

import akka.actor.typed.ActorRef;
import ex2.actorLogic.msgs.ActorBrushInterface;
import ex2.actorLogic.msgs.ActorBrushManagerInterface;
import ex2.pixelGrid.BrushManager;
import ex2.pixelGrid.PixelGrid;

public class SetupMsg implements ActorBrushInterface {
    private final PixelGrid grid;
    private final BrushManager brushManager;
    private final int height;
    private final int width;
    private final ActorRef<ActorBrushManagerInterface> mainBrushManagerActor;
    public SetupMsg(final PixelGrid grid,
                    final BrushManager brushManager,
                    final int height,
                    final int width,
                    final ActorRef<ActorBrushManagerInterface> mainBrushManagerActor) {
        this.grid = grid;
        this.brushManager = brushManager;
        this.height = height;
        this.width = width;
        this.mainBrushManagerActor = mainBrushManagerActor;
    }
    public PixelGrid getGrid() {
        return grid;
    }
    public BrushManager getBrushManager() {
        return brushManager;
    }
    public int getHeight() {
        return height;
    }
    public ActorRef<ActorBrushManagerInterface> getMainBrushManagerActor() {
        return mainBrushManagerActor;
    }
    public int getWidth() {
        return width;
    }
}
