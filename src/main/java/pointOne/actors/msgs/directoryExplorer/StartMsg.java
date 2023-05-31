package pointOne.actors.msgs.directoryExplorer;

import akka.actor.typed.ActorRef;
import pointOne.actors.ActorsAnalyser;
import pointOne.actors.msgs.DirectoryExplorerMsg;
import pointOne.actors.msgs.FileReaderMsg;
import pointOne.actors.msgs.MainActorMsg;

public class StartMsg implements DirectoryExplorerMsg {
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
