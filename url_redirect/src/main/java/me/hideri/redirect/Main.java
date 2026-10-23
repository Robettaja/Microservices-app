package me.hideri.redirect;

import io.javalin.Javalin;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URI;

public class Main {
    static String readResponse(InputStream is) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        final StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();
    }

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

                    final JSONObject data = new JSONObject(readResponse(connection.getInputStream()));

                    if (data.getString("shortId").equals(shortId)) {
                        ctx.contentType("text/plain;charset=utf-8");
                        ctx.header("Location", data.getString("inputUrl"));
                    } else {
                        System.err.println("Param Short-Id to DB Short-Id mismatch!");
                        ctx.contentType("text/plain;charset=utf-8");
                        ctx.header("Location", "/err");
                    }
                } catch (IOException ex) {
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
