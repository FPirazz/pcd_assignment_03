package ex3.remote;

import ex3.remote.interfaces.RemoteBrushManager;
import ex3.remote.interfaces.RemotePixelGrid;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class VisualiserPanel extends JPanel {
    private static final int STROKE_SIZE = 1;
    private static final int BRUSH_SIZE = 10;

    private final RemoteBrushManager brushManager;
    private final RemotePixelGrid grid;
    private final int w,h;

    public VisualiserPanel(RemotePixelGrid grid, RemoteBrushManager brushManager, int w, int h){
        setSize(w,h);
        this.grid = grid;
        this.w = w;
        this.h = h;
        this.brushManager = brushManager;
        this.setPreferredSize(new Dimension(w, h));
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        try {
            int dx = w / grid.getNumColumns();
            int dy = h / grid.getNumRows();
            g2.setStroke(new BasicStroke(STROKE_SIZE));
            for (int i = 0; i < grid.getNumRows(); i++) {
                int y = i * dy;
                g2.drawLine(0, y, w, y);
            }

            for (int i = 0; i < grid.getNumColumns(); i++) {
                int x = i * dx;
                g2.drawLine(x, 0, x, h);
            }

            for (int row = 0; row < grid.getNumRows(); row++) {
                int y = row * dy;
                for (int column = 0; column < grid.getNumColumns(); column++) {
                    int x = column * dx;
                    int color = grid.get(column, row);
                    if (color != 0) {
                        g2.setColor(new Color(color));
                        g2.fillRect(x + STROKE_SIZE, y + STROKE_SIZE, dx - STROKE_SIZE, dy - STROKE_SIZE);
                    }
                }
            }
            this.drawBrushes(g2);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void drawBrushes(Graphics2D g) throws RemoteException {
        brushManager.getBrushes().forEach(brush -> {
            try {
                g.setColor(new Color(brush.getColor()));
                var circle = new java.awt.geom.Ellipse2D.Double(brush.getX() - BRUSH_SIZE / 2.0, brush.getY() - BRUSH_SIZE / 2.0, BRUSH_SIZE, BRUSH_SIZE);
                // draw the polygon
                g.fill(circle);
                g.setStroke(new BasicStroke(STROKE_SIZE));
                g.setColor(Color.BLACK);
                g.draw(circle);
            }catch (Exception ex){
                System.err.println(ex);
            }
        });
    }
}
