package pointTwo.actorLogic.msgs.brushManager;

import akka.actor.typed.ActorRef;
import pointTwo.actorLogic.msgs.ActorBrushInterface;
import pointTwo.actorLogic.msgs.ActorBrushManagerInterface;
import pointTwo.pixelGrid.BrushManager;
import pointTwo.pixelGrid.PixelGridView;

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
