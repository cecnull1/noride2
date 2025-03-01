package a.b.noride2.enchantment;

import constant.Constant;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class QiangZhiJiPao extends Enchantment {
    public QiangZhiJiPao(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Constant.MODDatas.MOD_ID);
    public static final RegistryObject<Enchantment> QIANGZHI_JI_PAO = ENCHANTMENTS.register("qiangzhijipao", () -> new QiangZhiJiPao(Rarity.COMMON, EnchantmentCategory.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS}));

}
