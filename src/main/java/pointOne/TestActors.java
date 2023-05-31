package pointOne;


import pointOne.actors.ActorsAnalyser;

public class TestActors {

    public static void main(String[] args) {

        ActorsAnalyser analyser = new ActorsAnalyser();
        try {
            analyser.analyzeSources();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
