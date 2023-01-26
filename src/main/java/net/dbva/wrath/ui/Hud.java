package net.dbva.wrath.ui;

import net.dbva.wrath.font.IFont;
import net.dbva.wrath.mixin.MinecraftClientAccessor;
import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.ModuleManager;
import net.dbva.wrath.module.settings.BooleanSetting;
import net.dbva.wrath.module.settings.ModeSetting;
import net.dbva.wrath.ui.screens.clickgui.ClickGUI;
import net.dbva.wrath.utils.PlayerUtils;
import net.dbva.wrath.utils.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;

import java.awt.*;
import java.util.List;
import java.util.Comparator;

public class Hud extends Mod {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static BooleanSetting modlist = new BooleanSetting("Mod List", true);
    public static BooleanSetting fps = new BooleanSetting("FPS", true);
    public static BooleanSetting ping = new BooleanSetting("Ping", true);
    public static ModeSetting position = new ModeSetting("Position", "Top", "Side", "Top");

    public Hud() {
        super("HUD", "Displays lots of things", Category.RENDER, false, "");
        addSetting(modlist, fps, ping, position);
    }

    public static void render(MatrixStack matrices, float TickDelta) {
        RenderHud(matrices);
    }

    public static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 1f, 1f).getRGB();
    }


    public static void RenderHud(MatrixStack matrices) {
        int index = 0;
        int sWidth = mc.getWindow().getScaledWidth();
        int sHeight = mc.getWindow().getScaledHeight();

        List<Mod> enabled = ModuleManager.INSTANCE.getEnabledModules();
        enabled.sort(Comparator.comparingInt(m -> mc.textRenderer.getWidth(((Mod) m).getDisplayName())).reversed());

        RenderUtils.drawQuad(20,20,20,20, new MatrixStack());

        if (mc.currentScreen == ClickGUI.INSTANCE) return;
        for (Mod mod : enabled) {
            if (modlist.isEnabled()) {
                IFont.legacy16.drawStringWithShadow(matrices, mod.getDisplayName() + " " + Formatting.GRAY, (sWidth - 4) - IFont.legacy16.getStringWidth(mod.getDisplayName()), 16 + (index * IFont.legacy16.getFontHeight()), rainbow(1));
                index++;
            }
        }
        if (fps.isEnabled()) {
            IFont.legacy18.drawStringWithShadow(matrices, "§7[§4FPS§7]§f " + MinecraftClientAccessor.getFps(), (5), (sHeight - 5), -1);
        }
        if (ping.isEnabled()) {
            IFont.legacy18.drawStringWithShadow(matrices, "§7[§4PING§7]§f " + PlayerUtils.getPing() + "ms", (5), (sHeight - 15), -1);
        }
    }
}