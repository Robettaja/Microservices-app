package me.hideri.redirect;

import io.javalin.Javalin;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URI;

public class Main {
    static void main() {
        var app = Javalin.create(config -> {
            config.routes.get("/", ctx -> {
                ctx.contentType("text/html;charset=utf-8");
                ctx.status(200);
                ctx.result("""
                        <!DOCTYPE html>
                        <html>
                            <head>
                            <title>Redirector Root</title>
                            </head>
                            <body>
                                <div>
                                    <p style="font-weight: bold; line-height: 0.5;">Endpoints:</p>
                                    <p style="line-height: 0.5;">- /s/{short-id}</p>
                                    <p style="line-height: 0.5;">- /err</p>
                                </div>
                            </body>
                        </html>
                        """);
            });

            config.routes.get("/err", ctx -> {
                ctx.contentType("text/html;charset=utf-8");
                ctx.status(200);
                ctx.result("""
                        <!DOCTYPE html>
                        <html>
                            <head>
                                <title>...</title>
                            </head>
                            <body>
                                <p>Cannot redirect</p>
                            </body>
                        </html>
                        """);
            });

            config.routes.get("/s/{short-id}", ctx -> {
                try {
                    final String shortId = ctx.pathParam("short-id");
                    final URI api = new URI("http://url_shortener:8080/url?shortId=" + shortId);
                    final HttpURLConnection connection = (HttpURLConnection) api.toURL().openConnection();
                    connection.setRequestMethod("GET");

                    final JSONObject data = new JSONObject(connection.getResponseMessage());

                    if (data.getString("shortId").equals(shortId)) {
                        ctx.contentType("text/plain;charset=utf-8");
                        ctx.header("Location", data.getString("inputUrl"));
                    } else {
                        System.err.println("Param Short-Id to DB Short-Id mismatch!");
                        ctx.contentType("text/plain;charset=utf-8");
                        ctx.header("Location", "/err");
                    }
                } catch (ConnectException ex) {
                    System.err.println("Connection to DB API failed, redirecting...");
                    ctx.contentType("text/plain;charset=utf-8");
                    ctx.header("Location", "/err");
                }

                ctx.status(302);
                ctx.result("");
            });
        });
        app.start(7777);
    }
}
