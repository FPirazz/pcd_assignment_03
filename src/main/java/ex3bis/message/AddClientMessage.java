package ex3bis.message;

import ex3bis.pixelGrid.*;

import java.io.Serializable;

public class AddClientMessage implements Serializable {

    private final String clientID;
    private final BrushManager.Brush userBrush;
    private final PixelGridView userView;

    public AddClientMessage(final String userName,
                       final BrushManager.Brush userBrush,
                       final PixelGridView userView) {
        this.clientID = userName;
        this.userBrush = userBrush;
        this.userView = userView;
    }
    public String getClientID() {
        return clientID;
    }
    public BrushManager.Brush getUserBrush() { return userBrush; }
    public PixelGridView getUserView() {
        return userView;
    }

}
