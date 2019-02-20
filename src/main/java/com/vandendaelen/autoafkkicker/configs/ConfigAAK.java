package com.vandendaelen.autoafkkicker.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigAAK {

    public static final ConfigAAK CONFIG_AAK;
    public static final ForgeConfigSpec CONFIG_SPEC;

    public final ForgeConfigSpec.IntValue warnTimer;
    public final ForgeConfigSpec.IntValue kickTimer;
    public final ForgeConfigSpec.BooleanValue debug;
    public final ForgeConfigSpec.ConfigValue<String> discMsg;
    public final ForgeConfigSpec.ConfigValue<String> AFKMessage;
    public final ForgeConfigSpec.ConfigValue<String> noLongerAFKMessage;

    static {
        Pair<ConfigAAK, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ConfigAAK::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG_AAK = specPair.getLeft();
    }

    public ConfigAAK(ForgeConfigSpec.Builder builder) {
        builder.push("general");
        builder.push("timers");
        warnTimer = builder
                .comment("In minute")
                .defineInRange("warnTimer", 5, 1, Integer.MAX_VALUE);
        kickTimer = builder
                .comment("In minute")
                .defineInRange("kickTimer", 5, 1, Integer.MAX_VALUE);
        builder.pop();
        debug = builder
                .comment("Only to debug the mod, may spam the console a lot")
                .define("debug", false);
        builder.push("messages");
        discMsg = builder
                .comment("Disconnect message")
                .define("discMsg","You were ejected for inactivity");
        AFKMessage = builder
                .comment("Message to show when a player is AFK (%s is the username)")
                .define("afkmessage","%s is now AFK");
        noLongerAFKMessage = builder
                .comment("Message to show when a player is not longer AFK (%s is the username)")
                .define("noafkmessage","%s is no longer AFK");
        builder.pop();
        builder.pop();
    }

    public static int getWarnTimer() {
        return CONFIG_AAK.warnTimer.get();
    }

    public static int getKickTimer() {
        return CONFIG_AAK.kickTimer.get();
    }

    public static boolean getDebug() {
        return CONFIG_AAK.debug.get();
    }

    public static String getDiscMsg() {
        return CONFIG_AAK.discMsg.get();
    }

    public static String getAFKMessage() {
        return CONFIG_AAK.AFKMessage.get();
    }

    public static String getNoLongerAFKMessage() {
        return CONFIG_AAK.noLongerAFKMessage.get();
    }
}
