package ex3.pixelGrid;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RemotePixelGridImpl extends PixelGrid implements RemotePixelGrid, Serializable {


    @Override
    public void set(int x, int y, int color) throws RemoteException {
        super.set(x, y, color);
    }

    @Override
    public int get(int x, int y) throws RemoteException {
        return super.get(x, y);
    }

    @Override
    public int getNumRows() throws RemoteException {
        return super.getNumRows();
    }

    @Override
    public int getNumColumns() throws RemoteException {
        return super.getNumColumns();
    }

    @Override
    public void clear() {
        super.clear();
    }
    public RemotePixelGridImpl(int nRows, int nColumns) {
        super(nRows, nColumns);
    }

}
