package net.dbva.wrath.ui.screens.clickgui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.dbva.wrath.font.IFont;
import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.settings.BooleanSetting;
import net.dbva.wrath.module.settings.ModeSetting;
import net.dbva.wrath.module.settings.NumberSetting;
import net.dbva.wrath.module.settings.Setting;
import net.dbva.wrath.ui.screens.clickgui.setting.CheckBox;
import net.dbva.wrath.ui.screens.clickgui.setting.Component;
import net.dbva.wrath.ui.screens.clickgui.setting.ModeBox;
import net.dbva.wrath.ui.screens.clickgui.setting.Slider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class ModuleButton {

    public Mod module;
    public Frame parent;
    public int offset;
    public List<Component> components;
    public boolean extended;
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public ModuleButton(Mod module, Frame parent, int offset) {
        this.module = module;
        this.parent = parent;
        this.offset = offset;
        this.extended = false;
        this.components = new ArrayList<>();

        int setOffset = parent.height;
        for (Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                components.add(new CheckBox(setting, this, setOffset));
            } else if (setting instanceof ModeSetting) {
                components.add(new ModeBox(setting, this, setOffset));
            } else if (setting instanceof NumberSetting) {
                components.add(new Slider(setting, this, setOffset));
            }
            setOffset += parent.height;
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int textOffset = ((parent.height / 2) - IFont.legacy18.getFontHeight() / 2);

        DrawableHelper.fill(matrices, parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(28, 28, 28).getRGB());
        IFont.legacy18.drawString(matrices, module.getDisplayName(), parent.x + textOffset, parent.y + offset + textOffset, module.isEnabled() ? new Color(182, 37, 37).getRGB() : Color.white.getRGB());

        if (extended) {
            for (Component component : components) {
                component.render(matrices, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked( double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                module.toggle();
            } else if (button == 1) {
                extended = !extended;
                parent.updateButtons();
            }
        }

        for (Component component : components) {
            component.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, button);
        }
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.x && mouseX < parent.x + parent.width && mouseY > parent.y + offset && mouseY < parent.y + offset + parent.height;
    }
}
