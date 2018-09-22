package com.vandendaelen.autoafkkicker;

import com.vandendaelen.autoafkkicker.configs.AutoKickConfig;
import com.vandendaelen.autoafkkicker.handlers.AutoKickerServerEventHandler;
import com.vandendaelen.autoafkkicker.integrations.opencomputer.OC;
import com.vandendaelen.autoafkkicker.utils.AAKString;
import com.vandendaelen.autoafkkicker.integrations.computercraft.CC;
import dan200.computercraft.ComputerCraft;
import li.cil.oc.OpenComputers;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author LotuxPunk
 */

@Mod(modid = AutoAfkKicker.MOD_ID,name = AutoAfkKicker.MOD_NAME, version = AutoAfkKicker.VERSION, acceptableRemoteVersions = "*")
public class AutoAfkKicker {
    public static final String MOD_ID = "autoafkkicker";
    public static final String MOD_NAME = "AutoAfkKicker";
    public static final String VERSION = "0.0.5";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    @Mod.Instance(MOD_ID)
    public static AutoAfkKicker instance;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event){
        //Timers
        AutoKickerServerEventHandler.warnTimerTick = AutoKickConfig.TIMER.warnTimer*60*20;
        AutoKickerServerEventHandler.kickTimerTick = AutoKickConfig.TIMER.kickTimer*60*20;

        //Permissions
        PermissionAPI.registerNode(AAKString.Permissions.AFK_BYPASS, DefaultPermissionLevel.OP, "Allow player to bypass AFK kick");
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

        if (AutoKickConfig.DEBUGGING.debug)
            LOGGER.info("Debug ON");

        if (Loader.isModLoaded(AAKString.Integration.COMPUTER_CRAFT)) {
            CC computercraft = new CC();
            if (AutoKickConfig.DEBUGGING.debug){
                LOGGER.info("CC is loaded");
            }
        }

        if (Loader.isModLoaded(AAKString.Integration.OPEN_COMPUTER)) {
            OC opencomputer = new OC();
            if (AutoKickConfig.DEBUGGING.debug){
                LOGGER.info("OC is loaded");
            }
        }
    }
}
