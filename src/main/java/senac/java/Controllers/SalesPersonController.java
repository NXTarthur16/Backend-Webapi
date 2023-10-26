package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import senac.java.Domain.Salesperson;
import senac.java.Services.ResponseEndPoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class SalesPersonController {

    static ResponseEndPoints res = new ResponseEndPoints();

    private static List<Salesperson> salespersonList = new ArrayList<>();
    public static class SalesPersonHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if ("GET".equals(exchange.getRequestMethod())){

                List<Salesperson> getAllFromArray = Salesperson.getAllSalesperson(salespersonList);

                if(!getAllFromArray.isEmpty()){

                    Salesperson salesperson = new Salesperson();

                    for(Salesperson salespersonJson : getAllFromArray){
                        System.out.println("Name: " + salespersonJson.getName());
                        System.out.println("Last Name: " + salespersonJson.getLastName());
                        System.out.println("Cpf" + salespersonJson.getCpf());
                        System.out.println("Email: " + salespersonJson.getEmail());
                        System.out.println("Número de telefone: " + salespersonJson.getPhoneNumber());
                        System.out.println("Address: " + salespersonJson.getAddress());
                        System.out.println("");
                    }

                    System.out.println("getallfromarray"+getAllFromArray);
                    System.out.println("salespersonList"+salespersonList);

                    response = "Dados encontrados com sucesso";
                    res.enviarResponseJson(exchange, salesperson.arrayToJson(getAllFromArray), 201);
                }

                else{
                    response = "Dados não encontrados";
                    res.enviarResponse(exchange, response,405);
                }
            }

            else if ("POST".equals(exchange.getRequestMethod())){
                try(InputStream requestBody = exchange.getRequestBody()){
                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                    Salesperson salesperson = new Salesperson(
                            json.getString("name"),
                            json.getString("lastName"),
                            json.getString("phoneNumber"),
                            json.getString("cpf"),
                            json.getString("email"),
                            json.getString("address")
                    );

                    salespersonList.add(salesperson);

                    res.enviarResponseJson(exchange, salesperson.toJson(), 200);
                }catch(Exception e){
                    response = e.toString();

                    System.out.println(response);
                    System.out.println("---------");

                    res.enviarResponse(exchange, response, 200);
                }
            }

            else if ("OPTIONS".equals(exchange.getRequestMethod())){
                exchange.sendResponseHeaders(204,-1);
                exchange.close();
                return;
            }
            else if ("DELETE".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de vendedor - DELETE";
                res.enviarResponse(exchange, response, 200);
            }
            else if ("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de vendedor - PUT";
                res.enviarResponse(exchange, response, 200);
            }
            else {
                response = "nao definido." + "O metodo utilizado foi: " + exchange.getRequestMethod() + " So aceitamos get, put, post e delete";
                res.enviarResponse(exchange, response, 405);
            }
        }
    }
}