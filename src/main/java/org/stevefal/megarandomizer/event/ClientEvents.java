package org.stevefal.megarandomizer.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.stevefal.megarandomizer.gui.ModPauseScreen;

@SideOnly(Side.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public void onPauseMenuTriggered(GuiScreenEvent.InitGuiEvent event) {
        if (event.getGui() instanceof GuiIngameMenu && !(event.getGui() instanceof ModPauseScreen)) {
            Minecraft.getMinecraft().displayGuiScreen(new ModPauseScreen());
        }
    }
}
