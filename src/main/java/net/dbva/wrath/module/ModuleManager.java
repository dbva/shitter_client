package net.dbva.wrath.module;

import net.dbva.wrath.module.combat.*;
import net.dbva.wrath.module.movement.AutoSprint;
import net.dbva.wrath.module.movement.InvWalk;
import net.dbva.wrath.module.player.SpamXP;
import net.dbva.wrath.module.render.FullBright;
import net.dbva.wrath.module.render.NoHurtCam;

import net.dbva.wrath.module.render.ViewModel;
import net.dbva.wrath.ui.Hud;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static final ModuleManager INSTANCE = new ModuleManager();
    private final List<Mod> modules = new ArrayList<>();

    public ModuleManager() {
        addModules();
    }

    public List<Mod> getModules() {
        return modules;
    }

    public List<Mod> getEnabledModules() {
        List<Mod> enabled = new ArrayList<>();
        for (Mod module : modules) {
            if (module.isEnabled()) enabled.add(module);
        }
        return enabled;
    }

    public List<Mod> getModulesInCategory(Mod.Category category) {
        List<Mod>  categoryModules = new ArrayList<>();

        for (Mod mod : modules) {
            if (mod.getCategory() == category) {
                categoryModules.add(mod);
            }
        }
        return categoryModules;
    }

    private void addModules() {
        modules.add(new AutoSprint());
        modules.add(new NoHurtCam());
        modules.add(new FullBright());
        modules.add(new SpamXP());
        modules.add(new FastCrystals());
        modules.add(new Hud());
        modules.add(new AutoTotem());
        modules.add(new InvWalk());
        modules.add(new AutoDoubleHand());
        modules.add(new AutoCope());
        modules.add(new ViewModel());
    }
}
