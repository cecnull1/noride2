package a.b.noride2_changed_compatible.enchantment

import constant.Constant
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

class QiangZhiTransfur(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
    Enchantment(rarity, category, slots) {
    companion object {
        val ENCHANTMENTS: DeferredRegister<Enchantment> =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Constant.MODDatas.MOD_ID)

        @JvmField
        val QIANG_ZHI_TRANSFUR: RegistryObject<Enchantment> = ENCHANTMENTS.register(
            "qiang_zhi_transfur"
        ) {
            QiangZhiTransfur(
                Rarity.COMMON,
                EnchantmentCategory.ARMOR_CHEST,
                arrayOf(EquipmentSlot.CHEST)
            )
        }
    }
}