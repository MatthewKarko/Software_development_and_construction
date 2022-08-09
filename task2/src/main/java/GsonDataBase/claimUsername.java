package GsonDataBase;

import java.util.Map;

public class claimUsername {
    Map<String,Object> user;
    String token;

    public claimUsername(String token, Map<String,Object> user){
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public Map<String, Object> getUser() {
        return user;
    }
}
