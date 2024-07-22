package org.stevefal.megarandomizer.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;
import org.stevefal.megarandomizer.networking.MegaMessages;

public class SetGameRulesC2SPacket implements IMessage {

    private boolean isDoBlocks;
    private boolean isDoEntities;
    private boolean isDoPlayers;

    public SetGameRulesC2SPacket() {
    }

    public SetGameRulesC2SPacket(boolean isDoBlocks, boolean isDoEntities, boolean isDoPlayers) {
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


    public static class Handler implements IMessageHandler<SetGameRulesC2SPacket, IMessage> {

        @Override
        public IMessage onMessage(SetGameRulesC2SPacket setGameRulesC2SPacket, MessageContext ctx) {

            World world = ctx.getServerHandler().player.getEntityWorld();
            world.getGameRules().setOrCreateGameRule(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS, String.valueOf(setGameRulesC2SPacket.isDoBlocks));
            world.getGameRules().setOrCreateGameRule(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS, String.valueOf(setGameRulesC2SPacket.isDoEntities));
            world.getGameRules().setOrCreateGameRule(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS, String.valueOf(setGameRulesC2SPacket.isDoPlayers));

            MegaMessages.sendToPlayer(new GameRulesSyncS2CPacket(
                    setGameRulesC2SPacket.isDoBlocks,
                    setGameRulesC2SPacket.isDoEntities,
                    setGameRulesC2SPacket.isDoPlayers), ctx.getServerHandler().player);

            return null;
        }
    }
}
