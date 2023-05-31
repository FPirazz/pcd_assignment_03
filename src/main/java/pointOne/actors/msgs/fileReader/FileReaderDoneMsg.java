package pointOne.actors.msgs.fileReader;

import akka.actor.typed.ActorRef;
import pointOne.actors.msgs.DirectoryExplorerMsg;
import pointOne.actors.msgs.FileReaderMsg;

public class FileReaderDoneMsg implements FileReaderMsg {
    public final ActorRef<DirectoryExplorerMsg> directoryExplorer;
    public FileReaderDoneMsg(final ActorRef<DirectoryExplorerMsg> directoryExplorer) {
        this.directoryExplorer = directoryExplorer;
    }
}
