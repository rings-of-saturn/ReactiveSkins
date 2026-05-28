package rings_of_saturn.github.io.reactive_skins.util;

public class TimeUtil {
    public static int minutesInTicks(int minutes){
        return secondsInTicks(minutes)*60;
    }

    public static int secondsInTicks(int seconds){
        return seconds*20;
    }

    public static int tickTimer(int timer){
        if(timer-- <= 0)
            return timer;
        else
            return timer-1;
    }
}
