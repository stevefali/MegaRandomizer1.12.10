package org.stevefal.megarandomizer;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(useMetadata = true, modid = MegaRandomizer1_12_2.MODID, name = MegaRandomizer1_12_2.NAME, version = MegaRandomizer1_12_2.VERSION)
public class MegaRandomizer1_12_2
{
    public static final String MODID = "megarandomizer1_12_2";
    public static final String NAME = "Mega Randomizer 1.12.2";
    public static final String VERSION = "1.0-1.12.2";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
