package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import senac.java.DAL.SalesDal;
import senac.java.Domain.Sales;
import senac.java.Services.ResponseEndPoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class SalesController {

    public static String response = "";
    static ResponseEndPoints res = new ResponseEndPoints();

    private static List<Sales> salesList = new ArrayList<>();
    public static class SalesHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            if ("GET".equals(exchange.getRequestMethod())){
                doGet(exchange);
            }
            else if ("POST".equals(exchange.getRequestMethod())){
                doPost(exchange);
            }
            else if ("DELETE".equals(exchange.getRequestMethod())) {
                doDelete(exchange);
            }
            else if ("PUT".equals(exchange.getRequestMethod())){
                doPut(exchange);
            }
            else if ("OPTIONS".equals(exchange.getRequestMethod())){
                doOptions(exchange);
            }
            else {
                response = "nao definido." + "O metodo utilizado foi: " + exchange.getRequestMethod() + " So aceitamos get, put, post e delete";
                res.enviarResponse(exchange, response, 405);
            }
        }
    }

    public static void doGet(HttpExchange exchange) throws IOException{
        SalesDal salesDal = new SalesDal();

        List<Sales> getAllFromArray = Sales.getAllSales(salesList);

        if(!getAllFromArray.isEmpty()){
            Sales sales = new Sales();
            for(Sales salesJson : getAllFromArray){
                System.out.println("Image: " + salesJson.getImage());
                System.out.println("Titulo: " + salesJson.getTitulo());
                System.out.println("Subtitulo: " + salesJson.getSubtitulo());

                System.out.println(" ");
            }

            try{
                salesDal.listarVendas();
            }catch(Exception e){
                System.out.println("O erro foi: " + e);
            }

            System.out.println("getallfromarray" + getAllFromArray);
            System.out.println("salesList" + salesList);

            response = "Dados encontrados com sucesso";
            res.enviarResponseJson(exchange, sales.arrayToJson(getAllFromArray), 201);
        }else{
            response = "Dados não encontrados";
            res.enviarResponse(exchange, response,405);
        }
    }

    public static void doPost(HttpExchange exchange) throws IOException{
        SalesDal salesDal = new SalesDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            Sales sales = new Sales(
                    json.getString("image"),
                    json.getString("titulo"),
                    json.getString("subtitulo")
            );

            salesList.add(sales);

            resp = salesDal.inserirVendas(sales.image, sales.titulo, sales.subtitulo);

            if(resp == 0){
                response = "Houve um problema ao criar venda";
            }else{
                response = "Venda criada com sucesso";
            }

            res.enviarResponse(exchange, response, 200);
        }catch(Exception e){
            response = e.toString();

            System.out.println(response);
            System.out.println("---------");

            res.enviarResponse(exchange, response, 200);
        }
    }

    public static void doDelete(HttpExchange exchange) throws IOException{
        SalesDal salesDal = new SalesDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            int id = Integer.parseInt(json.getString("id"));

            resp = salesDal.excluirVendas(id);

            if(resp == 0){
                response = "Houve um problema ao deletar venda";
            }else{
                response = "Venda deletada com sucesso";
            }

            res.enviarResponse(exchange, response, 200);
        }catch(Exception e){
            response = e.toString();

            System.out.println(response);
            System.out.println("---------");

        }

        response = "Essa e a rota de sales - DELETE";
        res.enviarResponse(exchange, response, 200);
    }

    public static void doPut(HttpExchange exchange) throws IOException{
        SalesDal salesDal = new SalesDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            int id = Integer.parseInt(json.getString("id"));

            Sales sales = new Sales(
                    json.getString("image"),
                    json.getString("titulo"),
                    json.getString("subtitulo")
            );

            salesList.add(sales);

            resp = salesDal.atualizarVendas(sales.image, sales.titulo, sales.subtitulo, id);

            if(resp == 0){
                response = "Houve um problema ao atualizar venda";
            }else{
                response = "Usuário atualizado com sucesso";
            }

            res.enviarResponse(exchange, response, 200);
        }catch(Exception e){
            response = e.toString();

            System.out.println(response);
            System.out.println("---------");

            res.enviarResponse(exchange, response, 200);
        }
    }

    public static void doOptions(HttpExchange exchange) throws IOException{
        exchange.sendResponseHeaders(204,-1);
        exchange.close();
        return;
    }
}