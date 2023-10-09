package senac.java.Services;

import com.sun.net.httpserver.HttpServer; // Criar um servidor
import com.sun.net.httpserver.HttpHandler; // Receber a requisicao e programar ela (cuida da parte de ir de la para cá(abre os caminhos))
import com.sun.net.httpserver.HttpExchange; // Envia a requisicao do Front para o Back (e esse passa pelos caminhos)

import senac.java.Domain.Customer;

import java.io.IOException; //
import java.io.OutputStream; //
import java.net.InetSocketAddress; //

public class WebServer {

    static Customer cliente = new Customer();
    static HttpExchange ex; // classes abstratas sempre se inicializam se
    // ela for criada como parametro da classe ou como parametro de um metodo como está sendo criado agora

    public void apiserver() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        // localhost...
        server.createContext("/api/cliente", new ClienteHandler());
        server.createContext("/api/vendedor", new VendedorHandler());
        server.createContext("/api/produto", new ProdutoHandler());

        server.setExecutor(null);
        System.out.println("Servidor iniciado");
        server.start();
    }

    public static void enviarResponse(HttpExchange exchange, String response) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    class ClienteHandler implements HttpHandler {
        @Override // criar metodo de novo e forçar a usar esse criado(modifica o que existe)
        public void handle(HttpExchange exchange) throws IOException{

            String response = "Essa e a rota de cliente";
            enviarResponse(exchange, response);
        }
    }

    class VendedorHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException{

            String response = "Essa e a rota de vendedor";
            enviarResponse(exchange, response);
        }
    }

    class ProdutoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException{

            String response = "Essa e a rota de Produto";
            enviarResponse(exchange, response);
        }
    }
}