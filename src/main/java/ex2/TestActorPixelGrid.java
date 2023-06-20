package ex2;

import akka.actor.typed.ActorSystem;
import ex2.actorLogic.entities.MainBrushManagerActor;
import ex2.actorLogic.msgs.ActorBrushManagerInterface;
import ex2.actorLogic.msgs.brushManager.BootMsg;

public class TestActorPixelGrid {
    public static void main(String[] args) {
        ActorSystem<ActorBrushManagerInterface> brushManager = ActorSystem
                .create(MainBrushManagerActor.create(), "BrushManager");
        brushManager.tell(new BootMsg());
    }
}
