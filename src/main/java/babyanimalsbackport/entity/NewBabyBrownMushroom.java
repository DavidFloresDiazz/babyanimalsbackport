package babyanimalsbackport.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class NewBabyBrownMushroom extends MushroomCow {
    private static final int GROWTH_TICKS = 24000; // 20 minutos
    private int ageTicks = 0;
    private boolean isInitialized = false;

    public static AttributeSupplier.Builder createAttributes() {
        return MushroomCow.createAttributes();
    }

    public NewBabyBrownMushroom(EntityType<? extends MushroomCow> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        // Null check para el level
        if (this.level() == null) {
            System.err.println("[ERROR] NewBabyBrownMushroom.tick() - level() es null!");
            return;
        }

        super.tick();

        // Inicializar como bebé
        if (!this.level().isClientSide && !this.isInitialized) {
            this.setBaby(true);
            this.isInitialized = true;
        }

        // Asegurarse que siempre es bebé en el servidor
        if (!this.level().isClientSide && !this.isBaby()) {
            this.setBaby(true);
        }

        if (!this.level().isClientSide && this.isBaby()) {
            ageTicks++;

            if (ageTicks % 100 == 0) {
                System.out.println("[NewBabyBrownMushroom] Age: " + ageTicks + " / " + GROWTH_TICKS);
            }

            if (ageTicks >= GROWTH_TICKS) {
                System.out.println("[NewBabyBrownMushroom] ¡CONVERTIENDO A MOOSHROOM ADULTO!");
                this.convertToAdultMushroom();
            }
        }
    }

    private void convertToAdultMushroom() {
        if (this.level() == null) {
            System.err.println("[ERROR] NewBabyBrownMushroom.convertToAdultMushroom() - level() es null!");
            return;
        }

        try {
            MushroomCow adultMushroom = new MushroomCow(EntityType.MOOSHROOM, this.level());
            adultMushroom.moveTo(this.getX(), this.getY(), this.getZ());
            adultMushroom.setYRot(this.getYRot());
            adultMushroom.setXRot(this.getXRot());
            adultMushroom.setHealth(this.getHealth());

            this.level().addFreshEntity(adultMushroom);
            this.discard();
        } catch (Exception e) {
            System.err.println("[ERROR] Exception al convertir a mooshroom adulto:");
            e.printStackTrace();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("AgeTicks", this.ageTicks);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("AgeTicks")) {
            this.ageTicks = tag.getInt("AgeTicks");
        }
    }
}