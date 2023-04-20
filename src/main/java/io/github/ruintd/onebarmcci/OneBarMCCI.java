package io.github.ruintd.onebarmcci;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.world.ClientWorld;

@Environment(EnvType.CLIENT)
public class OneBarMCCI implements ClientModInitializer {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    private final io.github.madis0.ModConfig oneBarConfig =
            AutoConfig.getConfigHolder(io.github.madis0.ModConfig.class).getConfig();

    private boolean active = false;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Logic to prevent the code from triggering while
            // OneBar is disabled
            if (!active && disableBar() && oneBarConfig.showOneBar) {
                active = true;
                oneBarConfig.showOneBar = false;
            } else if (active && !disableBar()) {
                active = false;
                oneBarConfig.showOneBar = true;
            }
        });
    }

    public static boolean isOnMCCI() {
        ServerInfo server = client.getCurrentServerEntry();
        if (server == null)
            return false;
        return server.address.endsWith("mccisland.net");
    }

    public static boolean disableBar() {
        if (!isOnMCCI())
            return false;

        ClientWorld world = client.world;
        if (world == null)
            return false;

        return world.getLevelProperties().isHardcore();
    }
}
