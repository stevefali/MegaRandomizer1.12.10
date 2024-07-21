package org.stevefal.megarandomizer.megadrops;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomDrops {

    private static ArrayList<ItemStack> masterList;
    private static ArrayList<ItemStack> shuffledList;

    public static void shuffleItems(long gameSeed) {

        masterList = new ArrayList<>();
        masterList.addAll(MegaItemRegistry.createMegaItemList(excludeItems));

        shuffledList = new ArrayList<>(masterList);
        Collections.shuffle(shuffledList, new Random(gameSeed));
    }

    public static ItemStack getRandomizedItem(ItemStack vanillaItem) {
        if (masterList != null) {
            int index = getMasterListIndex(vanillaItem);

            if (index == -1) {
                return vanillaItem;
            } else {
                return shuffledList.get(index);
            }
        } else {
            return vanillaItem;
        }
    }

    private static int getMasterListIndex(ItemStack itemStack) {
        for (int i = 0; i < masterList.size(); i++)
            if (ItemStack.areItemsEqual(itemStack, masterList.get(i))) {
                return i;
            }
        return -1;
    }

    private static final String[] excludeItems = {
            "minecraft:structure_block",
            "minecraft:structure_void",
            "minecraft:chain_command_block",
            "minecraft:air",
            "minecraft:bedrock",
            "minecraft:command_block_minecart",
            "minecraft:spawn_egg",
            "minecraft:command_block",
            "minecraft:mob_spawner",
            "minecraft:repeating_command_block",
            "minecraft:barrier",
            "minecraft:end_portal_frame"
    };
}
