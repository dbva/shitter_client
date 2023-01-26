package net.dbva.wrath.module;

import net.dbva.wrath.module.settings.Setting;
import net.dbva.wrath.utils.ChatUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.dbva.wrath.utils.ChatUtils.sendPlainMessage;

public abstract class Mod {

    private static String name;
    public static String modMode;
    public boolean hasSetting;
    private String displayName;
    private String description;
    private Category category;
    private int key;
    private boolean enabled;

    private final List<Setting> settings = new ArrayList<>();
    protected MinecraftClient mc = MinecraftClient.getInstance();

    public enum Category { COMBAT("Combat"), RENDER("Render"), MOVEMENT("Movement"), PLAYER("Player");

        public final String name;

        Category(String name) {
            this.name = name;
        }
    }

    public Mod(String name, String description, Category category, boolean hasSetting, String modMode) {
        Mod.modMode = modMode;
        this.hasSetting = hasSetting;
        Mod.name = name;
        this.displayName = name;
        this.description = description;
        this.category = category;
    }

    public List<Setting> getSettings() {
        return settings;
    }
    public void addSetting(Setting setting) { settings.add(setting); }
    public void addSetting(Setting... settings) {
        for (Setting setting : settings) addSetting(setting);
    }

    public void toggle() {
        this.enabled = !this.enabled;
        assert mc.player != null;

        if (enabled) { onEnable();
            sendPlainMessage(ChatUtils.prefix + " Toggled §r" + this.getDisplayName() + " §aon");
        } else { onDisable();
            sendPlainMessage(ChatUtils.prefix + " Toggled §r" + this.getDisplayName() + " §coff");
        }
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public String getDisplayName() { return displayName; }
    public String getName() { return name; }
    public void setName(String name) { Mod.name = name; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }
    public boolean isEnabled() { return enabled; }

    public void nullCheck() {
        if (mc.world == null || mc.player == null || mc.getNetworkHandler() == null || mc.getBufferBuilders() == null) {
            return;
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (enabled) onEnable();
        else onDisable();
    }

    @Override
    public int hashCode() { return Objects.hashCode(name); }
}
