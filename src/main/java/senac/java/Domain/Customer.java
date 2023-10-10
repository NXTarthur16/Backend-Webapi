package senac.java.Domain;

public class Customer {
    int id = 0;
    public static String name = "";
    public static String lastName = "";
    public static int age = 0;
    public static String address = "";
    public static String userEmail = "";
    public static String password = "";
    public static String userCpf = "";

    public Customer(){
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String name){
        this.lastName = lastName;
    }

    public int getAge(){
        return age;
    }

    public void setAge(String name){
        this.age = age;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String name){
        this.address = address;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public void setUserEmail(String name){
        this.userEmail = userEmail;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String name){
        this.password = password;
    }

    public String getUserCpf(){
        return userCpf;
    }

    public void setUserCpf(String name){
        this.userCpf = userCpf;
    }

}