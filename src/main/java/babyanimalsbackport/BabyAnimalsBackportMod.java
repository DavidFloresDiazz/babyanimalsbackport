package babyanimalsbackport;

import babyanimalsbackport.entity.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod(BabyAnimalsBackportMod.MODID)
public class BabyAnimalsBackportMod {

    public static final String MODID = "babyanimalsbackport";

    public BabyAnimalsBackportMod(IEventBus modEventBus) {

        ModEntities.ENTITY_TYPES.register(modEventBus);

        modEventBus.addListener(this::registerAttributes);
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        // Registrar atributos para todas las entidades personalizadas
        event.put(
                ModEntities.NEW_BABY_COW.get(),
                NewBabyCow.createAttributes().build()
        );

        event.put(
                ModEntities.NEW_BABY_RED_MUSHROOM.get(),
                NewBabyRedMushroom.createAttributes().build()
        );

        event.put(
                ModEntities.NEW_BABY_BROWN_MUSHROOM.get(),
                NewBabyBrownMushroom.createAttributes().build()
        );

        event.put(
                ModEntities.NEW_BABY_CHICKEN.get(),
                NewBabyChicken.createAttributes().build()
        );
    }
}