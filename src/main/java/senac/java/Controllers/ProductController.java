package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import senac.java.DAL.ProductDal;
import senac.java.Domain.Product;
import senac.java.Services.ResponseEndPoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class ProductController {

    public static String response = "";
    static ResponseEndPoints res = new ResponseEndPoints();

    private static List<Product> productList = new ArrayList<>();
    public static class ProductsHandler implements HttpHandler {

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
        ProductDal productDal = new ProductDal();

        List<Product> getAllFromArray = Product.getAllProduct(productList);

        if(!getAllFromArray.isEmpty()){
            Product product = new Product();
            for(Product productJson : getAllFromArray){
                System.out.println("Nome: " + productJson.getpName());
                System.out.println("Preço: " + productJson.getpPrice());
                System.out.println("Cor: " + productJson.getpColor());
                System.out.println("Descrição: " + productJson.getpDescription());
                System.out.println("Quantidade: " + productJson.getpQuantity());
                System.out.println("img:" + productJson.getImg());

                System.out.println(" ");
            }

            try{
                productDal.listarProduto();
            }catch(Exception e){
                System.out.println("O erro foi: " + e);
            }

            System.out.println("getallfromarray" + getAllFromArray);
            System.out.println("productList" + productList);

            response = "Dados encontrados com sucesso";
            res.enviarResponseJson(exchange, product.arrayToJson(getAllFromArray), 201);
        }else{
            response = "Dados não encontrados";
            res.enviarResponse(exchange, response,405);
        }
    }

    public static void doPost(HttpExchange exchange) throws IOException{
        ProductDal productDal = new ProductDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            Product product = new Product(
                    json.getString("pName"),
                    json.getString("pPrice"),
                    json.getString("pColor"),
                    json.getString("pDescription"),
                    json.getInt("pQuantity"),
                    json.getString("img")
            );

            productList.add(product);

            resp = productDal.inserirProduto(product.pName, product.pPrice, product.pColor,
                    product.pDescription, product.pQuantity, product.img);

            if(resp == 0){
                response = "Houve um problema ao criar produto";
            }else{
                response = "Produto criado com sucesso";
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
        ProductDal productDal = new ProductDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            int id = Integer.parseInt(json.getString("id"));

            resp = productDal.excluirProduto(id);

            if(resp == 0){
                response = "Houve um problema ao deletar produto";
            }else{
                response = "Usuário deletado com sucesso";
            }

            res.enviarResponse(exchange, response, 200);
        }catch(Exception e){
            response = e.toString();

            System.out.println(response);
            System.out.println("---------");

        }

        response = "Essa e a rota de product - DELETE";
        res.enviarResponse(exchange, response, 200);
    }

    public static void doPut(HttpExchange exchange) throws IOException{
        ProductDal productDal = new ProductDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            int id = Integer.parseInt(json.getString("id"));

            Product product = new Product(
                    json.getString("pName"),
                    json.getString("pPrice"),
                    json.getString("pColor"),
                    json.getString("pDescription"),
                    json.getInt("pQuantity"),
                    json.getString("img")
            );

            productList.add(product);

            resp = productDal.atualizarProduto(product.pName, product.pPrice, product.pColor,
                    product.pDescription, product.pQuantity, product.img, id);

            if(resp == 0){
                response = "Houve um problema ao atualizar usuário";
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