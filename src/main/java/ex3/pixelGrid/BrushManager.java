package ex3.pixelGrid;


import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public class BrushManager {
    private static final int BRUSH_SIZE = 10;
    private static final int STROKE_SIZE = 2;
    private List<RemoteBrush> brushes = new java.util.ArrayList<RemoteBrush>();

    void draw(final Graphics2D g) throws RemoteException {
        brushes.forEach(brush -> {

            g.setColor(new Color(((RemoteBrushImpl) brush).color));
            var circle = new java.awt.geom.Ellipse2D.Double(
                    ((RemoteBrushImpl) brush).x - BRUSH_SIZE / 2.0,
                    ((RemoteBrushImpl) brush).y - BRUSH_SIZE / 2.0,
                    BRUSH_SIZE, BRUSH_SIZE);
            // draw the polygon
            g.fill(circle);
            g.setStroke(new BasicStroke(STROKE_SIZE));
            g.setColor(Color.BLACK);
            g.draw(circle);
        });
    }

    public void addBrush(final RemoteBrush brush) throws RemoteException {
        brushes.add(brush);
    }

    public void removeBrush(final Brush brush) throws RemoteException {
        brushes.remove(brush);
    }

    public static class Brush {
        protected int x, y;
        protected int color;

        public Brush(final int x, final int y, final int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public void updatePosition(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
        // write after this getter and setters
        public int getX(){
            return this.x;
        }
        public int getY(){
            return this.y;
        }
        public int getColor(){
            return this.color;
        }
        public void setColor(int color){
            this.color = color;
        }
    }
}
