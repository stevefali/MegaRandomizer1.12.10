package org.stevefal.megarandomizer.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.stevefal.megarandomizer.gui.ModPauseScreen;
import org.stevefal.megarandomizer.networking.MegaMessages;
import org.stevefal.megarandomizer.networking.packets.RequestGameRulesSyncC2SPacket;

@SideOnly(Side.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public void onPauseMenuTriggered(GuiScreenEvent.InitGuiEvent event) {
        MegaMessages.sendToServer(new RequestGameRulesSyncC2SPacket());
        if (event.getGui() instanceof GuiIngameMenu && !(event.getGui() instanceof ModPauseScreen)) {
            Minecraft.getMinecraft().displayGuiScreen(new ModPauseScreen());
        }
    }
}
