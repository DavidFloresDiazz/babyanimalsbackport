package babyanimalsbackport.client.renderer;

import babyanimalsbackport.entity.NewBabyCow;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NewBabyCowRenderer extends MobRenderer<NewBabyCow, CowModel<NewBabyCow>> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/cow/cow_cold_baby.png");

    public NewBabyCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(NewBabyCow entity) {
        return TEXTURE;
    }
}