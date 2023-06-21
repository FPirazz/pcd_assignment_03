package ex3.pixelGrid;

import java.io.Serializable;
import java.rmi.Remote;

public class RemotePixelGrid extends PixelGrid implements Remote, Serializable {


    public RemotePixelGrid(int nRows, int nColumns) {
        super(nRows, nColumns);
    }
}
