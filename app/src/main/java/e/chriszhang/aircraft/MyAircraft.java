package e.chriszhang.aircraft;

public class MyAircraft extends  AirCraft {

    int numkilled;

    MyAircraft(){
        setHeight(200 * getSkyManager().getRate());
        setWidth(200 * getSkyManager().getRate());
        this.setHP(5);
//        SetX(getSkyManager().)
    }


}
