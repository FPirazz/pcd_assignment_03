package ex3bis.message;

import ex3bis.pixelGrid.*;

import java.io.Serializable;

public class SetupMessage implements Serializable {

    private final PixelGrid grid = null;
    private final BrushManager brushManager;
    private final int height;
    private final int width;
    public SetupMessage(final PixelGrid grid,
                    final BrushManager brushManager,
                    final int height,
                    final int width) {
        // this.grid = grid;
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
