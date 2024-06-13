package com.mindfaer.arcanebrand.api.arcaneenchantment;

import com.mindfaer.arcanebrand.common.arcaneenchantment.ArcaneInfusionEarth;
import mod.maxbogomol.wizards_reborn.api.arcaneenchantment.ArcaneEnchantmentUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ArcaneBrandEnchantmentUtils extends ArcaneEnchantmentUtils {
    public static void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ArcaneInfusionEarth.hurtEnemy(stack, target, attacker);
    }
}
