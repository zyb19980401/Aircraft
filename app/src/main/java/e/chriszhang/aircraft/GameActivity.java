package e.chriszhang.aircraft;

public class GameActivity {


    /**
     * the SkyManager of the current game;
     */
    private static SkyManager skyManager = new SkyManager();


    /**
     * @return the current skymanger
     */
    public static SkyManager getSkyManager() {
        return skyManager;
    }
}
