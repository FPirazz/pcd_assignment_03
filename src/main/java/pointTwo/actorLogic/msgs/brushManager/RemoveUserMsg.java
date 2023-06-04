package pointTwo.actorLogic.msgs.brushManager;

import pointTwo.actorLogic.msgs.ActorBrushManagerInterface;

public class RemoveUserMsg implements ActorBrushManagerInterface {
    private final String name;
    public RemoveUserMsg(final String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
