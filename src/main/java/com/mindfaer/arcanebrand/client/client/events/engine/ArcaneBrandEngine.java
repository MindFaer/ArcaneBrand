package com.mindfaer.arcanebrand.client.client.events.engine;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.api.distmarker.Dist;
import com.mindfaer.arcanebrand.ArcaneBrandMain;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;

@EventBusSubscriber(
        modid = "arcanebrand",
        bus = Bus.MOD,
        value = {Dist.CLIENT}
)


public class ArcaneBrandEngine {
    public ArcaneBrandEngine() {
    }

    @SubscribeEvent
    public static void registerRenderer(PatchedRenderersEvent.Add event) {
        event.addItemRenderer((Item) ArcaneBrandMain.ARCANE_BRAND.get(), new RenderArcaneBrand());
    }
}
