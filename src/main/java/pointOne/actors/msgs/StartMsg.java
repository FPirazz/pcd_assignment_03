package pointOne.actors.msgs;

import akka.actor.Actor;
import akka.actor.typed.ActorRef;
import com.sun.tools.javac.Main;
import pointOne.actors.ActorsAnalyser;
import pointOne.actors.entities.MainActor;

public class StartMsg implements DirectoryExplorerMsg{
    public final String initDirectory;
    public final ActorRef<FileReaderMsg> fileReader;
    public final ActorsAnalyser analyser;
    public final ActorRef<MainActorMsg> mainActor;
    public StartMsg(final String initDirectory,
                    final ActorRef<FileReaderMsg> fileReader,
                    final ActorsAnalyser analyser,
                    final ActorRef<MainActorMsg> mainActor) {
        this.initDirectory = initDirectory;
        this.fileReader = fileReader;
        this.analyser = analyser;
        this.mainActor = mainActor;
    }

}
