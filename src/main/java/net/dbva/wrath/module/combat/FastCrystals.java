package net.dbva.wrath.module.combat;

import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.settings.NumberSetting;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import org.lwjgl.glfw.GLFW;

public class FastCrystals extends Mod {

    private int crystalPlaceClock = 0;
    private int crystalBreakClock = 0;

    public NumberSetting placetime = new NumberSetting("Place time", 0, 20, 0, 1);
    public NumberSetting breaktime = new NumberSetting("Break time", 0, 20, 0, 1);

    public FastCrystals() {
        super("FastCrystals", "Spams crystals fast", Category.COMBAT, false, "");
        addSetting(placetime, breaktime);
    }

    @Override
    public void onEnable() {
        crystalPlaceClock = 0;
        crystalBreakClock = 0;
        super.onEnable();
    }

    @Override
    public void onTick() {
        boolean dontPlaceCrystal = crystalPlaceClock != 0;
        boolean dontBreakCrystal = crystalBreakClock != 0;
        if (dontPlaceCrystal)
            crystalPlaceClock--;
        if (dontBreakCrystal)
            crystalBreakClock--;

        if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_2) != GLFW.GLFW_PRESS)
            return;

        assert mc.player != null;
        ItemStack mainHandStack = mc.player.getMainHandStack();
        if (!mainHandStack.isOf(Items.END_CRYSTAL))
            return;

        if (mc.crosshairTarget instanceof EntityHitResult hit) {
            if (!dontBreakCrystal && hit.getEntity() instanceof EndCrystalEntity crystal) {

                assert mc.interactionManager != null;
                crystalBreakClock = breaktime.getValueInt();
                mc.interactionManager.attackEntity(mc.player, crystal);
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }

        if (mc.crosshairTarget instanceof BlockHitResult hit) {
            if (!dontPlaceCrystal) {
                assert mc.interactionManager != null;
                crystalPlaceClock = placetime.getValueInt();
                ActionResult result = mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, hit);
                if (result.isAccepted() && result.shouldSwingHand()) {
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
            }
        }
    }
}
