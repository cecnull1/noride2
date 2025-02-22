package a.b.noride2;

import a.b.noride2.constant.Constant;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class aEntity extends Horse {
    public aEntity(EntityType<? extends Horse> p_30689_, Level p_30690_) {
        super(p_30689_, p_30690_);
    }

    // 添加属性创建方法
    public static AttributeSupplier.Builder createAttributes() {
        return Horse.createLivingAttributes().add(
                Attributes.FOLLOW_RANGE, 32
        ).add(
                Attributes.MOVEMENT_SPEED, 0.2
        ); // 继承马的属性，或自定义
    }

    // 内部定义 DeferredRegister 和 RegistryObject
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Constant.MODDatas.MOD_ID);
    public static final RegistryObject<EntityType<aEntity>> A_ENTITY = ENTITIES.register("a_entity", () ->
            EntityType.Builder.of(aEntity::new, MobCategory.CREATURE)
                    .sized(1.4F, 1.6F) // 设置实体宽度和高度
                    .build("a_entity")
    );
}