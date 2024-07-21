package org.stevefal.megarandomizer.event;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;
import org.stevefal.megarandomizer.megadrops.RandomDrops;

import java.util.ArrayList;

public class ServerEvents {

    @SubscribeEvent
    public void onBlockDrop(BlockEvent.HarvestDropsEvent event) {
        if (event.getWorld().getGameRules().getBoolean(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS)) {
            ArrayList<ItemStack> randomizedDrops = new ArrayList<>();
            for (ItemStack vanillaDrop : event.getDrops()) {
                for (int i = 0; i < vanillaDrop.getCount(); i++) {
                    randomizedDrops.add(RandomDrops.getRandomizedItem(vanillaDrop));
                }
            }
            event.getDrops().clear();
            event.getDrops().addAll(randomizedDrops);
        }
    }

}
