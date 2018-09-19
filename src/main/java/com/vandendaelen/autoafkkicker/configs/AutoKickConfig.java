package com.vandendaelen.autoafkkicker.configs;

import com.vandendaelen.autoafkkicker.AutoAfkKicker;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = AutoAfkKicker.MOD_ID)
@Config(modid = AutoAfkKicker.MOD_ID,name = "AutoAfkKicker")
public class AutoKickConfig {

    @Config.LangKey("config.entity")
    public static final Timer AfkTimer = new Timer();

    public static class Timer{
        @Config.LangKey("config.timer.warn")
        @Config.Comment("In minute")
        public int warnTimer= 5;

        @Config.LangKey("config.timer.kick")
        @Config.Comment("In minute")
        public int kickTimer= 10;
    }

    @Mod.EventBusSubscriber(modid = AutoAfkKicker.MOD_ID)
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(AutoAfkKicker.MOD_ID)) {
                ConfigManager.sync(AutoAfkKicker.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }

}
