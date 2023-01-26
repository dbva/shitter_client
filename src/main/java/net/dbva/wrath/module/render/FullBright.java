package net.dbva.wrath.module.render;

import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.settings.NumberSetting;

public class FullBright extends Mod {

    public NumberSetting gammaTest = new NumberSetting("Gamma", 0.1, 3, 3, 0.1);

    public FullBright() {
        super("FullBright", "Brightens your game", Category.RENDER, false, "");
        addSetting(gammaTest);
    }

    @Override
    public void onEnable() {
        mc.options.gamma = gammaTest.getValueFloat();
        super.onTick();
    }

    @Override
    public void onDisable() {
        mc.options.gamma = 1;
        super.onDisable();
    }
}
