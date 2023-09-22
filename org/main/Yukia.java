package org.yukia;

import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.Message;

import java.util.ArrayList;

import org.yukia.species.*;

public class Yukia {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
        DiscordApi api = new DiscordApiBuilder()
                .setToken("MTEyMjM2MDMzNjQyNzc5MDM4Nw.Gev-1y.4wXOBSE2Qqr3S9yms2DSQkziTaRT4XfQQDRqU4")
                .addIntents(Intent.MESSAGE_CONTENT, Intent.GUILD_MEMBERS)
                .login().join();

        ArrayList<Command> commands = new ArrayList<>();

        String curPath = new File("").getAbsolutePath();
        String cmdDirPath = curPath + "\\src\\main\\java\\org\\yukia\\commands";

        File cmdDir = new File(cmdDirPath);
        File[] cmdDirFiles = cmdDir.listFiles();

        URL[] urls = {cmdDir.toURI().toURL()};
        URLClassLoader ucLoader = new URLClassLoader(urls);

        for (File f : cmdDirFiles) {
            String fName = f.getName().replaceAll(".java", "");
            Class cmd = ucLoader.loadClass("org.yukia.commands." + fName);

            commands.add((Command) cmd.newInstance());
        }

        System.out.println(commands.size() + " commands loaded.");

        api.addMessageCreateListener(event -> {
            Message msg = event.getMessage();

            if (msg.getServer().isPresent()) {
                String msgContent = msg.getContent();
                String commandName = msgContent.split(" ")[0];

                commands.forEach(cmd -> {
                    if (commandName.equalsIgnoreCase(cmd.toString())) {
                        try {
                            cmd.execute(msg);
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }
}
