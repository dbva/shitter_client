package net.dbva.wrath.module.render;

import net.dbva.wrath.module.Mod;
import net.dbva.wrath.module.settings.NumberSetting;
import net.minecraft.client.util.math.MatrixStack;

public class ViewModel extends Mod {

    public static boolean enabled = false;

    public static NumberSetting x = new NumberSetting("X", -3, 1, 1, 0.1);
    public static NumberSetting y = new NumberSetting("Y", -3, 1, 1, 0.1);
    public static NumberSetting z = new NumberSetting("Z", -3, 1, 1, 0.1);

    public static NumberSetting scalex = new NumberSetting("Scale X",-3, 1, 1, 0.1);
    public static NumberSetting scaley = new NumberSetting("Scale Y", -3, 1, 1, 0.1);
    public static NumberSetting scalez = new NumberSetting("Scale Z", -3, 1, 1, 0.1);

    public ViewModel() {
        super("ViewModel", "Changes how the hand is rendered", Category.RENDER, false, "");
        addSetting(x, y, z, scalex, scaley, scalez);
    }

    public static void transform(MatrixStack matrices) {
        if (!isEnabled()) {
            enabled = false;
            return;
        }

        enabled = true;
        matrices.scale((float) scalex.getValue(), (float) scaley.getValue(),
                (float) scalez.getValue());
        matrices.translate(x.getValue(), y.getValue(), z.getValue());
    }

}
