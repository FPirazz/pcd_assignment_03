package ex3bis.message;

import ex2.pixelGrid.BrushManager;
import ex2.pixelGrid.PixelGrid;

import java.io.Serializable;

public class UpdateMessage implements Serializable {

    private final PixelGrid grid;
    private final BrushManager brushManager;

    public UpdateMessage(final PixelGrid grid,
                         final BrushManager brushManager) {
        this.grid = grid;
        this.brushManager = brushManager;
    }

    public PixelGrid getGrid() {
        return grid;
    }

    public BrushManager getBrushManager() {
        return brushManager;
    }

}