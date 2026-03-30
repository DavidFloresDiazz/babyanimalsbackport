package babyanimalsbackport.registry;

import babyanimalsbackport.BabyAnimalsBackportMod;
import babyanimalsbackport.entity.NewBabyCow;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModEntities {

    // 🔥 REGISTRO DE NEOFORGE (esto es lo importante)
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, BabyAnimalsBackportMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<NewBabyCow>> NEW_BABY_COW =
            ENTITIES.register("new_baby_cow", () ->
                    EntityType.Builder.of(NewBabyCow::new, MobCategory.CREATURE)
                            .sized(0.9f, 1.4f)
                            .build("new_baby_cow")
            );
}