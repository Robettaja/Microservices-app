package me.hideri.redirect;

import io.javalin.Javalin;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URI;

public class Main {
    static void main() {
        var app = Javalin.create(config -> {
            config.routes.get("/", ctx -> {
                ctx.contentType("text/plain;charset=utf-8");
                ctx.status(403);
                ctx.result("");
            });

            config.routes.get("/{short-id}", ctx -> {
                try {
                    final String shortId = ctx.pathParam("short-id");
                    final URI api = new URI("http://localhost:8080/url/" + shortId);
                    final HttpURLConnection connection = (HttpURLConnection) api.toURL().openConnection();
                    connection.setRequestMethod("GET");

                    ctx.contentType("text/plain;charset=utf-8");
                    ctx.header("Location", connection.getResponseMessage());
                    ctx.status(302);
                    ctx.result("");
                } catch (ConnectException ex) {
                    System.err.println("Connection to DB API failed, redirecting to root.");
                    ctx.contentType("text/plain;charset=utf-8");
                    ctx.header("Location", "/");
                    ctx.status(302);
                    ctx.result("");
                }
            });
        });
        app.start(7777);
    }
}
