package Study.App.controller.TOs;

public class UserTORequest {
    public String name;
    public String username;
    public String password;

    public UserTORequest(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
