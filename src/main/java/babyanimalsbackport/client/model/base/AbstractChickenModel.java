package babyanimalsbackport.client.model.base;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import babyanimalsbackport.client.model.base.renderstate.ChickenRenderState;
import net.minecraft.util.Mth;

public abstract class AbstractChickenModel extends ChickenModel {
    public static final float Y_OFFSET = 16.0F;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public AbstractChickenModel(final ModelPart root) {
        super(root);
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
        this.rightWing = root.getChild("right_wing");
        this.leftWing = root.getChild("left_wing");
    }

    public void setupAnim(ChickenRenderState state) {
        float flapAngle = (Mth.sin(state.flap) + 1.0F) * state.flapSpeed;
        float animationSpeed = state.walkAnimationSpeed;
        float animationPos = state.walkAnimationPos;
        this.rightLeg.xRot = Mth.cos(animationPos * 0.6662F) * 1.4F * animationSpeed;
        this.leftLeg.xRot = Mth.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * animationSpeed;
        this.rightWing.zRot = flapAngle;
        this.leftWing.zRot = -flapAngle;
    }
}
