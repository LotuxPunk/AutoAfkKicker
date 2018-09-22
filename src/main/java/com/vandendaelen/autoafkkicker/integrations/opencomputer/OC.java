package com.vandendaelen.autoafkkicker.integrations.opencomputer;

import li.cil.oc.OpenComputers;

public class OC {
    public OC() {
        OpenComputers.channel().register(new OCPacketHandler());
    }
}
