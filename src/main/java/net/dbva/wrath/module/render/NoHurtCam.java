package net.dbva.wrath.module.render;

import net.dbva.wrath.module.Mod;

public class NoHurtCam extends Mod {

    public static boolean HurtCam = false;

    public NoHurtCam() {
        super("NoHurtCam", "Removes hurt animation", Category.RENDER, false, "");
    }

    @Override
    public void onEnable() {
        HurtCam = true;
        super.onEnable();

    }

    @Override
    public void onDisable() {
        HurtCam = false;
        super.onDisable();
    }
}
