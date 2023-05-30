package pointOne.actors;

import akka.actor.typed.ActorSystem;
import pointOne.actors.entities.MainActor;
import pointOne.actors.msgs.BootMsg;
import pointOne.actors.msgs.MainActorMsg;
import pointOne.main.AbstractSourceAnalyser;

import java.time.Duration;
import java.time.Instant;

public class ActorsAnalyser extends AbstractSourceAnalyser {
    @Override
    public void getReport(String directory,
                          int ranges,
                          int maxL,
                          int numTopFiles) throws InterruptedException {
        Instant start = Instant.now();
        this.setParameters(directory, ranges, maxL, numTopFiles);

        final ActorSystem<MainActorMsg> bootActor =
                ActorSystem.create(MainActor.create(), "BootActor");
        bootActor.tell(new BootMsg(directory, this));

//        Thread.sleep(20000);

        Instant end = Instant.now();
        System.out.println("ActorsAnalyser, completed in " + Duration.between(start, end).toMillis() + " ms");
    }

    @Override
    public void analyzeSources() throws InterruptedException {

    }

    @Override
    public void stopExecution() {

    }

    @Override
    public void waitStart() {

    }

    @Override
    public void startPressed(String directory,
                             int ranges,
                             int maxL,
                             int numTopFile) {

    }
}
