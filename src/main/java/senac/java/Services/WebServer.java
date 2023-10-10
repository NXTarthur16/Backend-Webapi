package senac.java.Services;

import com.sun.net.httpserver.HttpServer; // Criar um servidor

import senac.java.Controllers.CustomerController;
import senac.java.Controllers.ProductController;
import senac.java.Controllers.SalesPersonController;
import senac.java.Domain.Customer;

import java.io.IOException; //
import java.net.InetSocketAddress; //

public class WebServer {
    static Customer cliente = new Customer();


//    static HttpExchange ex; // classes abstratas sempre se inicializam se
    // ela for criada como parametro da classe ou como parametro de um metodo como está sendo criado agora


    public void apiserver() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        // localhost...
        server.createContext("/api/cliente", new CustomerController.ClienteHandler());  // /api/cliente = controlador de informação
        server.createContext("/api/vendedor", new SalesPersonController.VendedorHandler());
        server.createContext("/api/produto", new ProductController.ProdutoHandler());

        server.setExecutor(null);
        System.out.println("Servidor iniciado");
        server.start();
    }
}