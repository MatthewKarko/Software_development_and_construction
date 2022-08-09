package GsonDataBase;

public class AccountInfo {

    UserAcc user;

    public AccountInfo(UserAcc user){
        this.user = user;
    }

    public UserAcc getUserAcc() {
        return user;
    }
}
