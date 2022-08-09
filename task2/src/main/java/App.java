import controller.UserFacade;
import controller.UserFacadeImpl;
import model.Offline;
import model.Online;
import view.HomePage;

public class App {

    public static void main(String[] args){
        UserFacade facade;
        Online online = new Online();
        Offline offline = new Offline();

        if (args.length != 1){
            System.out.println("Incorrect amount of arguments");
            System.exit(0);
        }

        if(args[0].equals("offline")){
            facade = new UserFacadeImpl(offline);
            HomePage page = new HomePage(facade);

            page.display();
        }

        else if (args[0].equals("online")) {
            facade = new UserFacadeImpl(online);
            HomePage page = new HomePage(facade);

            page.display();

        }
        else{
            System.out.println("API must be online or offline");
        }




    }
}
