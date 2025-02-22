package a.b.noride2.enchantment;

import a.b.noride2.constant.Constant;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Qiangzhifeixing extends Enchantment {
    public Qiangzhifeixing(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Constant.MODDatas.MOD_ID);
    public static final RegistryObject<Enchantment> QIANGZHI_FEI_XING = ENCHANTMENTS.register("qiangzhifeixing", () -> new Qiangzhifeixing(Rarity.COMMON, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST}));

}
