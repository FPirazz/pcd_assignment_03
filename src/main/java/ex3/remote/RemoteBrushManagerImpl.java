package ex3.remote;


import ex3.remote.interfaces.LocalCanvas;
import ex3.remote.interfaces.RemoteBrush;
import ex3.remote.interfaces.RemoteBrushManager;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteBrushManagerImpl implements RemoteBrushManager {

    private Map<String, RemoteBrush> brushes = new HashMap<>();
    private List<LocalCanvas> canvasesList = new ArrayList<>();


    @Override
    public void addBrush(String clientID, RemoteBrush brush) throws RemoteException {
        brushes.put(clientID, brush);
        sendNotification();
    }

    @Override
    public void removeBrush(String clientID) throws RemoteException {
        brushes.remove(clientID);
        sendNotification();
    }

    @Override
    public List<RemoteBrush>getBrushes() {
        return brushes.values().stream().toList();
    }

    @Override
    public int getColor(String clientID) throws RemoteException {
        return brushes.get(clientID).getColor();
    }

    @Override
    public void updateBrushPosition(String clientID, int x, int y) throws RemoteException {
        brushes.get(clientID).updatePosition(x, y);
        this.sendNotification();
    }

    @Override
    public void addCanvas(LocalCanvas canvas) throws RemoteException {
        this.canvasesList.add(canvas);
    }

    private void sendNotification() {
        canvasesList.forEach(c -> {
            try {
                c.notifyChange();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
