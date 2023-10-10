package senac.java.Domain;

public class Salesperson {
    int id = 0;
    public String nome = "";
    public String ultimoNome = "";
    public String phoneNumber = "";
    public String salesPersonCpf = "";
    public String salesPersonEmail = "";
    public String salesPersonAddress = "";

    public Store store;

    public Salesperson() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSalesPersonCpf() {
        return salesPersonCpf;
    }

    public void setSalesPersonCpf(String salesPersonCpf) {
        this.salesPersonCpf = salesPersonCpf;
    }

    public String getSalesPersonEmail() {
        return salesPersonEmail;
    }

    public void setSalesPersonEmail(String salesPersonEmail) {
        this.salesPersonEmail = salesPersonEmail;
    }

    public String getSalesPersonAddress() {
        return salesPersonAddress;
    }

    public void setSalesPersonAddress(String salesPersonAddress) {
        this.salesPersonAddress = salesPersonAddress;
    }
}