package rings_of_saturn.github.io.reactive_skins.util;

import rings_of_saturn.github.io.reactive_skins.client.ReactiveSkinsClient;
import rings_of_saturn.github.io.reactive_skins.player.data.PlayerData;

public class TimeUtil {
    public static final int adjustAmount = 5;
    public static final int refreshAmount = adjustAmount;

    public static int minutesInTicks(int minutes, boolean adjust){
        return adjust ? (secondsInTicks(minutes, false)*60)+adjustAmount : (secondsInTicks(minutes, false)*60);
    }

    public static int secondsInTicks(int seconds, boolean adjust){
        return adjust ? (seconds*20)+adjustAmount : (seconds*20);
    }

    public static int tickTimer(int timer){
        return Math.max(timer-1,0);
    }

    public static void resetRefreshTimer(PlayerData data){
        ReactiveSkinsClient.LOGGER.info("aaaaaaaaaa");
        data.refreshTimer = refreshAmount;
    }
}
