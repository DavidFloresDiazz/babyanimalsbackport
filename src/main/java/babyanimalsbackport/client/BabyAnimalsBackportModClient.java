package babyanimalsbackport.client;

import babyanimalsbackport.entity.ModEntities;
import babyanimalsbackport.entity.NewBabyCow;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = "babyanimalsbackport", value = Dist.CLIENT)
public class BabyAnimalsBackportModClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.NEW_BABY_COW.get(),
                context -> new NewBabyCowRenderer(context)
        );
    }

    public static class NewBabyCowRenderer extends MobRenderer<NewBabyCow, CowModel<NewBabyCow>> {

        private static final ResourceLocation COW_BABY_COLD_TEXTURE =
                ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/cow/cow_cold_baby.png");

        public NewBabyCowRenderer(EntityRendererProvider.Context context) {
            super(context, new CowModel<>(context.bakeLayer(ModelLayers.COW)), 0.7F);
        }

        @Override
        public ResourceLocation getTextureLocation(NewBabyCow entity) {
            return COW_BABY_COLD_TEXTURE;
        }
    }
}