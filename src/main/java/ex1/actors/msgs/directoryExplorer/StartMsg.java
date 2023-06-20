package ex1.actors.msgs.directoryExplorer;

import akka.actor.typed.ActorRef;
import ex1.actors.ActorsAnalyser;
import ex1.actors.msgs.DirectoryExplorerMsg;
import ex1.actors.msgs.FileReaderMsg;
import ex1.actors.msgs.MainActorMsg;

public class StartMsg implements DirectoryExplorerMsg {
    public final String initDirectory;
    public final ActorRef<FileReaderMsg> fileReader;
    public final ActorsAnalyser analyser;
    public final ActorRef<MainActorMsg> mainActor;
    public final boolean updateGUI;
    public StartMsg(final String initDirectory,
                    final ActorRef<FileReaderMsg> fileReader,
                    final ActorsAnalyser analyser,
                    final ActorRef<MainActorMsg> mainActor,
                    final boolean updateGUI) {
        this.initDirectory = initDirectory;
        this.fileReader = fileReader;
        this.analyser = analyser;
        this.mainActor = mainActor;
        this.updateGUI = updateGUI;
    }

}
