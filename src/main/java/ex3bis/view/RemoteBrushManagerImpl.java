package ex3bis.view;


import ex3bis.view.interfaces.RemoteBrush;
import ex3bis.view.interfaces.RemoteBrushManager;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

public class RemoteBrushManagerImpl implements RemoteBrushManager {
    private static final int BRUSH_SIZE = 10;
    private static final int STROKE_SIZE = 2;
    private List<RemoteBrush> brushes = new java.util.ArrayList<>();

    public void draw(final Graphics2D g) throws RemoteException{
        brushes.forEach(brush -> {
            try {
                g.setColor(new Color(brush.getColor()));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            Ellipse2D.Double circle = null;
            try {
                circle = new Ellipse2D.Double(brush.getX() - BRUSH_SIZE / 2.0, brush.getY() - BRUSH_SIZE / 2.0, BRUSH_SIZE, BRUSH_SIZE);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            // draw the polygon
            g.fill(circle);
            g.setStroke(new BasicStroke(STROKE_SIZE));
            g.setColor(Color.BLACK);
            g.draw(circle);
        });
    }

    public void addBrush(final RemoteBrush brush) {
        brushes.add(brush);
    }

    public void removeBrush(final RemoteBrush brush) {
        brushes.remove(brush);
    }

    @Override
    public List<RemoteBrush>getBrushes() {
        return brushes;
    }

    @Override
    public int getColor() throws RemoteException {
        return 255;
    }

}
