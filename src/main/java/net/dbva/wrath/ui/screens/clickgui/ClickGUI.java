package net.dbva.wrath.ui.screens.clickgui;

import net.dbva.wrath.module.Mod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Screen {

    public static final ClickGUI INSTANCE = new ClickGUI();

    private final List<Frame> frames;

    private ClickGUI() {
        super(Text.of("Click GUI"));

        frames = new ArrayList<>();

        int offset = 20;
        for (Mod.Category category : Mod.Category.values()) {
            frames.add(new Frame(category, offset,20, 100, 20));
            offset += 120;
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        for (Frame frame : frames) {
            frame.render(matrices, mouseX, mouseY, delta);
            frame.updatePosition(mouseX, mouseY);
        }
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
             frame.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);

    }
}
