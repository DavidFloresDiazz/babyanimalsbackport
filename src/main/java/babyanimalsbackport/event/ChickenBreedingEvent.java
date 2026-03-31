package babyanimalsbackport.event;

import babyanimalsbackport.entity.ModEntities;
import babyanimalsbackport.entity.NewBabyChicken;
import net.minecraft.world.entity.animal.Chicken;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = "babyanimalsbackport")
public class ChickenBreedingEvent {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        // Validación básica
        if (event.getEntity() == null || event.getLevel() == null) {
            return;
        }
        // Vaca bebé
        if (event.getEntity() instanceof Chicken
                && !(event.getEntity() instanceof NewBabyChicken)
                && ((Chicken) event.getEntity()).isBaby()) {
            try {
                Chicken normalChicken = (Chicken) event.getEntity();
                // Validar que el EntityType está registrado
                if (ModEntities.NEW_BABY_CHICKEN.get() == null) {
                    return;
                }
                NewBabyChicken babyChicken = new NewBabyChicken(ModEntities.NEW_BABY_CHICKEN.get(), normalChicken.level());
                babyChicken.moveTo(normalChicken.getX(), normalChicken.getY(), normalChicken.getZ());
                babyChicken.setHealth(normalChicken.getHealth());
                babyChicken.setBaby(true);

                event.setCanceled(true);
                normalChicken.level().addFreshEntity(babyChicken);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}