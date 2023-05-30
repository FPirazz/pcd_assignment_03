package pointOne;


import akka.actor.typed.ActorSystem;
import pointOne.actors.entities.MainActor;
import pointOne.actors.msgs.BootMsg;

public class TestActors {

    public static void main(String[] args) {
        final ActorSystem<BootMsg> bootActor =
                ActorSystem.create(MainActor.create(), "BootActor");
        bootActor.tell(new BootMsg("C:\\Users\\kryas\\Desktop\\Exercises\\Magistrale\\Pcd-Ricci\\pcd_assignment_03\\input"));
    }

}
