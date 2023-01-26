package net.dbva.wrath.module.settings;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ModeSetting extends Setting {

    private boolean hasSetting;
    private final List<String> modes;
    public String modMode;
    private int index;

    public ModeSetting(String name, String defaultMode, String... modes) {
        super(name);
        this.modMode = defaultMode;
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultMode);
    }

    public String getMode() {
        return modMode;
    }

    public List<String> getModes() {
        return modes;
    }

    public void setMode(String mode) {
        this.modMode = mode;
        this.index = modes.indexOf(mode);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        this.modMode = modes.get(index);
    }

    public void cycle() {
        if (index < modes.size() - 1) {
            index++;
            modMode = modes.get(index);
        } else if (index >= modes.size()- 1) {
            index = 0;
            modMode = modes.get(0);
        }
    }

    public boolean isMode(String mode) {
        return Objects.equals(this.modMode, mode);
    }
}
