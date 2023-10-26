package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import senac.java.Domain.Customer;
import senac.java.Services.ResponseEndPoints;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    static ResponseEndPoints res = new ResponseEndPoints();
    private static List <Customer> usersList = new ArrayList<>();

    public static class ClienteHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if ("GET".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de Cliente - GET";
                res.enviarResponse(exchange, response, 200);
            }else if ("POST".equals(exchange.getRequestMethod())){
//                response = "Essa e a rota de Cliente - POST";
//                res.enviarResponse(exchange, response,200);
                try (InputStream requestBody = exchange.getRequestBody()){
                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                    Customer user = new Customer(
                            json.getString("name"),
                            json.getString("lastName"),
                            json.getInt("age"),
                            json.getString("address"),
                            json.getString("email"),
                            json.getString("password"),
                            json.getString("cpf")
                    );

                    usersList.add(user);

                    System.out.println("ClienteList contem: " + user.toJson());

                    response = "Dados recebidos com sucesso";

                   res.enviarResponse(exchange, response, 200);

                } catch(Exception e){
                    String resposta = e.toString();
                    res.enviarResponse(exchange, resposta, 200);
                    System.out.println("O erro foi" + e);
                }

            }else if ("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de Cliente - PUT";
                res.enviarResponse(exchange, response, 200);
            }else if ("DELETE".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de Cliente - DELETE";
                res.enviarResponse(exchange, response, 200);
            }else {
                response = "Essa e a rota de Cliente - UNDEFINED";
                res.enviarResponse(exchange, response, 200);
            }
        }
    }
}