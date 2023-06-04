package pointTwo.actorLogic.utilities;

import java.util.Random;

public class Utilities {
    public static int randomColor() {
        Random rand = new Random();
        return rand.nextInt(256 * 256 * 256);
    }
}
