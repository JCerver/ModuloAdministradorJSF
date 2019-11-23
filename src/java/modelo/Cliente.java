package modelo;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int clientId;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String type;
    private int status;

    public Cliente() {
    }

    public Cliente(int userId, String name, String lastname, String email, String username, String password, String type, int status) {
        this.clientId = userId;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
        this.status = status;
    }

    

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int userId) {
        this.clientId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
