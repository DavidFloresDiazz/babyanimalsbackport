package babyanimalsbackport.client;

import babyanimalsbackport.client.model.NewBabyCowModel;
import babyanimalsbackport.entity.ModEntities;
import babyanimalsbackport.entity.NewBabyCow;
import babyanimalsbackport.entity.NewBabyRedMushroom;
import babyanimalsbackport.entity.NewBabyBrownMushroom;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = "babyanimalsbackport", value = Dist.CLIENT)
public class BabyAnimalsBackportModClient {

    public static final ModelLayerLocation BABY_COW_LAYER =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "baby_cow"), "main");

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BABY_COW_LAYER, NewBabyCowModel::createBodyLayer);
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
    }

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

    public static class NewBabyRedMushroomRenderer extends MobRenderer<NewBabyRedMushroom, EntityModel<NewBabyRedMushroom>> {

        private static final ResourceLocation RED_MUSHROOM_BABY_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/mushroom/red_mushroom_baby.png");

        public NewBabyRedMushroomRenderer(EntityRendererProvider.Context context) {
            super(context, new net.minecraft.client.model.CowModel(context.bakeLayer(net.minecraft.client.model.geom.ModelLayers.COW)), 0.7F);
        }

        @Override
        public ResourceLocation getTextureLocation(NewBabyRedMushroom entity) {
            return RED_MUSHROOM_BABY_TEXTURE;
        }
    }

    public static class NewBabyBrownMushroomRenderer extends MobRenderer<NewBabyBrownMushroom, EntityModel<NewBabyBrownMushroom>> {

        private static final ResourceLocation BROWN_MUSHROOM_BABY_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/mushroom/brown_mushroom_baby.png");

        public NewBabyBrownMushroomRenderer(EntityRendererProvider.Context context) {
            super(context, new net.minecraft.client.model.CowModel(context.bakeLayer(net.minecraft.client.model.geom.ModelLayers.COW)), 0.7F);
        }

        @Override
        public ResourceLocation getTextureLocation(NewBabyBrownMushroom entity) {
            return BROWN_MUSHROOM_BABY_TEXTURE;
        }
    }
}