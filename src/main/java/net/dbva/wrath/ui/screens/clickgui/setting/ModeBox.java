package net.dbva.wrath.ui.screens.clickgui.setting;

import net.dbva.wrath.font.IFont;
import net.dbva.wrath.module.settings.ModeSetting;
import net.dbva.wrath.module.settings.Setting;
import net.dbva.wrath.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class ModeBox extends Component {

    private ModeSetting modeSet = (ModeSetting)setting;

    public ModeBox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.modeSet = (ModeSetting)setting;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(40, 40, 40).getRGB());
        int textOffset = ((parent.parent.height / 2) - IFont.legacy16.getFontHeight() / 2);

        IFont.legacy16.drawStringWithShadow(matrices, modeSet.getName() + ":§7 " + modeSet.getMode(), parent.parent.x + textOffset, parent.parent.y + parent.offset + offset + textOffset, new Color(197, 51, 51).getRGB());
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            modeSet.cycle();
            super.mouseClicked(mouseX, mouseY, button);
        }
    }
}
