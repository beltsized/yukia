package org.yukia.commands;

import org.javacord.api.entity.message.Message;

import org.json.*;

import org.yukia.species.Command;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Neko extends Command {
    public Neko() {
        super("neko", "Sends a random neko image.", "()", 0);
    }

    @Override
    public void execute(Message msg) throws IOException, InterruptedException {
        HttpClient http = this.getHttpClient();
        URI uri = URI.create("https://nekos.life/api/v2/img/neko");
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(uri)
                .header("accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());

        msg.reply((String) json.get("url"));
    }
}
