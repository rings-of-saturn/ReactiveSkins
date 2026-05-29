package rings_of_saturn.github.io.reactive_skins.player.data;

import static rings_of_saturn.github.io.reactive_skins.util.TimeUtil.refreshAmount;

public class PlayerData {
    public boolean bedrockDust;
    public int snowTimer;
    public int mudTimer;
    public int refreshTimer = refreshAmount;

    public PlayerData(boolean bedrockDust, int snowTimer, int mudTimer){
        this.bedrockDust = bedrockDust;
        this.snowTimer = snowTimer;
        this.mudTimer = mudTimer;
    }
}
