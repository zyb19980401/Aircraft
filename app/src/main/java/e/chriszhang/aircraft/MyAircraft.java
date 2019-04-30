package e.chriszhang.aircraft;



/**
 * The MyAircraft class.
 */
public class MyAircraft extends AirCraft {


    public int getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * The number of enemy crafts killed.
     */
    private int numKilled;

    /**
     * The weapon type of my aircraft. 0 means one bullet, 1 means two bullets, 2 means three bullets
     */
    private int weaponType;

    /**
     * The constructor of the MyAircraft class.
     */
    MyAircraft() {
        super();
        setHeight(200 * getSkyManager().getRate());
        setWidth(200 * getSkyManager().getRate());
        this.setHP(3);
        SetX(getSkyManager().getWidth() / 2 - this.getWidth() / 2);
        SetY(getSkyManager().getHeight() * 0.7f - this.getHeight() / 2);
        new Thread(this).start();
    }

    /**
     * add one to number of enemy crafts killed.
     */
    void addNumKilled() {
        numKilled++;
    }


    @Override
    public void run() {
        while (getSkyManager().isRunning()) {
            super.run();

        }}}