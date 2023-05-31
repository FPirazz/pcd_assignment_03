package pointOne.actors.msgs.mainActor;

import pointOne.actors.ActorsAnalyser;
import pointOne.actors.msgs.MainActorMsg;
import pointOne.main.view.AnalyserView;

import java.util.Optional;

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
