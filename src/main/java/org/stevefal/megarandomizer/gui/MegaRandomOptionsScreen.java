package org.stevefal.megarandomizer.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;
import org.stevefal.megarandomizer.networking.MegaMessages;
import org.stevefal.megarandomizer.networking.packets.SetGameRulesC2SPacket;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class MegaRandomOptionsScreen extends GuiScreen {

    public final ModPauseScreen MOD_PAUSE_SCREEN;

    private final WorldClient WORLD = Minecraft.getMinecraft().world;

    private final String BLOCKS = I18n.format("megarandomizer1_12_2.menu.block");
    private final String ENTITIES = I18n.format("megarandomizer1_12_2.menu.entity");
    private final String PLAYERS = I18n.format("megarandomizer1_12_2.menu.player");
    private final String ON = I18n.format("megarandomizer1_12_2.menu.on");
    private final String OFF = I18n.format("megarandomizer1_12_2.menu.off");


    MegaRandomOptionsScreen(ModPauseScreen modPauseScreen) {
        this.MOD_PAUSE_SCREEN = modPauseScreen;
    }

    public void initGui() {
        this.buttonList.clear();
        byte b0 = -16;

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 24 + b0,
                getButtonString(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS, BLOCKS)));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 48 + b0,
                getButtonString(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS, ENTITIES)));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 72 + b0,
                getButtonString(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS, PLAYERS)));

        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 96 + b0,
                I18n.format("megarandomizer1_12_2.menu.done")));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                // Blocks
                setRule(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS);
                initGui();
                updateToServer();
                break;
            case 1:
                // Entities
                setRule(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS);
                initGui();
                updateToServer();
                break;
            case 2:
                // Players
                setRule(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS);
                initGui();
                updateToServer();
                break;
            case 4:
                // Done
                Minecraft.getMinecraft().displayGuiScreen(this.MOD_PAUSE_SCREEN);
        }
    }

    private String getButtonString(String megaGameRule, String ruleName) {
        boolean ruleValue = WORLD.getGameRules().getBoolean(megaGameRule);
        String onOff = ruleValue ? ON : OFF;
        return I18n.format("megarandomizer1_12_2.menu.drops", ruleName, onOff);
    }

    private void setRule(String rule) {
        WORLD.getGameRules().setOrCreateGameRule(rule, String.valueOf(!WORLD.getGameRules().getBoolean(rule)));
    }

    private void updateToServer() {
        MegaMessages.sendToServer(new SetGameRulesC2SPacket(
                WORLD.getGameRules().getBoolean(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS),
                WORLD.getGameRules().getBoolean(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS),
                WORLD.getGameRules().getBoolean(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS)
        ));
    }

    public void updateScreen() {
        super.updateScreen();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.format("megarandomizer1_12_2.menu.title"), this.width / 2, 40, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
