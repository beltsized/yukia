package org.yukia.commands;

import org.yukia.species.*;
import org.javacord.api.entity.message.*;

public class Ping extends Command {
    public Ping() {
        super(
                "ping",
                "Pong!",
                "()",
                0
        );
    }

    @Override
    public void execute(Message msg) {
        msg.reply("Pong!");
    }
}
