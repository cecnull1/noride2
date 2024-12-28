package a.b.noride2;

import a.b.noride2.enchantment.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Noride2.MODID)
public class Noride2 {

    public static final String MODID = "noride2";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Noride2() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // 注册附魔
        Wufaxiacheng.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Qiangzhifeixing.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Qiaochiqudong.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Zidongyidong.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Jiaohua.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BuXiaCheng.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ZhiNengXiaCheng.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        PiaoFu.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some preinit code
        LOGGER.info("HELLO FROM PREINIT");
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("noride2", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().map(m -> m.messageSupplier().get()).collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}