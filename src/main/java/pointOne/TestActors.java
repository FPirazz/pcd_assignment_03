package pointOne;


import akka.actor.typed.ActorSystem;
import pointOne.actors.ActorsAnalyser;
import pointOne.actors.entities.MainActor;
import pointOne.actors.msgs.BootMsg;

public class TestActors {

    public static void main(String[] args) {

        ActorsAnalyser analyser = new ActorsAnalyser();
        try {
            analyser.getReport("C:\\Users\\kryas\\Desktop\\Exercises\\Magistrale\\Pcd-Ricci\\pcd_assignment_03\\input",
            6, 150, 5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
