package au.edu.sydney.soft3202.reynholm.erp.billingsystem;

import au.edu.sydney.soft3202.reynholm.erp.cheatmodule.ERPCheatFactory;
import au.edu.sydney.soft3202.reynholm.erp.client.ClientReporting;
import au.edu.sydney.soft3202.reynholm.erp.compliance.ComplianceReporting;
import au.edu.sydney.soft3202.reynholm.erp.permissions.AuthToken;
import au.edu.sydney.soft3202.reynholm.erp.permissions.AuthenticationModule;
import au.edu.sydney.soft3202.reynholm.erp.permissions.AuthorisationModule;
import au.edu.sydney.soft3202.reynholm.erp.project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class BSFacadeImplTest {
    BSFacade bsFacade;
    @BeforeEach
    void setup(){
        bsFacade = new BSFacadeImpl();

    }
    //Making sure initialised with nothing
    @Test
    void testBSFacadeImpl(){
        assertEquals(0,bsFacade.getAllProjects().size());
    }

    @Test
    void testAddProjectPositive(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        bsFacade.addProject("project1", "matt",10,12);
        assertEquals(1,bsFacade.getAllProjects().size());

    }

    //No Cheat module
    @Test
    void taskAddProjectPositive(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.addProject("project1", "matt",10,12);
        assertEquals(1,bsFacade.getAllProjects().size());
    }
    //no authentication
    @Test
    void testAddProjectPositive2(){
        assertThrows(IllegalStateException.class,() -> bsFacade.addProject("project1", "matt",10,12));
    }
    //client string null
    @Test
    void testAddProjectNegative(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        assertThrows(IllegalArgumentException.class,() -> bsFacade.addProject("project1", null,10,12));


    }
    //One string empty
    @Test
    void testAddProjectNegative2(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        assertThrows(IllegalArgumentException.class,() -> bsFacade.addProject("", "Matt",10,12));


    }

    //Both strings empty
    @Test
    void testAddProjectNegative3(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        assertThrows(IllegalArgumentException.class,() -> bsFacade.addProject("", "",10,12));


    }

    //Both strings null
    @Test
    void testAddProjectNegative4(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        assertThrows(IllegalArgumentException.class,() -> bsFacade.addProject(null, null,10,12));


    }

    //OverRate not 10% over
    @Test
    void testAddProjectNegative5(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        assertThrows(IllegalArgumentException.class,() -> bsFacade.addProject("project1", "matt",10,10));


    }

    //standardRate under range
    @Test
    void testAddProjectNegative6(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        assertThrows(IllegalArgumentException.class,() -> bsFacade.addProject("project", "matt",0,12));


    }

    //standardRate over range
    @Test
    void testAddProjectNegative7(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        assertThrows(IllegalArgumentException.class,() -> bsFacade.addProject("project", "matt",150,12));


    }

    //overRate under range
    @Test
    void testAddProjectNegative8(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        assertThrows(IllegalArgumentException.class,() -> bsFacade.addProject("project", "matt",10,-1));


    }

    //No perm module
    @Test
    void testAddTaskNoPermModule(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.injectAuth(null,null);

        assertThrows(IllegalStateException.class, () ->  bsFacade.addProject("project", "matt",10,12));
    }

    //No perm module
    @Test
    void testAddTaskNoBasicUser(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        assertThrows(IllegalStateException.class, () ->  bsFacade.addProject("project", "matt",10,12));
    }

    //Simple remove
    @Test
    void testRemoveProjectPositive(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        assertEquals(1,bsFacade.getAllProjects().size());
        bsFacade.removeProject(project.getId());
        assertEquals(0,bsFacade.getAllProjects().size());
    }

    @Test
    void testRemoveProjectPositiveNoCheat(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        Project p = bsFacade.addProject("project2", "matt",10,12);
        assertEquals(2,bsFacade.getAllProjects().size());
        assertThrows(IllegalStateException.class, () -> bsFacade.removeProject(project.getId()));
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.removeProject(project.getId());
        assertEquals(1,bsFacade.getAllProjects().size());
        bsFacade.injectAuth(null,null);
        assertThrows(IllegalStateException.class, () -> bsFacade.removeProject(p.getId()));



    }
    //Complex remove
    @Test
    void testRemoveProjectPositive2(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        bsFacade.addProject("project1", "matt",0.1,66);
        bsFacade.addProject("project2", "matt",1,12);
        bsFacade.addProject("project3", "matt",0.13,10);

        int id = -1;

        for(int i=0;i<bsFacade.getAllProjects().size();i++){
            if(bsFacade.getAllProjects().get(i).getName().equals("project2")){
                id = i;
            }
        }
        assertEquals(3,bsFacade.getAllProjects().size());
        if(id!=-1){
            bsFacade.removeProject(bsFacade.getAllProjects().get(id).getId());
            assertEquals(2,bsFacade.getAllProjects().size());
        }
        else{
            int finalId = id;
            assertThrows(IllegalStateException.class,() -> bsFacade.removeProject(finalId));
        }


    }
    //Wrong ID & authentication
    @Test
    void testRemoveProjectNegative(){
        assertThrows(IllegalStateException.class,() -> bsFacade.removeProject(0));
    }

    //wrong id
    @Test
    void testRemoveProjectNegative2(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        bsFacade.addProject("project2", "matt",1,12);
        bsFacade.login("user","password");
        assertThrows(IllegalStateException.class,() -> bsFacade.removeProject(9999999));
        assertEquals(1,bsFacade.getAllProjects().size());
    }

    @Test
    void testRemoveProjectNoPermModule(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        Project p = bsFacade.addProject("project2", "matt",10,12);
        assertEquals(2,bsFacade.getAllProjects().size());
        bsFacade.injectAuth(null,null);
        assertThrows(IllegalStateException.class,() -> bsFacade.removeProject(project.getId()));
    }

    //Regular values
    @Test
    void testAddTaskPositive(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user","password");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        assertTrue(bsFacade.addTask(project.getId(),"Skyscraper",15,false));
    }

    @Test
    void testAddTaskPositiveNoCheatBasic(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        assertTrue(bsFacade.addTask(project.getId(),"Skyscraper",15,false));
        assertThrows(IllegalStateException.class, () -> bsFacade.addTask(project.getId(),"Skyscraper",15,true));

    }
    @Test
    void testAddTaskPositiveNoCheatSecure(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        assertTrue(bsFacade.addTask(project.getId(),"Skyscraper",15,true));
        assertThrows(IllegalStateException.class, () -> bsFacade.addTask(project.getId(),"Skyscraper",15,false));
        assertThrows(IllegalStateException.class, () -> bsFacade.addTask(project.getId()-10,"Skyscraper",15,true));

    }

    @Test
    void testAddTaskNegativeNoPermModule(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.injectAuth(null,null);
        assertThrows(IllegalStateException.class,() -> bsFacade.addTask(project.getId(), "Test",15,true));
    }

    @Test
    void TestAddTaskNegativeNoAuth(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        when(authenticationModule.authenticate(token)).thenReturn(false);
        assertThrows(IllegalStateException.class,() -> bsFacade.addTask(project.getId(), "Test",15,true));
    }


    //Regular values with force
    @Test
    void testAddTaskPositive2(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user","password");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        assertTrue(bsFacade.addTask(project.getId(),"Skyscraper",15,true));
    }

    //Setting project ceiling to be breached
    @Test
    void testAddTaskPositive3(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user","password");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        bsFacade.setProjectCeiling(project.getId(),40);
        assertFalse(bsFacade.addTask(project.getId(),"Skyscraper",55,false));
        assertTrue(bsFacade.addTask(project.getId(),"Skyscraper",55,true));
    }

    @Test
    void testAddTaskParametersBreached(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user","password");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        assertThrows(IllegalArgumentException.class,() -> bsFacade.addTask(project.getId(), null,0,true));
        assertThrows(IllegalStateException.class,() -> bsFacade.addTask(-1, "Hello",2,true));

    }
    @Test
    void testAddTaskNoAuthToken(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user","password");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        bsFacade.injectAuth(null,null);
        assertThrows(IllegalStateException.class,() -> bsFacade.addTask(project.getId(), "Test",15,true));

    }

    @Test
    void testProjectCeilingArguments(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user","password");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        assertEquals(1,bsFacade.getAllProjects().size());
        assertThrows(IllegalArgumentException.class,() -> bsFacade.setProjectCeiling(project.getId(),-1));
        assertThrows(IllegalArgumentException.class,() -> bsFacade.setProjectCeiling(project.getId(),1001));
        assertThrows(IllegalStateException.class,() -> bsFacade.setProjectCeiling(project.getId()-10,100));
        assertThrows(IllegalArgumentException.class,() -> bsFacade.setProjectCeiling(project.getId()+10,-1));
        bsFacade.injectAuth(null,null);
        assertThrows(IllegalStateException.class,() -> bsFacade.setProjectCeiling(project.getId(),10));
    }

    //No Cheat Module
    @Test
    void testProjectCeilingPositive(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        assertThrows(IllegalStateException.class, () -> bsFacade.setProjectCeiling(project.getId(),40));
        bsFacade.logout();
        when(authorisationModule.authorise(token, false)).thenReturn(false);
        when(authorisationModule.authorise(token, true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");

        bsFacade.setProjectCeiling(project.getId(),40);
        bsFacade.logout();
        when(authorisationModule.authorise(token, false)).thenReturn(true);
        when(authorisationModule.authorise(token, true)).thenReturn(false);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        assertTrue(bsFacade.addTask(project.getId(),"yeayea",20,false));
        assertFalse(bsFacade.addTask(project.getId(),"yeayea",60,false));

    }
    @Test
    void testProjectCeilingNegative(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        assertThrows(IllegalStateException.class, () -> bsFacade.setProjectCeiling(project.getId(),40));
        bsFacade.logout();
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.injectAuth(null,null);
        assertThrows(IllegalStateException.class, () -> bsFacade.setProjectCeiling(project.getId(),40));


    }

    @Test
    void testFindProjectIDPositive(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        assertEquals(project.getId(),bsFacade.findProjectID("project1","matt"));

        bsFacade.addProject("project1", "matt",10,12);
        bsFacade.addProject("project2", "mat",10,12);
        bsFacade.addProject("project3", "att",10,12);
        bsFacade.addProject("project4", "mtt",10,12);
        bsFacade.addProject("project5", "matt",10,12);
        bsFacade.addProject("project6", "at",10,12);
        Project p = bsFacade.addProject("project6", "tt",10,12);
        assertEquals(8,bsFacade.getAllProjects().size());
        assertEquals(p.getId(),bsFacade.findProjectID("project6","tt"));
        bsFacade.logout();
        bsFacade.injectAuth(null,null);
        assertEquals(p.getId(),bsFacade.findProjectID("project6","tt"));

    }

    //No cheat module
    @Test
    void testFindProjectIDPositive2(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        Project project = bsFacade.addProject("project1", "matt",10,12);
        assertEquals(project.getId(),bsFacade.findProjectID("project1","matt"));
        bsFacade.addProject("project1", "matt",10,12);
        bsFacade.addProject("project2", "mat",10,12);
        bsFacade.addProject("project3", "att",10,12);
        bsFacade.addProject("project4", "mtt",10,12);
        bsFacade.addProject("project5", "matt",10,12);
        bsFacade.addProject("project6", "at",10,12);
        Project p = bsFacade.addProject("project6", "tt",10,12);
        assertEquals(8,bsFacade.getAllProjects().size());
        assertEquals(p.getId(),bsFacade.findProjectID("project6","tt"));
        bsFacade.logout();
        assertEquals(p.getId(),bsFacade.findProjectID("project6","tt"));
        assertEquals(project.getId(),bsFacade.findProjectID("project1","matt"));

    }

    @Test
    void testFindProjectIDNegative(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        bsFacade.addProject("project1", "matt",10,12);
        assertEquals(1,bsFacade.getAllProjects().size());
        assertThrows(IllegalStateException.class,() ->bsFacade.findProjectID("","matt"));
        assertThrows(IllegalArgumentException.class,() ->bsFacade.findProjectID(null,null));
    }

    //Wrong project
    @Test
    void testFindProjectIDNegative2(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.addProject("project6", "at",10,12);
        Project p = bsFacade.addProject("project6", "tt",10,12);
        assertEquals(p.getId(),bsFacade.findProjectID("project6","tt"));
        assertEquals(2,bsFacade.getAllProjects().size());
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.removeProject(p.getId());
        assertThrows(IllegalStateException.class,() ->bsFacade.findProjectID("project1","matt"));
        assertEquals(1,bsFacade.getAllProjects().size());
        bsFacade.logout();
        assertThrows(IllegalStateException.class,() ->bsFacade.findProjectID("project6","tt"));
        assertEquals(1,bsFacade.getAllProjects().size());

    }

    //Cheat module
    @Test
    void testSearchProjectsPositive(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addProject("project2", "matt",8,12);
        bsFacade.addProject("project3", "matt",4,12);
        bsFacade.addProject("project4", "matt",3,12);
        bsFacade.addProject("project5", "matt",5,12);
        bsFacade.addProject("project6", "matt",10,12);
        bsFacade.addProject("project7", "mat",5,12);
        bsFacade.addProject("project8", "matt",4,12);
        bsFacade.addProject("project9", "matt",3,12);
        assertEquals(8,bsFacade.searchProjects("matt").size());
        bsFacade.injectAuth(null,null);
        assertEquals(8,bsFacade.searchProjects("matt").size());
    }
    //No cheat module
    @Test
    void testSearchProjectsPositive2(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addProject("project2", "matt",8,12);
        bsFacade.addProject("project3", "matt",4,12);
        bsFacade.addProject("project4", "matt",3,12);
        bsFacade.addProject("project5", "matt",5,12);
        bsFacade.addProject("project6", "matt",10,12);
        bsFacade.addProject("project7", "mat",5,12);
        bsFacade.addProject("project8", "matt",4,12);
        bsFacade.addProject("project9", "matt",3,12);
        assertEquals(8,bsFacade.searchProjects("matt").size());
        bsFacade.logout();
        assertEquals(8,bsFacade.searchProjects("matt").size());


    //No auth and logged out and remove
    }
    @Test
    void testSearchProjectsPositive3(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addProject("project2", "matt",8,12);
        bsFacade.addProject("project3", "matt",4,12);
        bsFacade.addProject("project4", "matt",3,12);
        bsFacade.addProject("project5", "matt",5,12);
        bsFacade.addProject("project6", "matt",10,12);
        bsFacade.addProject("project7", "mat",5,12);
        bsFacade.addProject("project8", "matt",4,12);
        Project p = bsFacade.addProject("project9", "matt",3,12);
        assertEquals(8,bsFacade.searchProjects("matt").size());
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.removeProject(p.getId());
        assertEquals(7,bsFacade.searchProjects("matt").size());
        bsFacade.logout();
        bsFacade.injectAuth(null,null);
        assertEquals(7,bsFacade.searchProjects("matt").size());



    }
    @Test
    void testSearchProjectsEmpty(){
        assertNotNull(bsFacade.searchProjects("test"));
    }

    //Incorrect arguments
    @Test
    void testSearchProjectArgumentsNegative(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        bsFacade.addProject("project1", "matt",9,12);
        assertThrows(IllegalArgumentException.class,() -> bsFacade.searchProjects(null));
        assertNotNull(bsFacade.searchProjects(""));
    }


    @Test
    void testGetAllProjectsEmpty(){
        assertNotNull(bsFacade.getAllProjects());
    }

    @Test
    void testGetAllProjectsPositive(){
        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user", "password");
        bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addProject("project2", "matt",9,12);
        Project p = bsFacade.addProject("project3", "matt",9,12);

        assertEquals(3,bsFacade.getAllProjects().size());

        bsFacade.removeProject(p.getId());

        assertEquals(2,bsFacade.getAllProjects().size());

    }

    @Test
    void testGetAllProjectsPositive2(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addProject("project2", "matt",9,12);
        assertEquals(2,bsFacade.getAllProjects().size());

    }

    //Not logged in
    @Test
    void testGetAllProjectsPositive3(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addProject("project2", "matt",9,12);
        bsFacade.logout();
        assertEquals(2,bsFacade.getAllProjects().size());

    }



    @Test
    void testAuditPositive(){
        ComplianceReporting complianceReporting = mock(ComplianceReporting.class);
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        ERPCheatFactory cheatModule = new ERPCheatFactory();
        bsFacade.injectAuth(cheatModule.getAuthenticationModule(),cheatModule.getAuthorisationModule());
        bsFacade.login("user","password");
        Project p = bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addTask(p.getId(),"test",50,false);
        bsFacade.logout();
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectCompliance(complianceReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.setProjectCeiling(p.getId(), 20);
        bsFacade.audit();
        verify(complianceReporting).sendReport(eq("project1"),anyInt(),eq(token));


    }

    @Test
    void testAuditPositive2(){
        ComplianceReporting complianceReporting = mock(ComplianceReporting.class);
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectCompliance(complianceReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");

        Project p = bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addTask(p.getId(),"test",50,false);
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.setProjectCeiling(p.getId(), 20);

        bsFacade.audit();
        verify(complianceReporting).sendReport(eq("project1"),anyInt(),eq(token));

    }
    //No perm module
    @Test
    void testAuditNegative(){
        ComplianceReporting complianceReporting = mock(ComplianceReporting.class);
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectCompliance(complianceReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");

        Project p = bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addTask(p.getId(),"test",50,false);
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.setProjectCeiling(p.getId(), 20);
        bsFacade.injectAuth(null,null);

        assertThrows(IllegalStateException.class, () -> bsFacade.audit());

    }

    //not secure user
    @Test
    void testAuditNegative2(){
        ComplianceReporting complianceReporting = mock(ComplianceReporting.class);
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectCompliance(complianceReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");

        Project p = bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addTask(p.getId(),"test",50,false);
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.setProjectCeiling(p.getId(), 20);
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authorisationModule.authorise(token,true)).thenReturn(false);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        assertThrows(IllegalStateException.class, () -> bsFacade.audit());

    }

    //user logged out
    @Test
    void testAuditNegative3(){
        ComplianceReporting complianceReporting = mock(ComplianceReporting.class);
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectCompliance(complianceReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");

        Project p = bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addTask(p.getId(),"test",50,false);
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.setProjectCeiling(p.getId(), 20);
        bsFacade.logout();

        assertThrows(IllegalStateException.class, () -> bsFacade.audit());

    }

    //compliance module
    @Test
    void testAuditNegative5(){
        ComplianceReporting complianceReporting = mock(ComplianceReporting.class);
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");

        Project p = bsFacade.addProject("project1", "matt",9,12);
        bsFacade.addTask(p.getId(),"test",50,false);
        bsFacade.logout();
        when(authorisationModule.authorise(token,false)).thenReturn(false);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.setProjectCeiling(p.getId(), 20);

        assertThrows(IllegalStateException.class, () -> bsFacade.audit());

    }

    @Test
    void finaliseProjectPositive(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        ClientReporting clientReporting = mock(ClientReporting.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectClient(clientReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        Project p = bsFacade.addProject("project1", "matt",9,12);
        bsFacade.logout();
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.finaliseProject(p.getId());

        verify(clientReporting).sendReport(eq("matt"),anyString(),eq(token));

    }

    //user is logged out
    @Test
    void finaliseProjectNegative(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        ClientReporting clientReporting = mock(ClientReporting.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectClient(clientReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user", "pass");
        Project p = bsFacade.addProject("project1", "matt",9,12);
        bsFacade.logout();
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        assertThrows(IllegalStateException.class, () ->  bsFacade.finaliseProject(p.getId()));

    }
    //incorrect authorisation
    @Test
    void finaliseProjectNegative2(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        ClientReporting clientReporting = mock(ClientReporting.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectClient(clientReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);

        bsFacade.login("user", "pass");
        Project p = bsFacade.addProject("project1", "matt",9,12);
        assertThrows(IllegalStateException.class, () ->  bsFacade.finaliseProject(p.getId()));

    }
    //No authorisation
    @Test
    void finaliseProjectNegative3(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        ClientReporting clientReporting = mock(ClientReporting.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectClient(clientReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);

        bsFacade.login("user", "pass");
        Project p = bsFacade.addProject("project1", "matt",9,12);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(false);
        assertThrows(IllegalStateException.class, () ->  bsFacade.finaliseProject(p.getId()));

    }
    //no authorisation module
    @Test
    void finaliseProjectNegative5(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        ClientReporting clientReporting = mock(ClientReporting.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectClient(clientReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);

        bsFacade.login("user", "pass");
        Project p = bsFacade.addProject("project1", "matt",9,12);
        bsFacade.logout();
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.injectAuth(null,null);
        assertThrows(IllegalStateException.class, () ->  bsFacade.finaliseProject(p.getId()));

    }
    //Wrong projectid
    @Test
    void finaliseProjectNegative6(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        ClientReporting clientReporting = mock(ClientReporting.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        bsFacade.injectClient(clientReporting);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);

        bsFacade.login("user", "pass");
        Project p = bsFacade.addProject("project1", "matt",9,12);
        assertThrows(IllegalStateException.class, () ->  bsFacade.finaliseProject(p.getId()-10));

    }

    //No client reporting
    @Test
    void finaliseProjectNegative4(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        ClientReporting clientReporting = mock(ClientReporting.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);

        bsFacade.login("user", "pass");
        Project p = bsFacade.addProject("project1", "matt",9,12);
        bsFacade.logout();
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        bsFacade.login("user","pass");
        assertThrows(IllegalStateException.class, () ->  bsFacade.finaliseProject(p.getId()));

    }



    @Test
    void testInjectAuthPositive(){
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);
        assertThrows(IllegalArgumentException.class,() -> bsFacade.injectAuth(null,authorisationModule));
    }

    @Test
    void testLoginPositive(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);

        assertTrue(bsFacade.login("user","pass"));


    }
    @Test
    void testLoginPositive2(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);

        assertFalse(bsFacade.login("user1","pass"));


    }
    @Test
    void testLoginNegative(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        assertThrows(IllegalStateException.class,() -> bsFacade.login("user","password"));


    }

    @Test
    void testLoginNegative2(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);

        assertThrows(IllegalArgumentException.class,() -> bsFacade.login(null,"pass"));


    }
    @Test
    void testLoginNegative3(){
        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);

        assertThrows(IllegalArgumentException.class,() -> bsFacade.login(null,null));


    }

    @Test
    void testLogoutPositive(){

        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        bsFacade.login("user","pass");
        bsFacade.logout();

        verify(authenticationModule).logout(token);

    }

    @Test
    void testLogoutNegative(){

        AuthToken token = mock(AuthToken.class);
        AuthenticationModule authenticationModule = mock(AuthenticationModule.class);
        AuthorisationModule authorisationModule = mock(AuthorisationModule.class);

        bsFacade.injectAuth(authenticationModule,authorisationModule);
        when(authenticationModule.login("user","pass")).thenReturn(token);
        when(authorisationModule.authorise(token,true)).thenReturn(true);
        when(authorisationModule.authorise(token,false)).thenReturn(true);
        when(authenticationModule.authenticate(token)).thenReturn(true);
        assertThrows(IllegalStateException.class,() -> bsFacade.logout());



    }

    @Test
    void testLogoutNegative2(){

        assertThrows(IllegalStateException.class,() -> bsFacade.logout());


    }


}
