package babyanimalsbackport.client.renderer;

import babyanimalsbackport.client.BabyAnimalsBackportModClient;
import babyanimalsbackport.client.model.NewBabySheepModel;
import babyanimalsbackport.entity.NewBabySheep;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
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
    public void render(NewBabySheep entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.scale(0.9F, 0.9F, 0.9F);
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(NewBabySheep entity) {
        return TEXTURE;
    }
}