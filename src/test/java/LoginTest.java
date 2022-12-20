import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

    @Test
    public void loginSuccess(){
        User data = new User()
                .withEmail("haifa@gmail.com")
                .withPassword("Haifa082022$");

        app.getUser().openLoginForm();
//        app.getUser().fillLoginForm("asd@fgh.com", "$Asdf1234");
        app.getUser().fillLoginForm(data);
        app.getUser().submitLogin();
//        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @AfterMethod
    public void postCondition(){
        app.getUser().pause(3000);
        app.getUser().clickOkButton();
    }
}