package babyanimalsbackport.client;

import babyanimalsbackport.client.model.NewBabyChickenModel;
import babyanimalsbackport.client.model.NewBabyCowModel;
import babyanimalsbackport.client.model.NewBabyRedMushroomModel;
import babyanimalsbackport.client.model.NewBabyBrownMushroomModel;
import babyanimalsbackport.client.renderer.NewBabyChickenRenderer;
import babyanimalsbackport.entity.*;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = "babyanimalsbackport", value = Dist.CLIENT)
public class BabyAnimalsBackportModClient {


    //VACA
    public static final ModelLayerLocation BABY_COW_LAYER =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "baby_cow"), "main");
    //CHAMPIVACA ROJA
    public static final ModelLayerLocation BABY_RED_MUSHROOM_LAYER =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "baby_red_mushroom"), "main");
    //CHAMPIVACA MARRON
    public static final ModelLayerLocation BABY_BROWN_MUSHROOM_LAYER =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "baby_brown_mushroom"), "main");
    //POLLO
    public static final ModelLayerLocation BABY_CHICKEN_LAYER =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "baby_chicken"), "main");



    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BABY_COW_LAYER, NewBabyCowModel::createBodyLayer);
        event.registerLayerDefinition(BABY_RED_MUSHROOM_LAYER, NewBabyRedMushroomModel::createBodyLayer);
        event.registerLayerDefinition(BABY_BROWN_MUSHROOM_LAYER, NewBabyBrownMushroomModel::createBodyLayer);
        event.registerLayerDefinition(BABY_CHICKEN_LAYER, NewBabyChickenModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.NEW_BABY_COW.get(),
                context -> new NewBabyCowRenderer(context)
        );

        event.registerEntityRenderer(
                ModEntities.NEW_BABY_RED_MUSHROOM.get(),
                context -> new NewBabyRedMushroomRenderer(context)
        );

        event.registerEntityRenderer(
                ModEntities.NEW_BABY_BROWN_MUSHROOM.get(),
                context -> new NewBabyBrownMushroomRenderer(context)
        );
        event.registerEntityRenderer(
                ModEntities.NEW_BABY_CHICKEN.get(),
                context -> new NewBabyChickenRenderer(context)
        );
    }


    ///COW RENDERER IMPORTANT

    public static class NewBabyCowRenderer extends MobRenderer<NewBabyCow, NewBabyCowModel> {

        private static final ResourceLocation COW_BABY_COLD_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/cow/cow_cold_baby.png");
        private static final ResourceLocation COW_BABY_WARM_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/cow/cow_warm_baby.png");
        private static final ResourceLocation COW_BABY_TEMPERATE_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/cow/cow_temperate_baby.png");

        public NewBabyCowRenderer(EntityRendererProvider.Context context) {
            super(context, new NewBabyCowModel(context.bakeLayer(BABY_COW_LAYER)), 0.7F);
        }

        @Override
        public void render(NewBabyCow entity, float entityYaw, float partialTick,
                           PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.2F, 1.2F);
            super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
            poseStack.popPose();
        }

        @Override
        public ResourceLocation getTextureLocation(NewBabyCow entity) {
            int biomeType = entity.getBiomeType();

            switch (biomeType) {
                case 0:
                    return COW_BABY_COLD_TEXTURE;
                case 2:
                    return COW_BABY_WARM_TEXTURE;
                case 1:
                default:
                    return COW_BABY_TEMPERATE_TEXTURE;
            }
        }
    }





    ///RED MUSHROOM RENDERER IMPORTANT

    public static class NewBabyRedMushroomRenderer extends MobRenderer<NewBabyRedMushroom, NewBabyRedMushroomModel> {

        private static final ResourceLocation RED_MUSHROOM_BABY_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/mushroom/red_mushroom_baby.png");

        public NewBabyRedMushroomRenderer(EntityRendererProvider.Context context) {
            super(context, new NewBabyRedMushroomModel(context.bakeLayer(BABY_RED_MUSHROOM_LAYER)), 0.7F);
        }

        @Override
        public void render(NewBabyRedMushroom entity, float entityYaw, float partialTick,
                           PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.2F, 1.2F);
            super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
            poseStack.popPose();
        }


        @Override
        public ResourceLocation getTextureLocation(NewBabyRedMushroom entity) {
            return RED_MUSHROOM_BABY_TEXTURE;
        }
    }



    ///BROWN MUSHROOM RENDERER IMPORTANT

    public static class NewBabyBrownMushroomRenderer extends MobRenderer<NewBabyBrownMushroom, NewBabyBrownMushroomModel> {

        private static final ResourceLocation BROWN_MUSHROOM_BABY_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/mushroom/brown_mushroom_baby.png");

        public NewBabyBrownMushroomRenderer(EntityRendererProvider.Context context) {
            super(context, new NewBabyBrownMushroomModel(context.bakeLayer(BABY_BROWN_MUSHROOM_LAYER)), 0.7F);
        }


        @Override
        public void render(NewBabyBrownMushroom entity, float entityYaw, float partialTick,
                           PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
            poseStack.pushPose();
            poseStack.scale(1.2F, 1.2F, 1.2F);
            super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
            poseStack.popPose();
        }

        @Override
        public ResourceLocation getTextureLocation(NewBabyBrownMushroom entity) {
            return BROWN_MUSHROOM_BABY_TEXTURE;
        }


    }






    ///CHICKEN RENDERER IMPORTANT

    public static class NewBabyChickenRenderer extends MobRenderer<NewBabyChicken, NewBabyChickenModel> {

        private static final ResourceLocation CHICKEN_BABY_COLD_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/chicken/chicken_cold_baby.png");
        private static final ResourceLocation CHICKEN_BABY_WARM_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/chicken/chicken_warm_baby.png");
        private static final ResourceLocation CHICKEN_BABY_TEMPERATE_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/chicken/chicken_temperate_baby.png");


        public NewBabyChickenRenderer(EntityRendererProvider.Context context) {
            super(context, new NewBabyChickenModel(context.bakeLayer(BABY_CHICKEN_LAYER)), 0.7F);
        }

        @Override
        public void render(NewBabyChicken entity, float entityYaw, float partialTick,
                           PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
            poseStack.pushPose();
            poseStack.scale(2.5F, 2.5F, 2.5F);
            super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
            poseStack.popPose();
        }

        @Override
        public ResourceLocation getTextureLocation(NewBabyChicken entity) {
            int biomeType = entity.getBiomeType();

            switch (biomeType) {
                case 0:
                    return CHICKEN_BABY_COLD_TEXTURE;
                case 2:
                    return CHICKEN_BABY_WARM_TEXTURE;
                case 1:
                default:
                    return CHICKEN_BABY_TEMPERATE_TEXTURE;
            }
        }
    }
}