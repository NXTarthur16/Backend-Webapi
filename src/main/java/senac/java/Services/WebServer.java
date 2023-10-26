package senac.java.Services;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer; // Criar um servidor

import senac.java.Controllers.CustomerController;
import senac.java.Controllers.ProductController;
import senac.java.Controllers.SalesPersonController;
import senac.java.Domain.Customer;

import java.io.IOException; //
import java.net.InetSocketAddress; //

public class WebServer {


    public void apiServer() throws IOException{


        HttpServer server = HttpServer.create(new InetSocketAddress(8080),
                0);

        HttpHandler userHandler = new CustomerController.ClienteHandler();
        HttpHandler salesPersonHandler = new SalesPersonController.SalesPersonHandler();
        HttpHandler productHandler = new ProductController.ProductsHandler();

        server.createContext("/api/vendedor", exchange -> {
            configureCors(exchange);
            salesPersonHandler.handle(exchange);
        });
        server.createContext("/api/usuario",  exchange -> {
            configureCors(exchange);
            userHandler.handle(exchange);
        });

        server.createContext("/api/produtos", exchange -> {
            configureCors(exchange);
            productHandler.handle(exchange);
        });

        server.setExecutor(null);
        System.out.println("Servidor Iniciado");
        server.start();
    }

    private void configureCors(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        String requestOrigin = exchange.getResponseHeaders().getFirst("Origin");
        if (requestOrigin != null) {
            headers.set("Access-Control-Allow-Origin", requestOrigin);
        }
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods", "GET,OPTIONS,POST,PUT,DELETE");
        headers.set("Access-Control-Allow-Headers", "Content-Type, Authorization");
        headers.set("Access-Control-Allow-Credentials", "true");
        headers.set("Access-Control-Max-Age", "3600");
    }
}