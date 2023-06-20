package ex3.message;

import akka.actor.typed.ActorRef;
import ex2.actorLogic.msgs.ActorBrushManagerInterface;
import ex3.pixelGrid.BrushManager;
import ex3.pixelGrid.PixelGrid;
import ex3.CanvasServer;

import java.io.Serializable;

public class SetupMessage implements Serializable {

    private final PixelGrid grid;
    private final BrushManager brushManager;
    private final int height;
    private final int width;
    public SetupMessage(final PixelGrid grid,
                    final BrushManager brushManager,
                    final int height,
                    final int width,
                    final CanvasServer server) {
        this.grid = grid;
        this.brushManager = brushManager;
        this.height = height;
        this.width = width;
    }
    public PixelGrid getGrid() { return grid; }
    public BrushManager getBrushManager() {
        return brushManager;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }


}
