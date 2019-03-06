package com.vandendaelen.autoafkkicker;

import com.vandendaelen.autoafkkicker.configs.ConfigAAK;
import com.vandendaelen.autoafkkicker.handlers.AAKEventsHandler;
import com.vandendaelen.autoafkkicker.utils.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
public class AutoAfkKicker {
    public static final Logger LOGGER = LogManager.getLogger();
    public static AutoAfkKicker INSTANCE;

    public AutoAfkKicker() {
        INSTANCE = this;
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigAAK.CONFIG_SPEC);
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        AAKEventsHandler.warnTimerTick = ConfigAAK.getWarnTimer()*60*20;
        AAKEventsHandler.kickTimerTick = ConfigAAK.getKickTimer()*60*20;
    }
}
