package net.dbva.wrath.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;

public class PlayerUtils {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static int getPing() {
        if (mc.getNetworkHandler() == null) return 0;

        assert mc.player != null;
        PlayerListEntry playerListEntry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
        if (playerListEntry == null) return 0;
        return playerListEntry.getLatency();
    }
}
