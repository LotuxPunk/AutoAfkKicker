package com.vandendaelen.autoafkkicker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author LotuxPunk
 */

@Mod(modid = AutoAfkKicker.MOD_ID,name = AutoAfkKicker.MOD_NAME, version = AutoAfkKicker.VERSION)
public class AutoAfkKicker {
    public static final String MOD_ID = "autoafkkicker";
    public static final String MOD_NAME = "AutoAfkKicker";
    public static final String VERSION = "0.0.1";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    @Mod.Instance(MOD_ID)
    public static AutoAfkKicker instance;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event){
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event){
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event){
    }

    @Mod.EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
        LOGGER.info("AutoAfkKicker started");
    }




}
