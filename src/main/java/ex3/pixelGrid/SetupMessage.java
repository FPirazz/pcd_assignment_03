package ex3.pixelGrid;



import ex3.pixelGrid.*;

import java.io.Serializable;

public class SetupMessage implements Serializable {

    private final RemotePixelGrid grid;
    private final RemoteBrushManager brushManager;
    private final int height;
    private final int width;
    public SetupMessage(final RemotePixelGrid grid,
                    final RemoteBrushManager brushManager,
                    final int height,
                    final int width) {
        this.grid = grid;
        this.brushManager = brushManager;
        this.height = height;
        this.width = width;
    }
    public RemotePixelGrid getGrid() { return grid; }
    public RemoteBrushManager getBrushManager() {
        return brushManager;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }


}
