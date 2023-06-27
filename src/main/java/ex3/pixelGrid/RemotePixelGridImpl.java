package ex3.pixelGrid;

import java.io.Serializable;
import java.rmi.Remote;

public class RemotePixelGridImpl extends PixelGrid implements RemotePixelGrid, Serializable {


    public RemotePixelGridImpl(int nRows, int nColumns) {
        super(nRows, nColumns);
    }
}
