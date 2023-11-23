package senac.java.DAL;

import senac.java.Services.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDal {

    public Connection conectar(){
        Connection conexao = null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=pi;trustServerCertificate=true";
            String usuario = "user";
            String senha = "123456";

            conexao = DriverManager.getConnection(url, usuario, senha);

            if(conexao != null){
                return conexao;
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("O Erro foi: " + e);

        }
//        finally{
//            try {
//                if (conexao != null && !conexao.isClosed()){
//                    conexao.close();
//                }
//            }catch(SQLException e){
//                System.out.println("O erro no finaly foi: " + e);
//            }
//        }
        return conexao;
    }

    //Inserir - Create
    public int inserirProduto(String pName, String pPrice, String pColor,
                              String pDescription, int pQuantity, String img) throws SQLException {
        String sql = "INSERT INTO Product (pName, pPrice, pColor, pDescription, pQuantity, img) VALUES(?, ?, ?, ?, ?, ?)";
        int linhasAfetadas = 0;
        Connection conexao = conectar();

        try (PreparedStatement statement = conectar().prepareStatement(sql)) {
            statement.setString(1, pName);
            statement.setString(2, pPrice);
            statement.setString(3, pColor);
            statement.setString(4, pDescription);
            statement.setInt(5, pQuantity);
            statement.setString(6, img);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");

            conexao.close();
            return linhasAfetadas;
        } catch (SQLException e) {
            System.out.println("O Erro na Inserção de dados foi: " + e);
            conexao.close();
        }
        conexao.close();
        return linhasAfetadas;
    }

    public ResultSet listarProduto() throws SQLException {
        String sql = "SELECT * FROM Product";
        ResultSet result = null;

        try (PreparedStatement statement = conectar().prepareStatement(sql)) {
            result = statement.executeQuery();

            System.out.println("Listagem dos produtos: ");

            while (result.next()) {
                int id = result.getInt("id");
                String pName = result.getString("pName");
                String pPrice = result.getString("pPrice");
                String pColor = result.getString("pColor");
                String pDescription = result.getString("pDescription");
                int pQuantity = result.getInt("pQuantity");
                String img = result.getString("img");

                System.out.println("id: " + id);
                System.out.println("nome: " + pName);
                System.out.println("preço: " + pPrice);
                System.out.println("Cor: " + pColor);
                System.out.println("descrição: " + pDescription);
                System.out.println("quantidade: " + pQuantity);
                System.out.println("img: " + img);
                System.out.println(" ");

            }
            return result;

        } catch (SQLException e) {
            System.out.println("O Erro na Listagem de dados foi: " + e);
        }
        return result;

    }

    public int atualizarProduto(String pName, String pPrice, String pColor,
                                String pDescription, int pQuantity, String img, int id) throws SQLException {
        String sql = "UPDATE Product SET pName = ?, pPrice = ?, pColor = ?, pDescription = ?, pQuantity = ?, img = ?< WHERE id = ?";
        int linhasAfetadas = 0;

        try (PreparedStatement statement = conectar().prepareStatement(sql)) {
            statement.setString(1, pName);
            statement.setString(2, pPrice);
            statement.setString(3, pColor);
            statement.setString(4, pDescription);
            statement.setInt(5, pQuantity);
            statement.setString(6, img);
            statement.setInt(7, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");

            return linhasAfetadas;

        } catch (SQLException e) {
            System.out.println("O Erro na Atualização de dados foi: " + e);
        }
        return linhasAfetadas;
    }

    public int excluirProduto(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE id = ?";
        int linhasAfetadas = 0;

        try (PreparedStatement statement = conectar().prepareStatement(sql)) {
            statement.setInt(1, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");

            return linhasAfetadas;
        } catch (SQLException e) {
            System.out.println("O Erro na inserção de dados foi: " + e);
        }
        return linhasAfetadas;
    }
}