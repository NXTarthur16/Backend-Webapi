package senac.java;

import senac.java.Services.WebServer;
import senac.java.Views.Telas;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Telas telas = new Telas();
        WebServer server = new WebServer();

        telas.telaInicial();
        server.apiserver();
    }
}