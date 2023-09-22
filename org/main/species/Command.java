package org.yukia.species;

import org.javacord.api.entity.message.Message;

import java.io.IOException;
import java.net.http.HttpClient;

public abstract class Command {
    private final String name;
    private final String description;
    private final String usage;
    private final int clearanceLevelRequired;
    private final HttpClient http = HttpClient.newHttpClient();

    public Command(String n, String d, String u, int cLR) {
        name = n;
        description = d;
        usage = u;
        clearanceLevelRequired = cLR;
    }

    public String getUsage() {
        return usage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getClearanceLevelRequired() {
        return clearanceLevelRequired;
    }

    public HttpClient getHttpClient() {
        return http;
    }

    public void execute() {
        System.out.println("Hello, sweet world.");
    }

    public String toString() {
        return "!" + name;
    }

    public abstract void execute(Message msg) throws IOException, InterruptedException;
}
