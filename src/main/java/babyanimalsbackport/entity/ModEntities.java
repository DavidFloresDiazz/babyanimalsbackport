package babyanimalsbackport.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, "babyanimalsbackport");

    public static final DeferredHolder<EntityType<?>, EntityType<NewBabyCow>> NEW_BABY_COW =
            ENTITY_TYPES.register("new_baby_cow", () ->
                    EntityType.Builder.<NewBabyCow>of(NewBabyCow::new, MobCategory.CREATURE)
                            .sized(0.9F, 1.4F)
                            .build("new_baby_cow")
            );
}