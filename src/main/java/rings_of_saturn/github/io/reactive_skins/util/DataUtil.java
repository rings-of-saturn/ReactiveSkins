package rings_of_saturn.github.io.reactive_skins.util;

import net.minecraft.block.Blocks;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import rings_of_saturn.github.io.reactive_skins.player.data.PlayerData;

import java.util.HashMap;

import static rings_of_saturn.github.io.reactive_skins.util.TimeUtil.refreshAmount;
import static rings_of_saturn.github.io.reactive_skins.util.TimeUtil.resetRefreshTimer;

public class DataUtil {
    public static final HashMap<PlayerEntity, PlayerData> playerDataMap = new HashMap<>();
    public static final PlayerData defaultData =
            new PlayerData(
                    false,
                    0,
                    0
            );

    public static PlayerData getPlayerData(PlayerEntity player){
        if(!playerDataMap.containsKey(player)) {
            playerDataMap.put(player, defaultData);
        }
        return playerDataMap.get(player);
    }

    public static void tickTimers(AbstractClientPlayerEntity player){
        PlayerData data = getPlayerData(player);
        //refresh
        if(
                        data.mudTimer == refreshAmount ||
                        data.snowTimer == refreshAmount
        ) {
            resetRefreshTimer(data);
        }


        data.mudTimer = TimeUtil.tickTimer(data.mudTimer);
        data.snowTimer = TimeUtil.tickTimer(data.snowTimer);
        data.refreshTimer = TimeUtil.tickTimer(data.refreshTimer);
    }

    public static void updatePlayerData(AbstractClientPlayerEntity player){
        PlayerData data = getPlayerData(player);
        tickTimers(player);

        //bedrock
        if(player.getBlockPos().getY() < -50){
            data.bedrockDust = true;
        }
        if (player.getBlockPos().getY() >= -45 && data.bedrockDust) {
            resetRefreshTimer(data);
            data.bedrockDust = false;
        }

        //snow
        if(!player.getWorld().getBiome(player.getBlockPos()).value().doesNotSnow(player.getBlockPos()) && player.getWorld().isRaining() && player.getWorld().isSkyVisible(player.getBlockPos())){
            data.snowTimer = TimeUtil.minutesInTicks(2, true);
        }

        //mud
        if(player.getSteppingBlockState().getBlock() == Blocks.MUD || player.getSteppingBlockState().getBlock() == Blocks.MUDDY_MANGROVE_ROOTS){
            data.mudTimer = TimeUtil.secondsInTicks(30, true);
        }

        if(player.getSteppingBlockState().getBlock() == Blocks.WATER || player.getSteppingBlockState().getBlock() == Blocks.WATER_CAULDRON){
            data.mudTimer = 0;
            resetRefreshTimer(data);
        }
    }
}
