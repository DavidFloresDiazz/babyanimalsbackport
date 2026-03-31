package babyanimalsbackport.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, "babyanimalsbackport");

    //VACA

    public static final DeferredHolder<EntityType<?>, EntityType<NewBabyCow>> NEW_BABY_COW =
            ENTITY_TYPES.register("new_baby_cow", () ->
                    EntityType.Builder.<NewBabyCow>of(NewBabyCow::new, MobCategory.CREATURE)
                            .sized(0.45F, 0.7F)
                            .build("new_baby_cow")
            );

    //CHAMPIVACA ROJA

    public static final DeferredHolder<EntityType<?>, EntityType<NewBabyRedMushroom>> NEW_BABY_RED_MUSHROOM =
            ENTITY_TYPES.register("new_baby_red_mushroom", () ->
                    EntityType.Builder.<NewBabyRedMushroom>of(NewBabyRedMushroom::new, MobCategory.CREATURE)
                            .sized(0.45F, 0.7F)
                            .build("new_baby_red_mushroom")
            );

    //CHAMPIVACA MARRÓN

    public static final DeferredHolder<EntityType<?>, EntityType<NewBabyBrownMushroom>> NEW_BABY_BROWN_MUSHROOM =
            ENTITY_TYPES.register("new_baby_brown_mushroom", () ->
                    EntityType.Builder.<NewBabyBrownMushroom>of(NewBabyBrownMushroom::new, MobCategory.CREATURE)
                            .sized(0.45F, 0.7F)
                            .build("new_baby_brown_mushroom")
            );

    //POLLO

    public static final DeferredHolder<EntityType<?>, EntityType<NewBabyChicken>> NEW_BABY_CHICKEN =
            ENTITY_TYPES.register("new_baby_chicken", () ->
                    EntityType.Builder.<NewBabyChicken>of(NewBabyChicken::new, MobCategory.CREATURE)
                            .sized(0.45F, 0.7F)
                            .build("new_baby_chicken")
            );


}