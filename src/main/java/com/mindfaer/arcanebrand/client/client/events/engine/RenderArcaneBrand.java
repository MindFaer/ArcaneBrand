package com.mindfaer.arcanebrand.client.client.events.engine;


import com.mindfaer.arcanebrand.ArcaneBrandMain;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.client.renderer.patched.item.RenderItemBase;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;


@OnlyIn(Dist.CLIENT)
public class RenderArcaneBrand extends RenderItemBase {
    private final ItemStack sheathStack;

    public RenderArcaneBrand() {
        this.sheathStack = new ItemStack((ItemLike) ArcaneBrandMain.ARCANE_BRAND_SHEATH.get());
    }
    public void renderItemInHand(ItemStack stack, LivingEntityPatch<?> entityPatch, InteractionHand hand, HumanoidArmature armature, OpenMatrix4f[] poses, MultiBufferSource buffer, PoseStack poseStack, int packedLight) {
        OpenMatrix4f modelMatrix = new OpenMatrix4f(this.mainhandcorrectionMatrix);
        modelMatrix.mulFront(poses[armature.toolR.getId()]);
        poseStack.pushPose();
        this.mulPoseStack(poseStack, modelMatrix);
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, (Level)null, 0);
        poseStack.popPose();
        modelMatrix = new OpenMatrix4f(this.mainhandcorrectionMatrix);
        modelMatrix.mulFront(poses[armature.toolL.getId()]);
        poseStack.pushPose();
        this.mulPoseStack(poseStack, modelMatrix);
        Minecraft.getInstance().getItemRenderer().renderStatic(this.sheathStack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, (Level)null, 0);
        poseStack.popPose();
    }
}