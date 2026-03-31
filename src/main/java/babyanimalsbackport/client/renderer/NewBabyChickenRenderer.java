package babyanimalsbackport.client.renderer;

import babyanimalsbackport.entity.NewBabyChicken;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NewBabyChickenRenderer extends MobRenderer<NewBabyChicken, ChickenModel<NewBabyChicken>> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/cow/cow_cold_baby.png");

    public NewBabyChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(NewBabyChicken entity) {
        return TEXTURE;
    }
}