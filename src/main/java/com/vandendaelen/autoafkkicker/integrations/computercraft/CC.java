package com.vandendaelen.autoafkkicker.integrations.computercraft;

import dan200.computercraft.ComputerCraft;

public class CC {
    public CC() {
        ComputerCraft.networkEventChannel.register(new CCPacketHandler());
    }
}
