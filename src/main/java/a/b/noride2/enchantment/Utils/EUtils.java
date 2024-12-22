package a.b.noride2.enchantment.Utils; // 假设这是你的工具类所在的包

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EUtils {

    /**
     * 检查玩家是否穿戴了带有特定附魔的物品。
     *
     * @param player 玩家实体
     * @param enchantment 要检查的附魔
     * @return 如果玩家穿戴了带有指定附魔的物品，则返回 true；否则返回 false。
     */
    public static boolean hasSpecificEnchantment(Player player, Enchantment enchantment) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack itemStack = player.getItemBySlot(slot);
            if (!itemStack.isEmpty() && EnchantmentHelper.getItemEnchantmentLevel(enchantment, itemStack) > 0) {
                return true;
            }
        }
        return false;
    }

    // 你可以根据需要添加更多的工具方法
}