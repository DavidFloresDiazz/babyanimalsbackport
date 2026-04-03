package babyanimalsbackport.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class NewBabySheep extends Sheep {
    private static final int GROWTH_TICKS = 24000; // 20 minutos
    private int ageTicks = 0;
    private boolean isInitialized = false;

    public static AttributeSupplier.Builder createAttributes() {
        return Sheep.createAttributes();
    }

    public NewBabySheep(EntityType<? extends Sheep> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        // Null check para el level
        if (this.level() == null) {
            System.err.println("[ERROR] NewBabySheep.tick() - level() es null!");
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
                System.out.println("[NewBabySheep] Age: " + ageTicks + " / " + GROWTH_TICKS);
            }

            if (ageTicks >= GROWTH_TICKS) {
                System.out.println("[NewBabySheep] ¡CONVERTIENDO A OVEJA ADULTA!");
                this.convertToAdultSheep();
            }
        }
    }

    private void convertToAdultSheep() {
        if (this.level() == null) {
            System.err.println("[ERROR] NewBabySheep.convertToAdultSheep() - level() es null!");
            return;
        }

        try {
            Sheep adultSheep = new Sheep(EntityType.SHEEP, this.level());
            adultSheep.moveTo(this.getX(), this.getY(), this.getZ());
            adultSheep.setYRot(this.getYRot());
            adultSheep.setXRot(this.getXRot());
            adultSheep.setHealth(this.getHealth());
            // Copiar color de lana si es posible
            if (this instanceof Sheep) {
                adultSheep.setColor(((Sheep) this).getColor());
            }

            this.level().addFreshEntity(adultSheep);
            this.discard();
        } catch (Exception e) {
            System.err.println("[ERROR] Exception al convertir a oveja adulta:");
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