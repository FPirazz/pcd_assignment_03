package pointOne.actors.msgs;

import akka.actor.typed.ActorRef;

public class StartMsg implements DirectoryExplorerMsg{
    public final String initDirectory;
    public final ActorRef<FileReaderMsg> fileReader;
    public StartMsg(final String initDirectory, final ActorRef<FileReaderMsg> fileReader) {
        this.initDirectory = initDirectory;
        this.fileReader = fileReader;
    }

}
