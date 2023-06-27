package ex3.pixelGrid;

import ex3.pixelGrid.BrushManager;
import ex3.pixelGrid.RemoteViewImpl;

import java.io.Serializable;

public class AddClientMessage implements Serializable {

    private final String clientID;
    private final BrushManager.Brush userBrush;
    private final RemoteViewImpl userView;

    public AddClientMessage(final String userName,
                       final BrushManager.Brush userBrush,
                       final RemoteViewImpl userView) {
        this.clientID = userName;
        this.userBrush = userBrush;
        this.userView = userView;
    }
    public String getClientID() {
        return clientID;
    }
    public BrushManager.Brush getUserBrush() { return userBrush; }
    public RemoteViewImpl getUserView() {
        return userView;
    }

}
