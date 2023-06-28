package ex3.pixelGrid;

import java.awt.*;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RemoteBrushManagerImpl extends BrushManager implements RemoteBrushManager, Serializable {

    public RemoteBrushManagerImpl() {
        super();
    }

    @Override
    public void addBrush(RemoteBrush userBrush) throws RemoteException {
        super.addBrush(userBrush);
    }

    @Override
    public void draw(Graphics2D g) throws RemoteException {
        super.draw(g);
    }
    @Override
    public void removeBrush(Brush brush) throws RemoteException {
        super.removeBrush(brush);
    }
}
