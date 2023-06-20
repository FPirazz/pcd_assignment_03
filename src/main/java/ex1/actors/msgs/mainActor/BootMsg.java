package ex1.actors.msgs.mainActor;

import ex1.actors.ActorsAnalyser;
import ex1.actors.msgs.MainActorMsg;

public class BootMsg implements MainActorMsg {
    public final String initDirectory;
    public final ActorsAnalyser analyser;
    public final boolean updateGUI;
    public BootMsg(final String initDirectory,
                   final ActorsAnalyser analyser,
                   final boolean updateGUI) {
        this.initDirectory = initDirectory;
        this.analyser = analyser;
        this.updateGUI = updateGUI;
    }
}
