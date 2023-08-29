package com.su.serverutils;


import com.su.serverutils.config.ApiConfig;
import com.su.serverutils.handlers.AdvancementHandler;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ServerUtils.MODID)
public class ServerUtils {

    public static final String MODID = "serverutils";


    public ServerUtils() {
        // Register the common setup method for modloading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        ApiConfig config = new ApiConfig(builder);
        ForgeConfigSpec spec = builder.build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, spec);

    }

    private void setup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new AdvancementHandler());

    }
}
