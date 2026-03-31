package babyanimalsbackport.client.model;

import babyanimalsbackport.entity.NewBabyChicken;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class NewBabyChickenModel extends ChickenModel<NewBabyChicken> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("babyanimalsbackport", "new_baby_chicken"), "main");

    public NewBabyChickenModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        float scale = 1.0F; // opcional, escala interna del modelo

        // cabeza vacía
        root.addOrReplaceChild("head", CubeListBuilder.create().addBox(0f,0f,0f,0,0,0), PartPose.ZERO);

        // cuerpo
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0,0).addBox(-2.0F*scale,-2.25F*scale,-0.75F*scale,4.0F*scale,4.0F*scale,4.0F*scale)
                        .texOffs(10,8).addBox(-1.0F*scale,-0.25F*scale,-1.75F*scale,2.0F*scale,1.0F*scale,1.0F*scale),
                PartPose.offset(0F,20.25F*scale,-1.25F*scale)
        );

        // patas
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(2,2).addBox(-0.5F,0,0,1,2,0)
                .texOffs(0,1).addBox(-0.5F,2, -1,1,0,1), PartPose.offset(1,22,0.5F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0,2).addBox(-0.5F,0,0,1,2,0)
                .texOffs(0,0).addBox(-0.5F,2,-1,1,0,1), PartPose.offset(-1,22,0.5F));

        // alas
        root.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(6,8).addBox(0,0,-1,1,0,2), PartPose.offset(2,20,0));
        root.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(4,8).addBox(-1,0,-1,1,0,2), PartPose.offset(-2,20,0));

        // beak y red_thing invisibles
        root.addOrReplaceChild("beak", CubeListBuilder.create().addBox(0,0,0,0,0,0), PartPose.ZERO);
        root.addOrReplaceChild("red_thing", CubeListBuilder.create().addBox(0,0,0,0,0,0), PartPose.ZERO);

        return LayerDefinition.create(mesh,16,16);
    }
}