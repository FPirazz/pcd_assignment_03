package pointOne.actors;

import akka.Done;
import akka.actor.typed.ActorSystem;
import akka.dispatch.OnComplete;
import pointOne.actors.entities.MainActor;
import pointOne.actors.msgs.mainActor.BootMsg;
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

        ActorsAnalyser context = this;

        bootActor.whenTerminated().onComplete(new OnComplete<Done>() {
            @Override
            public void onComplete(Throwable failure, Done success) {
                context.printTopFiles(context.topFiles);
                context.printIntervals(context.intervals);

                Instant end = Instant.now();
                System.out.println("ActorsAnalyser, completed in " + Duration.between(start, end).toMillis() + " ms");
            }
        }, bootActor.executionContext());

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
