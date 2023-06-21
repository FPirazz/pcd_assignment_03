package ex3.message;

import ex3.pixelGrid.BrushManager;
import ex3.pixelGrid.PixelGridView;
import ex3.pixelGrid.RemoteView;

import java.io.Serializable;

public class AddClientMessage implements Serializable {

    private final String clientID;
    private final BrushManager.Brush userBrush;
    private final RemoteView userView;

    public AddClientMessage(final String userName,
                       final BrushManager.Brush userBrush,
                       final RemoteView userView) {
        this.clientID = userName;
        this.userBrush = userBrush;
        this.userView = userView;
    }
    public String getClientID() {
        return clientID;
    }
    public BrushManager.Brush getUserBrush() { return userBrush; }
    public RemoteView getUserView() {
        return userView;
    }

}
