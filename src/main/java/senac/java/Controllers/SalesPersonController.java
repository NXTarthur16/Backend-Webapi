package senac.java.Controllers;

import com.sun.net.httpserver.HttpHandler; // Receber a requisicao e programar ela (cuida da parte de ir de la para cá(abre os caminhos))
import com.sun.net.httpserver.HttpExchange; // Envia a requisicao do Front para o Back (e esse passa pelos caminhos)
import senac.java.Services.ResponseEndPoints;
import org.json.JSONObject;

import java.io.IOException;

public class SalesPersonController {
    static ResponseEndPoints res = new ResponseEndPoints();

    public static class VendedorHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            // sempre a chave e o valor
            JSONObject json1 = new JSONObject();
            json1.put("chave1", "valor1");
            json1.put("chave2", "valor2");

            JSONObject json2 = new JSONObject();
            json2.put("chave3", "valor3");
            json2.put("chave4", "valor4");

            if("GET".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de vendedor - GET";
                res.enviarResponseJson(exchange, json1);
//                res.enviarResponse(exchange, response);
            }else if("POST".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de vendedor - POST";
                res.enviarResponse(exchange, response);
            }else if("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de vendedor - PUT";
                res.enviarResponse(exchange, response);
            }else if("DELETE".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de vendedor - DELETE";
                res.enviarResponse(exchange, response);
            }else{
                response = "Essa e a rota de Vendedor - UNDEFINED ";
//                response = "Essa e a rota de Vendedor - UNDEFINED" + " O metodo utilizado foi: " + exchange.getRequestMethod();
                res.enviarResponse(exchange, response);
            }
        }
    }
}