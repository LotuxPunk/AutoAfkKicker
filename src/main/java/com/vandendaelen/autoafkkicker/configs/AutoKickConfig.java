package com.vandendaelen.autoafkkicker.configs;

import com.vandendaelen.autoafkkicker.AutoAfkKicker;
import net.minecraftforge.common.config.Config;

@Config(modid = AutoAfkKicker.MOD_ID,name = AutoAfkKicker.MOD_NAME)
public class AutoKickConfig {

    public static final Timer TIMER = new Timer();
    public static final Debugging DEBUGGING = new Debugging();
    public static final Text TEXT = new Text();


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

    public static class Debugging {
        @Config.LangKey("config.debug")
        @Config.Comment("Only to debug the mod, may spam the console a lot")
        public boolean debug = false;
    }

    public static class Text{
        @Config.LangKey("config.text.afkmessage")
        @Config.Comment("Message to show when a player is AFK or not (%s is the username)")
        public String AFKMessage = "%s is now AFK";
        @Config.LangKey("config.text.notafkmessage")
        public String noLongerAFKMessage = "%s is no longer AFK";
    }

}
