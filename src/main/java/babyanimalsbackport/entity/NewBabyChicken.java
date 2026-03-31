package babyanimalsbackport.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class NewBabyChicken extends Chicken {
    private static final int GROWTH_TICKS = 24000; // 20 minutos
    private static final EntityDataAccessor<Integer> BIOME_TYPE =
            SynchedEntityData.defineId(NewBabyChicken.class, net.minecraft.network.syncher.EntityDataSerializers.INT);

    private int ageTicks = 0;
    private boolean biomeTypeDetermined = false;

    public static AttributeSupplier.Builder createAttributes() {
        return Chicken.createAttributes();
    }

    public NewBabyChicken(EntityType<? extends Chicken> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BIOME_TYPE, 1); // 1 = Templado por defecto
    }

    private void determineBiomeType() {
        if (!this.biomeTypeDetermined) {
            // Null check para evitar NullPointerException
            if (this.level() == null) {
                System.err.println("[ERROR] NewBabyChicken.level() es null!");
                this.biomeTypeDetermined = true;
                return;
            }

            if (this.blockPosition() == null) {
                System.err.println("[ERROR] NewBabyChicken.blockPosition() es null!");
                this.biomeTypeDetermined = true;
                return;
            }

            try {
                float biomeTemp = this.level().getBiome(this.blockPosition()).value().getBaseTemperature();

                int biomeType;
                if (biomeTemp < 0.1F) {
                    biomeType = 0; // Frío
                } else if (biomeTemp > 1.0F) {
                    biomeType = 2; // Cálido
                } else {
                    biomeType = 1; // Templado
                }

                this.entityData.set(BIOME_TYPE, biomeType);
                this.biomeTypeDetermined = true;
                System.out.println("[NewBabyChicken] Biome type determined: " + biomeType + " (temp: " + biomeTemp + ")");
            } catch (Exception e) {
                System.err.println("[ERROR] Exception al determinar biome type:");
                e.printStackTrace();
                this.biomeTypeDetermined = true;
            }
        }
    }

    public int getBiomeType() {
        if (this.entityData == null) {
            return 1; // Valor por defecto
        }
        return this.entityData.get(BIOME_TYPE);
    }

    @Override
    public void tick() {
        super.tick();

        // Null check para el level
        if (this.level() == null) {
            System.err.println("[ERROR] NewBabyChicken.tick() - level() es null!");
            return;
        }

        // Determinar tipo de bioma en el primer tick
        if (!this.level().isClientSide && !this.biomeTypeDetermined) {
            this.determineBiomeType();
        }

        // Asegurarse que siempre es bebé en el servidor
        if (!this.level().isClientSide && !this.isBaby()) {
            this.setBaby(true);
        }

        if (!this.level().isClientSide && this.isBaby()) {
            ageTicks++;

            if (ageTicks % 100 == 0) {
                System.out.println("[NewBabyChicken] Age: " + ageTicks + " / " + GROWTH_TICKS);
            }

            if (ageTicks >= GROWTH_TICKS) {
                System.out.println("[NewBabyChicken] ¡CONVERTIENDO A VACA ADULTA!");
                this.convertToAdultChicken();
            }
        }
    }

    private void convertToAdultChicken() {
        if (this.level() == null) {
            System.err.println("[ERROR] NewBabyChicken.convertToAdultChicken() - level() es null!");
            return;
        }

        try {
            Chicken adultChicken = new Chicken(EntityType.CHICKEN, this.level());
            adultChicken.moveTo(this.getX(), this.getY(), this.getZ());
            adultChicken.setYRot(this.getYRot());
            adultChicken.setXRot(this.getXRot());
            adultChicken.setHealth(this.getHealth());

            this.level().addFreshEntity(adultChicken);
            this.discard();
        } catch (Exception e) {
            System.err.println("[ERROR] Exception al convertir a vaca adulta:");
            e.printStackTrace();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("AgeTicks", this.ageTicks);
        if (this.entityData != null) {
            tag.putInt("BiomeType", this.entityData.get(BIOME_TYPE));
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("AgeTicks")) {
            this.ageTicks = tag.getInt("AgeTicks");
        }
        if (tag.contains("BiomeType") && this.entityData != null) {
            this.entityData.set(BIOME_TYPE, tag.getInt("BiomeType"));
            this.biomeTypeDetermined = true;
        }
    }
}