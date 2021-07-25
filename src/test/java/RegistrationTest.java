import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (!app.userHelper().isLogined()) {
            app.userHelper().logout();
        }
    }

    @Test
    public void registrationTestPositive() {
        User user = new User().withName("Serg").withLastName("Aser").withEmail("skelon+12@bk.ru").withPassword("Qwerty$4");

            app.userHelper().openRegistrationForm();
            app.userHelper().fillRegistrationForm(user);
            String message = app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));
            Assert.assertEquals(message, "User with this email already exists");
            app.userHelper().clickOK();
       // System.out.println(message);

    }
}
