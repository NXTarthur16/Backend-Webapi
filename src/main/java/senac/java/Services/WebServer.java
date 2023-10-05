package senac.java.Services;

import com.sun.net.httpserver.HttpServer; // Criar um servidor
import com.sun.net.httpserver.HttpHandler; // Receber a requisicao e programar ela (cuida da parte de ir de la para cá(abre os caminhos))
import com.sun.net.httpserver.HttpExchange; // Envia a requisicao do Front para o Back (e esse passa pelos caminhos)

import senac.java.Domain.Users;

import java.io.IOException; //
import java.io.OutputStream; //
import java.net.InetSocketAddress; //

public class WebServer {

    static Users cliente = new Users();
    static HttpExchange ex; // classes abstratas sempre se inicializam se
    // ela for criada como parametro da classe ou como parametro de um metodo como está sendo criado agora

    public static void apiserver() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/teste", WebServer::chamar);

        server.setExecutor(null);
        System.out.println("Servidor iniciado");
        server.start();
    }

    public static void chamar(HttpExchange exchange) throws IOException {

        if ("GET".equals(exchange.getRequestMethod())) {
            String response = "Cheguei no método chamar";
            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            String response = "Método não implementado";

            exchange.sendResponseHeaders(405, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}