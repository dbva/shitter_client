package net.dbva.wrath.module.combat;

import net.dbva.wrath.module.Mod;

import static net.dbva.wrath.utils.ChatUtils.sendPlainChatMessage;

public class AutoCope extends Mod {

    public AutoCope() {
        super("AutoCope", "Automatically cope when you die", Category.COMBAT,false, "");
    }

    @Override
    public void onTick() {
        nullCheck();
        if (mc.player.isDead()) {
            mc.player.requestRespawn();
            sendPlainChatMessage("gg");
        }
        super.onTick();
    }
}

