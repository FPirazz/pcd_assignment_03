package ex3.view;


import ex3.view.interfaces.RemoteBrush;
import ex3.view.interfaces.RemoteBrushManager;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteBrushManagerImpl implements RemoteBrushManager {

    private Map<String, RemoteBrush> brushes = new HashMap<>();

    public void addBrush(final String clientID, final RemoteBrush brush) {
        brushes.put(clientID, brush);
    }

    @Override
    public void removeBrush(String clientID) throws RemoteException {
        brushes.remove(clientID);
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
    }

}
