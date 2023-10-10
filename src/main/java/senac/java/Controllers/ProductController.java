package senac.java.Controllers;

import com.sun.net.httpserver.HttpHandler; // Receber a requisicao e programar ela (cuida da parte de ir de la para cá(abre os caminhos))
import com.sun.net.httpserver.HttpExchange; // Envia a requisicao do Front para o Back (e esse passa pelos caminhos)
import senac.java.Services.ResponseEndPoints;
import org.json.JSONObject;

import java.io.IOException;

public class ProductController {
    static ResponseEndPoints res = new ResponseEndPoints();

    public static class ProdutoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if("GET".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de Produto - GET";
                res.enviarResponse(exchange, response);
            }else if("POST".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de Produto - POST";
                res.enviarResponse(exchange, response);
            }else if("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de Produto - PUT";
                res.enviarResponse(exchange, response);
            }else if("DELETE".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de Produto - DELETE";
                res.enviarResponse(exchange, response);
            }else{
                response = "Essa e a rota de Produto - UNDEFINED";
//                response = "Essa e a rota de Produto - UNDEFINED" + " O metodo utilizado foi: " + exchange.getRequestMethod();
                res.enviarResponse(exchange, response);
            }
        }
    }
}
