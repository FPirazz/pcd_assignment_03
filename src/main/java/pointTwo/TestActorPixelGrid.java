package pointTwo;

import akka.actor.Actor;
import akka.actor.typed.ActorSystem;
import pointTwo.actorLogic.entities.MainBrushManagerActor;
import pointTwo.actorLogic.msgs.ActorBrushManagerInterface;
import pointTwo.actorLogic.msgs.brushManager.BootMsg;

public class TestActorPixelGrid {
    public static void main(String[] args) {
        ActorSystem<ActorBrushManagerInterface> brushManager = ActorSystem
                .create(MainBrushManagerActor.create(), "BrushManager");
        brushManager.tell(new BootMsg());
    }
}
