package rings_of_saturn.github.io.reactive_skins.util;

public class TimeUtil {
    public static int minutesInTicks(int minutes, boolean adjust){
        return adjust ? (secondsInTicks(minutes, false)*60)+5 : (secondsInTicks(minutes, false)*60);
    }

    public static int secondsInTicks(int seconds, boolean adjust){
        return adjust ? (seconds*20)+5 : (seconds*20);
    }

    public static int tickTimer(int timer){
        return Math.max(timer-1,0);
    }
}
