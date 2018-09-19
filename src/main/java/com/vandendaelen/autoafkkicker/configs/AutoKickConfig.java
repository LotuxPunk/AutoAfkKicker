package com.vandendaelen.autoafkkicker.configs;

import com.vandendaelen.autoafkkicker.AutoAfkKicker;
import net.minecraftforge.common.config.Config;

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

        @Config.LangKey("config.disconnect.msg")
        public String discMsg = "You were ejected for inactivity";
    }

}
