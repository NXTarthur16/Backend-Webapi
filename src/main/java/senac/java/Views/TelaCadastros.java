package senac.java.Views;

import senac.java.Domain.Product;
import senac.java.Domain.Salesperson;
import senac.java.Domain.Customer;
import senac.java.Services.Roteamento;

import java.util.Scanner;

public class TelaCadastros {

    static Scanner sc = new Scanner(System.in);
    static Customer cliente = new Customer();
    static Salesperson vendedor = new Salesperson();
    static Product produto = new Product();

    static String resposta = "";
    static Roteamento rota = new Roteamento();

    public static void cadastroVendedor() {

        System.out.println("Bem vindo ao cadastro de vendedor");
        System.out.println(" ");
        System.out.println("Preencha corretamente os dados abaixo: ");
        System.out.println(" ");
        System.out.println("Digite o seu primeiro nome");
        vendedor.name = sc.nextLine();
        System.out.println(" ");
        System.out.println("Digite o seu sobrenome");
        vendedor.lastName = sc.nextLine();
        System.out.println(" ");
        System.out.println("Digite o seu telefone");
        vendedor.phoneNumber = sc.nextLine();
        System.out.println(" ");
        System.out.println("Digite o seu CPF");
        vendedor.cpf = sc.nextLine();
        System.out.println(" ");
        System.out.println("Digite o seu endereço de email");
        vendedor.email = sc.nextLine();
        System.out.println(" ");
        System.out.println("Digite o seu endereço completo");
        vendedor.address = sc.nextLine();
        System.out.println(" ");

        System.out.println("Cadastro de vendedor realizado com sucesso:");
        System.out.println("Nome: " + vendedor.name + " " + vendedor.lastName);
        System.out.println("Telefone: " + vendedor.phoneNumber);
        System.out.println("CPF: " + vendedor.cpf);
        System.out.println("E-mail: " + vendedor.email);
        System.out.println("Endereço: " + vendedor.address);

        rota.rotasReturn(resposta);
    }

    public static void cadastroCliente() {

        System.out.println("Bem vindo ao cadastro de cliente");
        System.out.println(" ");
        System.out.println("Preencha corretamente os dados abaixo: ");
        System.out.println(" ");
        System.out.println("Digite o seu primeiro nome");
        cliente.name = sc.nextLine();
        System.out.println(" ");
        System.out.println("Digite o seu sobrenome");
        cliente.lastName = sc.nextLine();
        System.out.println(" ");
        System.out.println("Digite a sua idade");
        cliente.age = Integer.parseInt(sc.nextLine());
        System.out.println(" ");
        System.out.println("Digite o seu endereço completo");
        cliente.address = sc.nextLine();
        System.out.println(" ");
        System.out.println("Digite o seu endereço de email");
        cliente.email = sc.nextLine();
        System.out.println(" ");
        System.out.println("Defina a sua senha");
        cliente.password = sc.nextLine();
        System.out.println(" ");
        System.out.println("Digite o seu CPF");
        cliente.cpf = sc.nextLine();
        System.out.println(" ");

        System.out.println("Cadastro de cliente realizado com sucesso:");
        System.out.println("Nome: " + cliente.name + " " + cliente.lastName);
        System.out.println("Idade: " + cliente.age);
        System.out.println("Endereço: " + cliente.address);
        System.out.println("E-mail: " + cliente.email);
        System.out.println("CPF: " + cliente.cpf);

        rota.rotasReturn(resposta);
    }

    public static void cadastroProduto() {

        System.out.println("Bem vindo ao cadastro de produto");
        System.out.println(" ");
        System.out.println("Preencha corretamente os dados abaixo: ");
        System.out.println(" ");
        System.out.println("Digite o nome do seu produto");
        produto.pName = sc.nextLine();
        System.out.println("Digite o preço do seu produto");
        produto.pPrice = sc.nextLine();
        System.out.println("Digite a cor do seu produto");
        produto.pColor = sc.nextLine();
        System.out.println("Digite a descrição do seu produto");
        produto.pDescription = sc.nextLine();
        System.out.println("Digite a quantidade de produto");
        produto.pQuantity = Integer.parseInt(sc.nextLine());
        System.out.println("Digite o link de img do seu produto");
        produto.img = sc.nextLine();
        System.out.println(" ");

        System.out.println("Cadastro de produto realizado com sucesso:");
        System.out.println("Nome do produto: " + produto.pName);
        System.out.println("Preço do produto: " + produto.pPrice);
        System.out.println("Cor do produto: " + produto.pColor);
        System.out.println("Descrição do produto: " + produto.pDescription);
        System.out.println("Quantidade do produto: " + produto.pQuantity);
        System.out.println("Link da imagem do produto: " + produto.img);

        rota.rotasReturn(resposta);
    }
}