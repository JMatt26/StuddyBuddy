package Study.App.controller.TOs;

public class UserTO {
    public Integer id;
    public String name;
    public String username;
    public String password;

    public UserTO(Integer id, String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
