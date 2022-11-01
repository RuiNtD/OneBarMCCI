package io.github.faynealdan.onebarmcci;

import dev.andante.mccic.api.client.game.GameTracker;
import dev.andante.mccic.api.game.GameState;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.world.ClientWorld;

@Environment(EnvType.CLIENT)
public class OneBarMCCI implements ClientModInitializer {
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
        if (state == GameState.ACTIVE)
            return true;

        return false;
    }
}
