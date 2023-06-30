package ex3bis.view;


import ex3bis.view.interfaces.RemoteBrush;
import ex3bis.view.interfaces.RemoteBrushManager;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteBrushManagerImpl implements RemoteBrushManager {
    private static final int BRUSH_SIZE = 10;
    private static final int STROKE_SIZE = 2;
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

}
