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

    private static List<Customer> customerList = new ArrayList<>();
    public static class ClienteHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if ("GET".equals(exchange.getRequestMethod())){

                List<Customer> getAllFromArray = Customer.getAllCustomer(customerList);

                if(!getAllFromArray.isEmpty()){

                    Customer customer = new Customer();

                    for(Customer customerJson : getAllFromArray){
                        System.out.println("Nome: " + customerJson.getName());
                        System.out.println("Sobrenome: " + customerJson.getLastName());
                        System.out.println("Idade: " + customerJson.getAge());
                        System.out.println("Endereço: " + customerJson.getAddress());
                        System.out.println("Email: " + customerJson.getEmail());
                        System.out.println("Cpf:" + customerJson.getCpf());

                        System.out.println("");
                    }

                    System.out.println("getallfromarray" + getAllFromArray);
                    System.out.println("customerList" + customerList);

                    response = "Dados encontrados com sucesso";
                    res.enviarResponseJson(exchange, customer.arrayToJson(getAllFromArray), 201);
                }

                else{
                    response = "Dados não encontrados";
                    res.enviarResponse(exchange, response,405);
                }
            }

            else if ("POST".equals(exchange.getRequestMethod())){
                try(InputStream requestBody = exchange.getRequestBody()){
                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                        Customer customer = new Customer(
                                json.getString("name"),
                                json.getString("lastName"),
                                json.getInt("age"),
                                json.getString("address"),
                                json.getString("email"),
                                json.getString("password"),
                                json.getString("cpf")
                        );

                    customerList.add(customer);

                    res.enviarResponseJson(exchange, customer.toJson(), 200);
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