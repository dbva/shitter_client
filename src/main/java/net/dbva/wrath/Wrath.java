package net.dbva.wrath;
import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.ModuleManager;
import net.dbva.wrath.ui.screens.clickgui.ClickGUI;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class Wrath implements ModInitializer {

	public static final MinecraftClient mc = MinecraftClient.getInstance();

	@Override
	public void onInitialize() {
		System.out.println("loaded wrath");
	}

	public static void onKeyPress(int key, int action) {
		if (action == GLFW.GLFW_PRESS) {
			for (Mod module : ModuleManager.INSTANCE.getModules()) {
				if (key == module.getKey()) module.toggle();
			}

			if (key == GLFW.GLFW_KEY_RIGHT_CONTROL) {
				if (mc.currentScreen == null) mc.setScreen(ClickGUI.INSTANCE);
			}
		}
	}

	public static void onTick() {
		if (mc.player != null) {
			for (Mod module : ModuleManager.INSTANCE.getEnabledModules()) { module.onTick(); }
		}
	}
}