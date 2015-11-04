package us.newberg.bullet;

public class Subroutines extends Opmode {
    double wheel1;
    double wheel1m1;
    double wheel1m2;
    double wheel2;
    double wheel2m1;
    double wheel2m2;
    double wheel1circum;
    double wheel2circum;
    double revolution;
    public void powerratio(wheel1,wheel1m1,wheel1m2,wheel2,wheel2m1,wheel2m2){
        wheel1circum = wheel1 * 3.1415;
        wheel2circum = wheel2 * 3.1415;
        revolution = wheel1circum / wheel2circum;
        wheel1m1.setpower()


    }
}
