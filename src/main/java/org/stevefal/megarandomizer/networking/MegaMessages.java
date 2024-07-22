package org.stevefal.megarandomizer.networking;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.stevefal.megarandomizer.MegaRandomizer1_12_2;
import org.stevefal.megarandomizer.networking.packets.GameRulesSyncS2CPacket;
import org.stevefal.megarandomizer.networking.packets.RequestGameRulesSyncC2SPacket;
import org.stevefal.megarandomizer.networking.packets.SetGameRulesC2SPacket;

public class MegaMessages {

    private static SimpleNetworkWrapper INSTANCE;

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleNetworkWrapper netReg = NetworkRegistry.INSTANCE.newSimpleChannel(MegaRandomizer1_12_2.MODID);
        INSTANCE = netReg;

        // To Server
        netReg.registerMessage(SetGameRulesC2SPacket.Handler.class, SetGameRulesC2SPacket.class, id(), Side.SERVER);
        netReg.registerMessage(RequestGameRulesSyncC2SPacket.Handler.class, RequestGameRulesSyncC2SPacket.class, id(), Side.SERVER);

        // To Client
        netReg.registerMessage(GameRulesSyncS2CPacket.Handler.class, GameRulesSyncS2CPacket.class, id(), Side.CLIENT);
    }

    public static void sendToServer(IMessage message) {
        INSTANCE.sendToServer(message);
    }

    public static void sendToPlayer(IMessage message, EntityPlayerMP player) {
        INSTANCE.sendTo(message, player);
    }


}
