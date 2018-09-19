package com.vandendaelen.autoafkkicker;

import net.minecraftforge.fml.common.Mod;

/**
 * @author LotuxPunk
 */

@Mod(modid = this.MOD_ID,name = this.MOD_NAME, version = this.VERSION, dependencies = this.THIS_DEP)
public class AutoAfkKicker {
    public static final String MOD_ID = "autoafkkicker";
    public static final String MOD_NAME = "AutoAfkKicker";
    public static final String VERSION = "0.0.1";
    public static final String THIS_DEP = "required-after:" + MOD_ID + "@[" + VERSION + ",)";


}
