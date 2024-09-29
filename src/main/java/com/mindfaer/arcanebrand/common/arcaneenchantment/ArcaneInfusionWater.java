package com.mindfaer.arcanebrand.common.arcaneenchantment;

import com.mindfaer.arcanebrand.ArcaneBrandMain;
import com.mindfaer.arcanebrand.api.arcaneenchantment.ArcaneBrandEnchantmentUtils;
import com.mindfaer.arcanebrand.item.equipment.ArcaneBrandItem;
import mod.maxbogomol.wizards_reborn.WizardsReborn;
import mod.maxbogomol.wizards_reborn.api.arcaneenchantment.ArcaneEnchantment;
import mod.maxbogomol.wizards_reborn.api.arcaneenchantment.ArcaneEnchantmentUtils;
import mod.maxbogomol.wizards_reborn.api.wissen.WissenUtils;
import mod.maxbogomol.wizards_reborn.common.network.MagicBladeEffectPacket;
import mod.maxbogomol.wizards_reborn.common.network.PacketHandler;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ArcaneInfusionWater extends ArcaneEnchantment {
    private static Random random = new Random();

    public ArcaneInfusionWater(String id, int maxLevel) {
        super(id, maxLevel);
    }

    public Color getColor() {
        return new Color(152, 180, 223);
    }

    public boolean canEnchantItem(ItemStack stack) {
        return ((stack.getItem() instanceof ArcaneBrandItem));
    }

    public static void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide) {
            if (attacker instanceof Player player) {

                if (ArcaneEnchantmentUtils.isArcaneItem(stack)) {
                    int enchantmentLevel = ArcaneBrandEnchantmentUtils.getArcaneEnchantment(stack, ArcaneBrandMain.ARCANE_INFUSION_WATER);

                    if (enchantmentLevel > 0 && random.nextFloat() < (0.35F + ((enchantmentLevel - 1) * 0.025F))) {
                        float costModifier = WissenUtils.getWissenCostModifierWithDiscount(player);
                        List<ItemStack> items = WissenUtils.getWissenItemsNoneAndStorage(WissenUtils.getWissenItemsCurios(player));
                        int wissen = WissenUtils.getWissenInItems(items);
                        int cost = (int) (60 * (1 - costModifier));
                        if (cost <= 0) {
                            cost = 1;
                        }

                        if (WissenUtils.canRemoveWissen(wissen, cost)) {
                            WissenUtils.removeWissenFromWissenItems(items, cost);
                            target.invulnerableTime = 0;
                            target.hurt(new DamageSource(target.damageSources().magic().typeHolder(), player), (1.0f * enchantmentLevel));
                            target.level().playSound(WizardsReborn.proxy.getPlayer(), target.getOnPos(), WizardsReborn.CRYSTAL_HIT_SOUND.get(), SoundSource.PLAYERS, 1.3f, (float) (1.0f + ((random.nextFloat() - 0.5D) / 3)));
                            PacketHandler.sendToTracking(target.level(), target.getOnPos(), new MagicBladeEffectPacket((float) target.getX(), (float) target.getY() + (target.getBbHeight() / 2), (float) target.getZ()));
                        }
                    }
                }
            }
        }
    }
}