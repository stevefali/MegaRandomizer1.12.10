package org.stevefal.megarandomizer.megadrops;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MegaItemRegistry {

    private static final Map<Integer, Integer> subtypeQuantities = createSubtypeQuantities();

    public static ArrayList<ItemStack> createMegaItemList(String[] excludeItems) {
        ArrayList<ItemStack> megaItems = new ArrayList<>();

        ForgeRegistries.ITEMS.getEntries().forEach(registryItem -> {
            if (!Arrays.asList(excludeItems).contains(registryItem.getKey().toString())) {
                if (!registryItem.getValue().getHasSubtypes()) {
                    megaItems.add(registryItem.getValue().getDefaultInstance());
                } else {
                    if (!megaItems.contains(registryItem.getValue().getDefaultInstance())) {
                        int itemId = Item.getIdFromItem(registryItem.getValue());
                        if (subtypeQuantities.containsKey(itemId)) {
                            // Add all the item's subtypes
                            int subtypeQuantity = subtypeQuantities.get(itemId);
                            for (int i = 0; i < subtypeQuantity; i++) {
                                ItemStack subtype = Item.getItemById(itemId).getDefaultInstance();
                                subtype.setItemDamage(i);
                                megaItems.add(subtype);
                            }
                        } else {
                            // Just add the one
                            megaItems.add(registryItem.getValue().getDefaultInstance());
                        }
                    }
                }
            }
        });
        return megaItems;
    }

    private static Map<Integer, Integer> createSubtypeQuantities() {
        Map<Integer, Integer> subtypeQuantities = new HashMap<>();
        subtypeQuantities.put(1, 7);
        subtypeQuantities.put(322, 2);
        subtypeQuantities.put(3, 3);
        subtypeQuantities.put(5, 6);
        subtypeQuantities.put(6, 6);
        subtypeQuantities.put(263, 2);
        subtypeQuantities.put(139, 2);
        subtypeQuantities.put(12, 2);
        subtypeQuantities.put(397, 6);
        subtypeQuantities.put(145, 3);
        subtypeQuantities.put(17, 4);
        subtypeQuantities.put(18, 4);
        subtypeQuantities.put(19, 2);
        subtypeQuantities.put(24, 3);
        subtypeQuantities.put(155, 3);
        subtypeQuantities.put(349, 4);
        subtypeQuantities.put(350, 2);
        subtypeQuantities.put(351, 16);
        subtypeQuantities.put(95, 16);
        subtypeQuantities.put(159, 16);
        subtypeQuantities.put(31, 2);
        subtypeQuantities.put(160, 16);
        subtypeQuantities.put(161, 2);
        subtypeQuantities.put(97, 6);
        subtypeQuantities.put(162, 2);
        subtypeQuantities.put(98, 4);
        subtypeQuantities.put(355, 16);
        subtypeQuantities.put(35, 16);
        subtypeQuantities.put(38, 9);
        subtypeQuantities.put(168, 3);
        subtypeQuantities.put(425, 16);
        subtypeQuantities.put(171, 16);
        subtypeQuantities.put(44, 7);
        subtypeQuantities.put(175, 6);
        subtypeQuantities.put(179, 3);
        subtypeQuantities.put(251, 16);
        subtypeQuantities.put(252, 16);
        subtypeQuantities.put(126, 6);

        return subtypeQuantities;
    }
}
