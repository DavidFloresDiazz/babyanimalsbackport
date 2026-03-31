package babyanimalsbackport.event;

import babyanimalsbackport.entity.ModEntities;
import babyanimalsbackport.entity.NewBabyCow;
import babyanimalsbackport.entity.NewBabyRedMushroom;
import babyanimalsbackport.entity.NewBabyBrownMushroom;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.MushroomCow;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import java.util.Random;

@EventBusSubscriber(modid = "babyanimalsbackport")
public class CowBreedingEvent {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        // Vaca bebé
        if (event.getEntity() instanceof Cow
                && !(event.getEntity() instanceof NewBabyCow)
                && !(event.getEntity() instanceof MushroomCow)
                && ((Cow) event.getEntity()).isBaby()) {

            Cow normalCow = (Cow) event.getEntity();

            System.out.println("[CowBreedingEvent] Vaca bebé detectada, reemplazando...");

            NewBabyCow babyCow = new NewBabyCow(ModEntities.NEW_BABY_COW.get(), normalCow.level());
            babyCow.moveTo(normalCow.getX(), normalCow.getY(), normalCow.getZ());
            babyCow.setHealth(normalCow.getHealth());
            babyCow.setBaby(true);

            event.setCanceled(true);
            normalCow.level().addFreshEntity(babyCow);

            System.out.println("[CowBreedingEvent] ¡Nueva baby cow nacida!");
        }

        // Mooshroom bebé
        else if (event.getEntity() instanceof MushroomCow
                && !(event.getEntity() instanceof NewBabyRedMushroom)
                && !(event.getEntity() instanceof NewBabyBrownMushroom)
                && ((MushroomCow) event.getEntity()).isBaby()) {

            MushroomCow normalMushroom = (MushroomCow) event.getEntity();

            System.out.println("[CowBreedingEvent] Mooshroom bebé detectado, reemplazando...");

            Random rng = new Random();

            // Decidir si es rojo o marrón basándose en el NBT o aleatoriamente
            if (rng.nextBoolean()){
                NewBabyRedMushroom babyMushroom = new NewBabyRedMushroom(ModEntities.NEW_BABY_RED_MUSHROOM.get(), normalMushroom.level());
                babyMushroom.moveTo(normalMushroom.getX(), normalMushroom.getY(), normalMushroom.getZ());
                babyMushroom.setHealth(normalMushroom.getHealth());
                babyMushroom.setBaby(true);

                event.setCanceled(true);
                normalMushroom.level().addFreshEntity(babyMushroom);
            } else {
                NewBabyBrownMushroom babyMushroom = new NewBabyBrownMushroom(ModEntities.NEW_BABY_BROWN_MUSHROOM.get(), normalMushroom.level());
                babyMushroom.moveTo(normalMushroom.getX(), normalMushroom.getY(), normalMushroom.getZ());
                babyMushroom.setHealth(normalMushroom.getHealth());
                babyMushroom.setBaby(true);

                event.setCanceled(true);
                normalMushroom.level().addFreshEntity(babyMushroom);
            }

            System.out.println("[CowBreedingEvent] ¡Nuevo baby mooshroom nacido!");
        }
    }
}