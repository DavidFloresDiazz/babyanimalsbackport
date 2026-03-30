package babyanimalsbackport.entity;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class NewBabyCow extends Cow {

    public static AttributeSupplier.Builder createAttributes() {
        return Cow.createAttributes(); // copiamos los atributos de la vaca
    }

    public NewBabyCow(EntityType<? extends Cow> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean isBaby() {
        return true; // Siempre se comporta como bebé
    }
}