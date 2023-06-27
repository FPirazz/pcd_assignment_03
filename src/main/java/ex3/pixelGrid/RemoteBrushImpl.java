package ex3.pixelGrid;

import java.io.Serializable;
import java.rmi.Remote;

public class RemoteBrushImpl extends BrushManager.Brush implements RemoteBrush, Serializable {


    public RemoteBrushImpl(int x, int y, int color) {
        super(x, y, color);
    }
}
