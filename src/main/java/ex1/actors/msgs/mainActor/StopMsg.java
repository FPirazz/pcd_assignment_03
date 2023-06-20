package ex1.actors.msgs.mainActor;

import ex1.actors.ActorsAnalyser;
import ex1.actors.msgs.MainActorMsg;

public class StopMsg implements MainActorMsg {
    public ActorsAnalyser analyser;
    public StopMsg(final ActorsAnalyser analyser) {
        this.analyser = analyser;
    }
}
