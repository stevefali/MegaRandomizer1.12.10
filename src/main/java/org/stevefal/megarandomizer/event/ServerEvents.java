package org.stevefal.megarandomizer.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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

    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event) {
        Entity ent = event.getEntity();
        World world = ent.getEntityWorld();

        if (ent instanceof EntityPlayer) {
            if (world.getGameRules().getBoolean(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS)) {
                randomizeEntityDrops(event, world, ent);
            }
        } else {
            if (world.getGameRules().getBoolean(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS)) {
                randomizeEntityDrops(event, world, ent);
            }
        }

    }


    private static void randomizeEntityDrops(LivingDropsEvent event, World world, Entity ent) {
        ArrayList<EntityItem> randomizedDrops = new ArrayList<>();
        for (EntityItem vanillaDrop : event.getDrops()) {
                randomizedDrops.add(new EntityItem(world, ent.posX, ent.posY, ent.posZ, RandomDrops.getRandomizedItem(vanillaDrop.getItem())));
        }
        event.getDrops().clear();
        event.getDrops().addAll(randomizedDrops);
    }


}
