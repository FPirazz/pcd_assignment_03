package ex3.pixelGrid;

import java.io.Serializable;
import java.rmi.Remote;

public class RemoteBrush extends BrushManager.Brush implements Remote, Serializable {


    public RemoteBrush(int x, int y, int color) {
        super(x, y, color);
    }
}
