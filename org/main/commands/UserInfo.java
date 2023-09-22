package org.yukia.commands;

import org.yukia.species.*;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import org.javacord.api.entity.message.*;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class UserInfo extends Command {
    public UserInfo() {
        super(
                "userinfo",
                "Sends information about a user.",
                "(user mention)",
                0
        );
    }

    @Override
    public void execute(Message msg) {
        List<User> mentions = msg.getMentionedUsers();

        if (mentions.isEmpty()) {
            msg.reply("Please mention a user!");
        } else {
            User target = mentions.get(0);
            Server here = msg.getServer().orElse(null);
            EmbedBuilder embed = new EmbedBuilder()
                    .setColor(new Color(0x54a12f))
                    .setThumbnail(target.getAvatar())
                    .setDescription(
                            String.join("\n", Arrays.asList(
                                    "Name: `" + target.getDiscriminatedName() + "`",
                                    "Roles: `" + (target.getRoles((Server) here).size() - 1) + "`",
                                    "Is bot: `" + target.isBot() + "`",
                                    "Is dev: `" + target.isBotOwner() + "`"
                            ))
                    );

            msg.reply(embed);
        }
    }
}
