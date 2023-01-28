package net.dbva.wrath.ui;

import net.dbva.wrath.font.IFont;
import net.dbva.wrath.mixin.MinecraftClientAccessor;
import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.ModuleManager;
import net.dbva.wrath.module.settings.BooleanSetting;
import net.dbva.wrath.module.settings.ModeSetting;
import net.dbva.wrath.ui.screens.clickgui.ClickGUI;
import net.dbva.wrath.utils.PlayerUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

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

    public static int rainbow(float seconds, float saturation, float brightness, long index) {
        float hue = ((System.currentTimeMillis() + index) % (int) (seconds * 1000)) / (float) (seconds * 1000);
        int color = Color.HSBtoRGB(hue, saturation, 1);
        return color;
    }

    public static void RenderHud(MatrixStack matrices) {
        int index = 0;
        int sWidth = mc.getWindow().getScaledWidth();
        int sHeight = mc.getWindow().getScaledHeight();

        List<Mod> enabled = ModuleManager.INSTANCE.getEnabledModules();
        enabled.sort(Comparator.comparingInt(m -> IFont.legacy18.getStringWidth(((Mod) m).getDisplayName())).reversed());

        if (mc.currentScreen == ClickGUI.INSTANCE) return;
        for (Mod mod : enabled) {
            if (modlist.isEnabled()) {
                DrawableHelper.fill(matrices, sWidth - 11 - IFont.legacy18.getStringWidth(mod.getDisplayName()), IFont.legacy18.getFontHeight() * index + 6, sWidth - 5, (IFont.legacy18.getFontHeight() * index) + IFont.legacy18.getFontHeight() + 6, new Color(28, 28, 28, 80).getRGB());
                IFont.legacy18.drawStringWithShadow(matrices, mod.getDisplayName(), (sWidth - 10) - IFont.legacy18.getStringWidth(mod.getDisplayName()), 6 + (index * IFont.legacy18.getFontHeight()), rainbow(10, 0.8f, 1, 400L * index));
                DrawableHelper.fill(matrices, sWidth - 5, IFont.legacy18.getFontHeight() * index + 5, sWidth - 3, (IFont.legacy18.getFontHeight() * index) + IFont.legacy18.getFontHeight() + 8, rainbow(10, 0.8f, 1, 400L * index));
                index++;
            }
        }
        if (fps.isEnabled()) {
            IFont.legacy18.drawStringWithShadow(matrices, "§7[§r" + "FPS" + "§7]§f " + MinecraftClientAccessor.getFps(), (5), (sHeight - 30), rainbow(10, 0.8f, 1, 400L * index));
        }
        if (ping.isEnabled()) {
            IFont.legacy18.drawStringWithShadow(matrices, "§7[§r" + "PING" + "§7]§f " + PlayerUtils.getPing() + "ms", (5), (sHeight - 15), rainbow(10, 0.8f, 1, 400L * index));
        }
    }
}