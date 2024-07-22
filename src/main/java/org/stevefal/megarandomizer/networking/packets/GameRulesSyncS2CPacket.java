package org.stevefal.megarandomizer.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;

public class GameRulesSyncS2CPacket implements IMessage {

    private boolean isDoBlocks;
    private boolean isDoEntities;
    private boolean isDoPlayers;

    public GameRulesSyncS2CPacket() {
    }

    public GameRulesSyncS2CPacket(boolean isDoBlocks, boolean isDoEntities, boolean isDoPlayers) {
        this.isDoBlocks = isDoBlocks;
        this.isDoEntities = isDoEntities;
        this.isDoPlayers = isDoPlayers;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.isDoBlocks = buf.readBoolean();
        this.isDoEntities = buf.readBoolean();
        this.isDoPlayers = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isDoBlocks);
        buf.writeBoolean(isDoEntities);
        buf.writeBoolean(isDoPlayers);
    }

    public static class Handler implements IMessageHandler<GameRulesSyncS2CPacket, IMessage> {

        @Override
        public IMessage onMessage(GameRulesSyncS2CPacket gameRulesSyncS2CPacket, MessageContext ctx) {

            WorldClient worldClient = Minecraft.getMinecraft().world;
            worldClient.getGameRules().setOrCreateGameRule(
                    MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS,
                    String.valueOf(gameRulesSyncS2CPacket.isDoBlocks)
            );
            worldClient.getGameRules().setOrCreateGameRule(
                    MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS,
                    String.valueOf(gameRulesSyncS2CPacket.isDoEntities)
            );
            worldClient.getGameRules().setOrCreateGameRule(
                    MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS,
                    String.valueOf(gameRulesSyncS2CPacket.isDoPlayers)
            );
            return null;
        }
    }
}
