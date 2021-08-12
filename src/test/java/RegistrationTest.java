import application.MyDataProvider;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

public class RegistrationTest extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        if (!app.userHelper().isLogined()) {
            app.userHelper().logout();
        }
    }

    @Test(groups = {"web"})
    public void registrationTestPositive() throws InterruptedException {
        int i = (int) ((System.currentTimeMillis() / 10000) % 3600);
        User user = new User()
                .withName("Serg")
                .withLastName("Aser")
                .withEmail("skelon" + i + "@bk.ru")
                .withPassword("Qwerty$4");
        logger.info("Registration with -> Name: " + user.getName() + ", Lastname: " + user.getLastName() +
                ", Email: " + user.getEmail() + ", Password: " + user.getPassword());
        logger.info("Test passed");

        app.userHelper().openRegistrationForm();
        app.userHelper().fillRegistrationForm(user);
        app.userHelper().checkPolicy();
        app.userHelper().submitForm();
        String message = app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));
        Thread.sleep(2000);
        Assert.assertFalse(message.equals("User with this email already exists"));
      //  app.userHelper().clickOK();
        // System.out.println(message);

    }
    //========================================================================================
    @Test(groups = {"web"}, dataProvider = "registrationCSV", dataProviderClass = MyDataProvider.class)
    public void registrationTestCSV(User user) throws InterruptedException {

        app.userHelper().openRegistrationForm();
        app.userHelper().fillRegistrationForm(user);
        app.userHelper().checkPolicy();
        app.userHelper().submitForm();
        String message = app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));

       // Assert.assertFalse(message.equals("User with this email already exists"));
        logger.info("Test passed");

        // System.out.println(message);

    }
//======================================================================================
    @Test(dataProvider = "registration", dataProviderClass = MyDataProvider.class)
    public void registrationTestWithoutCSV(String name, String lastName, String email, String password) throws InterruptedException {
        User user = new User()
                .withName(name)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password);
        logger.info("Registration with --> Name: "+user.getName()+", Lastname: "+user.getLastName());

        app.userHelper().openRegistrationForm();
        app.userHelper().fillRegistrationForm(user);
        app.userHelper().checkPolicy();
        app.userHelper().submitForm();
        String message = app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));

        Assert.assertFalse(message.equals("User with this email already exists"));
        logger.info("Test passed");

        // System.out.println(message);

    }
    @AfterMethod
    public void postCondition(){
        app.userHelper().pause(1000);
        app.userHelper().clickOK();
        app.userHelper().pause(1000);
        app.userHelper().click(By.xpath("//a[.='Delete account']"));
        app.userHelper().click(By.xpath("//button[.='Delete']"));
    }

}