package babyanimalsbackport;

import babyanimalsbackport.entity.ModEntities;
import babyanimalsbackport.entity.NewBabyCow;
import net.minecraft.world.entity.animal.Cow;
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
        event.put(
                ModEntities.NEW_BABY_COW.get(),
                NewBabyCow.createAttributes().build()
        );
    }
}