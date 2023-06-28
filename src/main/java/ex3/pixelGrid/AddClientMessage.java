package ex3.pixelGrid;

import ex3.pixelGrid.BrushManager;
import ex3.pixelGrid.RemoteViewImpl;

import java.io.Serializable;

public class AddClientMessage implements Serializable {

    private final String clientID;
    private final RemoteBrush userBrush;
    private final RemoteView userView;

    public AddClientMessage(final String userName,
                       final RemoteBrush userBrush,
                       final RemoteView userView) {
        this.clientID = userName;
        this.userBrush = userBrush;
        this.userView = userView;
    }
    public String getClientID() {
        return clientID;
    }
    public RemoteBrush getUserBrush() { return userBrush; }
    public RemoteView getUserView() {
        return userView;
    }

}
