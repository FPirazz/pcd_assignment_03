package ex1.actors;

import akka.Done;
import akka.actor.typed.ActorSystem;
import akka.dispatch.OnComplete;
import ex1.actors.entities.MainActor;
import ex1.actors.msgs.mainActor.BootMsg;
import ex1.actors.msgs.MainActorMsg;
import ex1.main.AbstractSourceAnalyser;
import ex1.main.view.AnalyserView;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class ActorsAnalyser extends AbstractSourceAnalyser {
    private ActorSystem<MainActorMsg> bootActor;
    @Override
    public void getReport(String directory,
                          int ranges,
                          int maxL,
                          int numTopFiles) throws InterruptedException {
        Instant start = Instant.now();
        this.setParameters(directory, ranges, maxL, numTopFiles);
        ActorsAnalyser context = this;

        bootActor = ActorSystem.create(MainActor.create(context, Optional.empty()), "BootActor");
        bootActor.tell(new BootMsg(directory, this, false));



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
        view = new AnalyserView(this);
        view.display();
        ActorsAnalyser context = this;

        while(true) {
            bootActor = ActorSystem.create(MainActor.create(context, Optional.of(view)), "BootActor");

            waitStart();

            bootActor.tell(new BootMsg(initialDirectory, this, true));
            Instant start = Instant.now();

            bootActor.whenTerminated().onComplete(new OnComplete<Done>() {
                @Override
                public void onComplete(Throwable failure, Done success) {
                    Instant end = Instant.now();
                    view.changeState("ActorsAnalyser, completed in " + Duration.between(start, end).toMillis() + " ms");
                }
            }, bootActor.executionContext());
        }
    }

    @Override
    public void stopExecution() {
        bootActor.terminate();
        view.changeState("Stopped");
    }

    @Override
    public void waitStart() {
        synchronized(this) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        resetTopFiles();
        resetIntervals();
    }

    @Override
    public void startPressed(String directory,
                             int ranges,
                             int maxL,
                             int numTopFile) {
        this.initialDirectory = directory;
        this.ranges = ranges;
        this.maxL = maxL;
        this.numTopFiles = numTopFile;
        synchronized (this) {
            notifyAll();
        }
    }
}
