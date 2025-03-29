package a.b.noride2.enchantment

import constant.Constant
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

class Enchantments {
    // 不下沉
    class BuXiaCheng(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val BUXIA_CHENG: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "buxiacheng"
            ) {
                BuXiaCheng(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_CHEST,
                    arrayOf(EquipmentSlot.CHEST)
                )
            }
        }
    }
    // 船滑
    class ChuangHua(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val CHUANG_HUA: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "chuanghua"
            ) {
                ChuangHua(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_LEGS,
                    arrayOf(EquipmentSlot.LEGS)
                )
            }
        }
    }
    // 船移
    class ChuangYi(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val CHUANG_YI: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "chuangyi"
            ) {
                ChuangYi(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_LEGS,
                    arrayOf(EquipmentSlot.LEGS)
                )
            }
        }
    }
    // 脚滑
    class Jiaohua(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val JIAOHUA: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "jiaohua"
            ) {
                Jiaohua(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_FEET,
                    arrayOf(EquipmentSlot.FEET)
                )
            }
        }
    }
    // 飘浮
    class PiaoFu(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val PIAOFU: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "piaofu"
            ) {
                PiaoFu(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_CHEST,
                    arrayOf(EquipmentSlot.CHEST)
                )
            }
        }
    }
    // 强制鞘翅飞行
    class Qiangzhifeixing(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val QIANGZHI_FEI_XING: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "qiangzhifeixing"
            ) {
                Qiangzhifeixing(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_CHEST,
                    arrayOf(EquipmentSlot.CHEST)
                )
            }
        }
    }
    // 强制疾跑
    class QiangZhiJiPao(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val QIANGZHI_JI_PAO: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "qiangzhijipao"
            ) {
                QiangZhiJiPao(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_LEGS,
                    arrayOf(EquipmentSlot.LEGS)
                )
            }
        }
    }
    // 强制爬梯
    class QiangZhiPaTi(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val QIANGZHI_PA_TI: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "qiangzhipati"
            ) {
                QiangZhiPaTi(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_LEGS,
                    arrayOf(EquipmentSlot.LEGS)
                )
            }
        }
    }
    // 强制游泳
    class QiangZhiYouYong(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val QIANGZHI_YOU_YONG: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "qiangzhiyouyong"
            ) {
                QiangZhiYouYong(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_CHEST,
                    arrayOf(EquipmentSlot.CHEST)
                )
            }
        }
    }
    // 鞘翅驱动
    class Qiaochiqudong(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        override fun getMaxLevel(): Int {
            return 10
        }

        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val QIAOCHI_QU_DONG: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "qiaochiqudong"
            ) {
                Qiangzhifeixing(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_CHEST,
                    arrayOf(EquipmentSlot.CHEST)
                )
            }
        }
    }
    // 无法下乘
    class Wufaxiacheng :
        Enchantment(Rarity.COMMON, EnchantmentCategory.ARMOR_LEGS, arrayOf(EquipmentSlot.LEGS)) {
        override fun isCurse(): Boolean {
            return true
        }

        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val WUFA_XIA_CHENG: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "wufaxiacheng"
            ) { Wufaxiacheng() }
        }
    }
    // 只能下沉
    class ZhiNengXiaCheng(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val ZHINENG_XIA_CHENG: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "zhinengxiacheng"
            ) {
                ZhiNengXiaCheng(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_CHEST,
                    arrayOf(EquipmentSlot.CHEST)
                )
            }
        }
    }
    // 自动移动
    class Zidongyidong(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        override fun getMaxLevel(): Int {
            return 10
        }

        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val ZIDONG_YI_DONG: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "zidongyidong"
            ) {
                Zidongyidong(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_LEGS,
                    arrayOf(EquipmentSlot.LEGS)
                )
            }
        }
    }
    // 自由穿行
    class ZiYouChuangXing(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
        Enchantment(rarity, category, slots) {
        companion object {
            val ENCHANTMENTS: DeferredRegister<Enchantment> =
                getDeferredRegister()
            val ZIYOU_CHUANGXING: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                "ziyouchuangxing"
            ) {
                ZiYouChuangXing(
                    Rarity.COMMON,
                    EnchantmentCategory.ARMOR_CHEST,
                    arrayOf(EquipmentSlot.CHEST)
                )
            }
        }
    }
    // 不在水中
    class NotInWater(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
            Enchantment(rarity, category, slots) {
                companion object {
                    val ENCHANTMENTS: DeferredRegister<Enchantment> =
                        getDeferredRegister()
                    val NOT_IN_WATER: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                        "notinwater"
                    ) {
                        NotInWater(
                            Rarity.COMMON,
                            EnchantmentCategory.ARMOR_CHEST,
                           arrayOf(EquipmentSlot.CHEST)
                        )
                    }
                }
    }
    // 不在熔岩中
    class NotInLava(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
            Enchantment(rarity, category, slots) {
                companion object {
                    val ENCHANTMENTS: DeferredRegister<Enchantment> =
                        getDeferredRegister()

                    val NOT_IN_LAVA: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                        "notinlava"
                    ) {
                        NotInLava(
                            Rarity.COMMON,
                            EnchantmentCategory.ARMOR_CHEST,
                            arrayOf(EquipmentSlot.CHEST)
                        )
                    }
                }
            }
    // 在水中
    class InWater(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
            Enchantment(rarity, category, slots) {
                companion object {
                    val ENCHANTMENTS: DeferredRegister<Enchantment> =
                        getDeferredRegister()
                    val IN_WATER: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                        "inwater"
                    ) {
                        InWater(
                            Rarity.COMMON,
                            EnchantmentCategory.ARMOR_CHEST,
                            arrayOf(EquipmentSlot.CHEST)
                        )
                    }
                }
            }
    // 在熔岩中
    class InLava(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
            Enchantment(rarity, category, slots) {
                companion object {
                    val ENCHANTMENTS: DeferredRegister<Enchantment> =
                        getDeferredRegister()
                    val IN_LAVA: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                        "inlava"
                    ){
                        InLava(
                            Rarity.COMMON,
                            EnchantmentCategory.ARMOR_CHEST,
                            arrayOf(EquipmentSlot.CHEST)
                        )
                    }
                }
            }
    // 强制创造飞行
    class QiangZhiCFly(rarity: Rarity, category: EnchantmentCategory, slots: Array<EquipmentSlot?>) :
            Enchantment(rarity, category, slots) {
                companion object {
                    val ENCHANTMENTS: DeferredRegister<Enchantment> =
                        getDeferredRegister()
                    val QIANGZHI_C_FLY: RegistryObject<Enchantment> = ENCHANTMENTS.register(
                        "qiangzhicfly"
                    ) {
                        QiangZhiCFly(
                            Rarity.COMMON,
                            EnchantmentCategory.ARMOR_CHEST,
                            arrayOf(EquipmentSlot.CHEST)
                        )
                    }
                }
            }
}

fun getDeferredRegister(): DeferredRegister<Enchantment> =
    DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Constant.MODDatas.MOD_ID)