package org.stevefal.megarandomizer.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.GameRules;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;
import org.stevefal.megarandomizer.networking.MegaMessages;

public class RequestGameRulesSyncC2SPacket implements IMessage {

    public RequestGameRulesSyncC2SPacket() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<RequestGameRulesSyncC2SPacket, IMessage> {

        @Override
        public IMessage onMessage(RequestGameRulesSyncC2SPacket requestGameRulesSyncC2SPacket, MessageContext ctx) {
            GameRules gameRules = ctx.getServerHandler().player.getEntityWorld().getGameRules();
            MegaMessages.sendToPlayer(new GameRulesSyncS2CPacket(
                            gameRules.getBoolean(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS),
                            gameRules.getBoolean(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS),
                            gameRules.getBoolean(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS)),
                    ctx.getServerHandler().player);

            return null;
        }
    }
}
