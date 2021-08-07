import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
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
        String message = app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));
        Thread.sleep(2000);
        Assert.assertFalse(message.equals("User with this email already exists"));
        app.userHelper().clickOK();
        // System.out.println(message);

    }
}