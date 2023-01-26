package net.dbva.wrath.module.player;

import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.settings.BooleanSetting;
import net.dbva.wrath.utils.FindItemResult;
import net.dbva.wrath.utils.InventoryUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class SpamXP extends Mod {

    public BooleanSetting swap = new BooleanSetting("Swap stack", false);

    public SpamXP() {
        super("SpamXP", "Use experience bottles quickly", Category.PLAYER, true, "Swap");
        addSetting(swap);
    }

    @Override
    public void onTick() {
        assert mc.player != null;
        if (mc.player.getMainHandStack().getItem() == Items.EXPERIENCE_BOTTLE && mc.options.useKey.isPressed()) {
            MinecraftClient.getInstance().interactionManager.interactItem(mc.player, mc.world, Hand.MAIN_HAND);
        } else if (mc.player.getMainHandStack().getItem() != Items.EXPERIENCE_BOTTLE && swap.isEnabled()) {
            FindItemResult xpSlot = InventoryUtils.findInHotbar(Items.EXPERIENCE_BOTTLE);
            if (xpSlot.found()) {
                InventoryUtils.selectItemFromHotbar(Items.EXPERIENCE_BOTTLE);
            }
        }
        super.onTick();
    }
}