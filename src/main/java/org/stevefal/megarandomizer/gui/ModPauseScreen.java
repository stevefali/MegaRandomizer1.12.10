package org.stevefal.megarandomizer.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class ModPauseScreen extends GuiIngameMenu {

    public ModPauseScreen() {
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.get(0).y = this.height / 4 + 144 - 16;
        this.buttonList.add(new GuiButton(14, this.width / 2 - 100, this.height / 4 + 120 - 16, I18n.format("megarandomizer1_12_2.menu.megarandomoptions")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if (button.id == 14) {
            this.mc.displayGuiScreen(new MegaRandomOptionsScreen(this));
        }
    }
}
