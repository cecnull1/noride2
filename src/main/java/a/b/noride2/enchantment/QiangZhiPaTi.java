package a.b.noride2.enchantment;

import a.b.noride2.Noride2;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class QiangZhiPaTi extends Enchantment {
    public QiangZhiPaTi(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Noride2.MODID);
    public static final RegistryObject<Enchantment> QIANGZHI_PA_TI = ENCHANTMENTS.register("qiangzhipati", () -> new QiangZhiPaTi(Rarity.COMMON, EnchantmentCategory.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS}));

}
