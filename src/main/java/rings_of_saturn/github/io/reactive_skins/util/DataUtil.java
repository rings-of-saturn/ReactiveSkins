package rings_of_saturn.github.io.reactive_skins.util;

import net.minecraft.block.Blocks;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import rings_of_saturn.github.io.reactive_skins.player.data.PlayerData;

import java.util.HashMap;

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
        getPlayerData(player).mudTimer = TimeUtil.tickTimer(getPlayerData(player).mudTimer);
        getPlayerData(player).mudTimer = TimeUtil.tickTimer(getPlayerData(player).snowTimer);
    }

    public static void updatePlayerData(AbstractClientPlayerEntity player){
        //bedrock
        if(player.getBlockPos().getY() < -50){
            getPlayerData(player).bedrockDust = true;
        }
        if (player.getBlockPos().getY() == -45) {
            getPlayerData(player).bedrockDust = false;
        }

        //snow
        if(!player.getWorld().getBiome(player.getBlockPos()).value().doesNotSnow(player.getBlockPos()) && player.getWorld().isRaining() && player.getWorld().isSkyVisible(player.getBlockPos())){
            getPlayerData(player).snowTimer = TimeUtil.minutesInTicks(2);
        }

        //mud
        if(player.getSteppingBlockState().getBlock() == Blocks.MUD || player.getSteppingBlockState().getBlock() == Blocks.MUDDY_MANGROVE_ROOTS){
            getPlayerData(player).mudTimer = TimeUtil.secondsInTicks(30);
        }

        if(player.getSteppingBlockState().getBlock() == Blocks.WATER || player.getSteppingBlockState().getBlock() == Blocks.WATER_CAULDRON){
            getPlayerData(player).mudTimer = 0;
        }
    }
}
