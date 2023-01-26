package net.dbva.wrath.module.movement;

import net.dbva.wrath.module.Mod;

public class AutoSprint extends Mod {

    public AutoSprint() {
        super("AutoSprint", "Keeps your sprint.", Category.MOVEMENT, false, "");
        
    }

    @Override
    public void onTick() {
        assert mc.player != null;
        if (mc.player.forwardSpeed > 0 && mc.player.canMoveVoluntarily()) {
            mc.player.setSprinting(true);
            super.onTick();
        }
    }
    
    @Override
    public void onDisable() {
        assert mc.player != null;
        mc.player.setSprinting(false);
        super.onDisable();
    }
}
