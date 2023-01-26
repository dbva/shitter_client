package net.dbva.wrath.module.combat;

import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.settings.BooleanSetting;
import net.dbva.wrath.module.settings.NumberSetting;

public class AutoLog extends Mod {

    public NumberSetting health = new NumberSetting("Health", 1, 20, 1, 1);
    public BooleanSetting totem = new BooleanSetting("Totem", false);

    public AutoLog() {
        super("AutoLog", "Log on met circumstances", Category.COMBAT,false, "");
    }

    @Override
    public void onTick() {


        super.onTick();
    }
}

