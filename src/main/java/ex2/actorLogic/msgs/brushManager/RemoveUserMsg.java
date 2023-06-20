package ex2.actorLogic.msgs.brushManager;

import ex2.actorLogic.msgs.ActorBrushManagerInterface;

public class RemoveUserMsg implements ActorBrushManagerInterface {
    private final String name;
    public RemoveUserMsg(final String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
