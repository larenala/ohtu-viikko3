package ohtu;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.services.*;

public class Stepdefs {
    App app;
    StubIO io;
    UserDao userDao = new InMemoryUserDao();
    AuthenticationService auth = new AuthenticationService(userDao);
    List<String> inputLines = new ArrayList<>();
    
    @Given("^command login is selected$")
    public void command_login_selected() throws Throwable {
        inputLines.add("login");
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are entered$")
    public void a_username_and_password_are_entered(String username, String password) throws Throwable {
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
    
     @When("^username \"([^\"]*)\" is entered$")
    public void username_is_entered(String arg1) throws Throwable {
        inputLines.add(arg1);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
    
     @Given("^command new user is selected$")
    public void command_new_user_is_selected() throws Throwable {
         inputLines.add("login");
    }

    @When("^Username \"([^\"]*)\" and password \"([^\"]*)\" are entered$")
    public void username_and_password_are_entered(String arg1, String arg2) throws Throwable {
        inputLines.add(arg1);
        inputLines.add(arg2);
       
        io = new StubIO(inputLines); 
        app = new App(io, auth);
        app.run();
    }

    
    @When("^already used username \"([^\"]*)\" is entered$")
    public void already_used_username_is_entered(String arg1) throws Throwable {
        inputLines.add(arg1);
      
        io = new StubIO(inputLines); 
        app = new App(io, auth);
        app.run();
    }
    


    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String expectedOutput) throws Throwable {
        assertTrue(io.getPrints().contains(expectedOutput));
    }
    
   /* @Given("^command new is selected$")
    public void command_new_selected() throws Throwable {
        inputLines.add("login");
    }
    
    @When("^Username \"([^\"]*)\" and password \"([^\"]*)\" are entered$")
    public void new_username_and_password_are_entered(String username, String password) throws Throwable {
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
    
     @When("^alreadyUsedUsername \"([^\"]*)\" is entered$")
    public void alreadyUsedUsername_is_entered(String arg1) throws Throwable {
        inputLines.add(arg1);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
    
    @Then("^System will respond with \"([^\"]*)\"$")
    public void System_will_respond_with(String expectedOutput) throws Throwable {
        assertTrue(io.getPrints().contains(expectedOutput));
    }*/
}
