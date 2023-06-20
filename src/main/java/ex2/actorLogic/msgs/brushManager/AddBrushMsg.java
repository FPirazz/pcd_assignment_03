package ex2.actorLogic.msgs.brushManager;

import ex2.actorLogic.msgs.ActorBrushManagerInterface;
import ex2.pixelGrid.BrushManager;
import ex2.pixelGrid.PixelGridView;

public class AddBrushMsg implements ActorBrushManagerInterface {
    private final String userName;
    private final BrushManager.Brush userBrush;
    private final PixelGridView userView;

    public AddBrushMsg(final String userName,
                       final BrushManager.Brush userBrush,
                       final PixelGridView userView) {
        this.userName = userName;
        this.userBrush = userBrush;
        this.userView = userView;
    }
    public String getUserName() {
        return userName;
    }
    public BrushManager.Brush getUserBrush() {
        return userBrush;
    }
    public PixelGridView getUserView() {
        return userView;
    }
}
