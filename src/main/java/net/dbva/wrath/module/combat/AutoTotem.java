package net.dbva.wrath.module.combat;

import net.dbva.wrath.module.Mod;
import net.dbva.wrath.utils.FindItemResult;
import net.dbva.wrath.utils.InventoryUtils;
import net.minecraft.item.Items;

public class  AutoTotem extends Mod {

    public AutoTotem() {
        super("AutoTotem", "Automatically offhand totems", Category.COMBAT,false, "");
    }

    @Override
    public void onTick() {
        nullCheck();
        assert mc.player != null;
        if (mc.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING) {
            FindItemResult iTotem = InventoryUtils.find(Items.TOTEM_OF_UNDYING);
            if (iTotem.found()) {
                InventoryUtils.move().fromHotbar(iTotem.getSlot()).toOffhand();
            } else return;
        }
        super.onTick();
    }
}

