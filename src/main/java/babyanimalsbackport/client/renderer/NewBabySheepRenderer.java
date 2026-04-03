package babyanimalsbackport.client.renderer;

import babyanimalsbackport.client.BabyAnimalsBackportModClient;
import babyanimalsbackport.client.model.NewBabySheepModel;
import babyanimalsbackport.entity.NewBabySheep;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NewBabySheepRenderer extends MobRenderer<NewBabySheep, NewBabySheepModel> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "textures/entity/sheep/sheep_temperate_baby.png");

    public NewBabySheepRenderer(EntityRendererProvider.Context context) {
        super(context, new NewBabySheepModel(context.bakeLayer(BabyAnimalsBackportModClient.BABY_SHEEP_LAYER)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(NewBabySheep entity) {
        return TEXTURE;
    }
}