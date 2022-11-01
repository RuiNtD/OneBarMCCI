package io.github.faynealdan.onebarmcci;

import dev.andante.mccic.api.client.game.GameTracker;
import dev.andante.mccic.api.game.GameState;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.world.ClientWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class OneBarMCCI implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("onebarmcci");
    private static final MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {}

    public static boolean showBar() {
        if (!GameTracker.INSTANCE.isOnServer())
            return true;

        ClientWorld world = client.world;
        if (world != null && world.getLevelProperties().isHardcore())
            return false;
        
        GameState state = GameTracker.INSTANCE.getGameState();
        if (state == GameState.ACTIVE || state == GameState.WAITING_FOR_GAME)
            return true;

        return false;
    }
}
