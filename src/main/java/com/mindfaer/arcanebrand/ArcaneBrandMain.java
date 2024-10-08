package com.mindfaer.arcanebrand;

import com.mindfaer.arcanebrand.common.arcaneenchantment.*;
import com.mindfaer.arcanebrand.item.equipment.ArcaneBrandItem;
import com.mindfaer.arcanebrand.item.equipment.CustomItemTier;
import mod.maxbogomol.wizards_reborn.api.arcaneenchantment.ArcaneEnchantment;
import mod.maxbogomol.wizards_reborn.api.arcaneenchantment.ArcaneEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(ArcaneBrandMain.MODID)
public class ArcaneBrandMain
{
    public static final String MODID = "arcanebrand";
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    //Arcane Enchantments
    public static ArcaneEnchantment ARCANE_INFUSION_EARTH = new ArcaneInfusionEarth(MODID +":arcane_infusion_earth", 1);
    public static ArcaneEnchantment ARCANE_INFUSION_WATER = new ArcaneInfusionWater(MODID +":arcane_infusion_water", 1);
    public static ArcaneEnchantment ARCANE_INFUSION_FIRE = new ArcaneInfusionFire(MODID +":arcane_infusion_fire", 1);
    public static ArcaneEnchantment ARCANE_INFUSION_AIR = new ArcaneInfusionVoid(MODID +":arcane_infusion_air", 1);
    public static ArcaneEnchantment ARCANE_INFUSION_VOID = new ArcaneInfusionAir(MODID +":arcane_infusion_void", 1);

    public static void setupArcaneEnchantments() {
        ArcaneEnchantments.register(ARCANE_INFUSION_EARTH);
        ArcaneEnchantments.register(ARCANE_INFUSION_WATER);
        ArcaneEnchantments.register(ARCANE_INFUSION_VOID);
        ArcaneEnchantments.register(ARCANE_INFUSION_FIRE);
        ArcaneEnchantments.register(ARCANE_INFUSION_AIR);
    }

    // Blocks and items
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

    // Weapons
    public static final RegistryObject<Item> ARCANE_BRAND = ITEMS.register("arcane_brand", () -> new ArcaneBrandItem(CustomItemTier.ARCANE_BRAND, 5, -2f, new Item.Properties()));
    public static final RegistryObject<Item> ARCANE_BRAND_SHEATH = ITEMS.register("arcane_brand_sheath", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

    // Creative Tab
    public static final RegistryObject<CreativeModeTab> ARCANEBRANDTAB = CREATIVE_MODE_TABS.register("arcane_brand", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ARCANE_BRAND.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ARCANE_BRAND.get());
                output.accept(ARCANE_BRAND_SHEATH.get());
                output.accept(EXAMPLE_BLOCK_ITEM.get());
            }).build());

    public ArcaneBrandMain()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // Actually set up the arcane enchantments!
        setupArcaneEnchantments();
    }
    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }
}
