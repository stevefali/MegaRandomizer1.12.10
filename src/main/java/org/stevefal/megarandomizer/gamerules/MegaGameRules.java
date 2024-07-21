package org.stevefal.megarandomizer.gamerules;

import net.minecraft.world.GameRules;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class MegaGameRules {

    public static final String RULE_DO_BLOCK_RANDOM_DROPS = "doBlockRandomDrops";
    public static final String RULE_DO_ENTITY_RANDOM_DROPS = "doEntityRandomDrops";
    public static final String RULE_DO_PLAYER_RANDOM_DROPS = "doPlayerRandomDrops";

    private static final String[] MEGA_RULES = {
            RULE_DO_BLOCK_RANDOM_DROPS,
            RULE_DO_ENTITY_RANDOM_DROPS,
            RULE_DO_PLAYER_RANDOM_DROPS
    };

    public static void register() {
        GameRules gameRules = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getGameRules();

        for (String megaRule : MEGA_RULES) {
            if (!gameRules.hasRule(megaRule)) {
                gameRules.addGameRule(megaRule, "true", GameRules.ValueType.BOOLEAN_VALUE);
            }
        }
    }

}
