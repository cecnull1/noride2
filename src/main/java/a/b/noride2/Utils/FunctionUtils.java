package a.b.noride2.Utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class FunctionUtils {
    public static void processRiddenEntities(Player player, EntityProcessor processor) {
        Entity currentEntity = player;
        while (currentEntity != null) {
            // 执行对当前实体的处理逻辑
            processor.process(currentEntity);

            // 获取当前实体的乘客（即当前实体所骑乘的实体）
            currentEntity = currentEntity.getVehicle();
        }
    }
}
