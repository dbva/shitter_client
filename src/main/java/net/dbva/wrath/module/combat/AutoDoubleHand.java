package net.dbva.wrath.module.combat;

import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.settings.NumberSetting;
import net.dbva.wrath.utils.FindItemResult;
import net.dbva.wrath.utils.InventoryUtils;
import net.minecraft.item.Items;

public class AutoDoubleHand extends Mod {

    public NumberSetting health = new NumberSetting("MinHealth", 0, 20, 0, 1);

    public AutoDoubleHand() {
        super("AutoDoubleHand", "Automatically goes to totem when popped", Mod.Category.COMBAT,false, "");
        addSetting(health);
    }

    @Override
    public void onTick() {
        nullCheck();
        assert mc.player != null;
        if (mc.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING) {
            FindItemResult iTotem = InventoryUtils.findInHotbar(Items.TOTEM_OF_UNDYING);
            if (iTotem.found()) {
                if (mc.player.getHealth() <= health.getValue()) {
                    InventoryUtils.selectItemFromHotbar(Items.TOTEM_OF_UNDYING);
                }
            } else return;
        }
        super.onTick();
    }
}

