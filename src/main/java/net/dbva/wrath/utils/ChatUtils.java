package net.dbva.wrath.utils;

import net.minecraft.text.Text;
import static net.dbva.wrath.Wrath.mc;

public class ChatUtils {

    public static final String prefix = "§7[§4Wrath§7]";

    public static void sendPlainMessage(String message) {
        if (mc.world != null) {
            assert mc.player != null;
            mc.player.sendMessage(Text.of(message), false);
        }
    }
    public static void sendPlainChatMessage(String message) {
        if (mc.world != null) {
            assert mc.player != null;
            mc.player.sendChatMessage(message);
        }
    }
}