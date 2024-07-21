package org.stevefal.megarandomizer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import org.apache.logging.log4j.Logger;
import org.stevefal.megarandomizer.event.ServerEvents;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;
import org.stevefal.megarandomizer.megadrops.RandomDrops;

@Mod(useMetadata = true, modid = MegaRandomizer1_12_2.MODID, name = MegaRandomizer1_12_2.NAME, version = MegaRandomizer1_12_2.VERSION)
public class MegaRandomizer1_12_2 {
    public static final String MODID = "megarandomizer1_12_2";
    public static final String NAME = "Mega Randomizer 1.12.2";
    public static final String VERSION = "1.0-1.12.2";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ServerEvents());
    }

    @EventHandler
    public void onServerReady(FMLServerStartedEvent event) {
        MegaGameRules.register();

        RandomDrops.shuffleItems(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSeed());
    }
}
