package senac.java.Services;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseEndPoints {
    public static void enviarResponse(HttpExchange exchange, String response) throws IOException {

        exchange.getResponseHeaders().set("Content-Type", "text/plain");

        exchange.sendResponseHeaders(200, response.getBytes("UTF-8").length);

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static void enviarResponseJson(HttpExchange exchange, JSONObject response) throws IOException {

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        byte[] responseBytes = response.toString().getBytes("UTF-8");

        exchange.sendResponseHeaders(200, responseBytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }

}