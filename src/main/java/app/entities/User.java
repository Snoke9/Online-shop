package app.entities;

public class User {
    private static int count = 0;
    private int id;
    private String user_name;
    private String mail;
    private String password;

    public User(String user_name, String password,  String mail) {
        //НУЖЕН СПИСОК ТОВАРОВ ????
        this.id = ++count;
        this.user_name = user_name;
        this.mail = mail;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return user_name;
    }
    public void setName(String name) {
        this.user_name = name;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId() { return id; }
}
