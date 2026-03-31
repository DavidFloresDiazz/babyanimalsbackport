package babyanimalsbackport.client.model.base.renderstate;

public class ChickenRenderState {
    public float flap;
    public float flapSpeed;
    public float walkAnimationSpeed;
    public float walkAnimationPos;

    public ChickenRenderState(float flap, float flapSpeed, float walkAnimationSpeed, float walkAnimationPos) {
        this.flap = flap;
        this.flapSpeed = flapSpeed;
        this.walkAnimationSpeed = walkAnimationSpeed;
        this.walkAnimationPos = walkAnimationPos;
    }
}