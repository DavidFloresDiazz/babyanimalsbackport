package babyanimalsbackport.client.model.base.renderstate;

public class SheepRenderState {
    public float headXRot;
    public float headYRot;
    public float woolColor;
    public boolean isSheared;
    public boolean isJeb;
    public float walkAnimationSpeed;
    public float walkAnimationPos;
    public float headEatPositionScale;
    public float headEatAngleScale;
    public float ageScale;

    public SheepRenderState(float headXRot, float headYRot, float woolColor, boolean isSheared, boolean isJeb, float walkAnimationSpeed, float walkAnimationPos, float headEatPositionScale, float headEatAngleScale, float ageScale) {
        this.headXRot = headXRot;
        this.headYRot = headYRot;
        this.woolColor = woolColor;
        this.isSheared = isSheared;
        this.isJeb = isJeb;
        this.walkAnimationSpeed = walkAnimationSpeed;
        this.walkAnimationPos = walkAnimationPos;
        this.headEatPositionScale = headEatPositionScale;
        this.headEatAngleScale = headEatAngleScale;
        this.ageScale = ageScale;
    }
}