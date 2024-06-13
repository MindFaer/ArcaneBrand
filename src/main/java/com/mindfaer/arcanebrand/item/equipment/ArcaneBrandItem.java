package com.mindfaer.arcanebrand.item.equipment;

import mod.maxbogomol.wizards_reborn.api.arcaneenchantment.ArcaneEnchantmentUtils;
import mod.maxbogomol.wizards_reborn.api.arcaneenchantment.IArcaneItem;
import mod.maxbogomol.wizards_reborn.common.item.equipment.arcane.ArcaneSwordItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Consumer;

public class ArcaneBrandItem extends ArcaneSwordItem implements IArcaneItem {
    public ArcaneBrandItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flags) {
        list.addAll(ArcaneEnchantmentUtils.appendHoverText(stack, world, flags));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
        ArcaneEnchantmentUtils.inventoryTick(stack, world, entity, slot, isSelected);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return ArcaneEnchantmentUtils.damageItem(stack, amount, entity);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ArcaneEnchantmentUtils.hurtEnemy(stack, target, attacker);
        return super.hurtEnemy(stack, target, attacker);
    }
}