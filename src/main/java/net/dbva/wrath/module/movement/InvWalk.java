package net.dbva.wrath.module.movement;

import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.settings.BooleanSetting;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class InvWalk extends Mod {

    public BooleanSetting inventory = new BooleanSetting("Inventory", true);
    public BooleanSetting shift = new BooleanSetting("Shift", true);

    public InvWalk() {
        super("InvWalk", "Walk while in inventory", Category.MOVEMENT, false, "");
        addSetting(inventory, shift);
    }

    @Override
    public void onTick() {
        if (inventory.isEnabled() && mc.currentScreen != null && !(mc.currentScreen instanceof ChatScreen) && !(mc.currentScreen instanceof SignEditScreen) && !(mc.currentScreen instanceof BookScreen)) {
            for (KeyBinding k : new KeyBinding[]{mc.options.forwardKey, mc.options.backKey,
                    mc.options.leftKey, mc.options.rightKey, mc.options.jumpKey, mc.options.sprintKey}) {
                k.setPressed(InputUtil.isKeyPressed(mc.getWindow().getHandle(),
                        InputUtil.fromTranslationKey(k.getBoundKeyTranslationKey()).getCode()));
            }
            if (shift.isEnabled()) mc.options.sneakKey.setPressed(InputUtil.isKeyPressed(mc.getWindow().getHandle(),
                    InputUtil.fromTranslationKey(mc.options.sneakKey.getBoundKeyTranslationKey()).getCode()));
        }
        super.onTick();
    }
}