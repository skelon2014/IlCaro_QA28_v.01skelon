import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.xml.dom.ParentSetter;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (!app.userHelper().isLogined()) {
            app.userHelper().logout();
        }
    }

  /* @Test
    public void loginTestPositive() {
        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm("skelon+2@bk.ru", "Qwerty$4");
        app.userHelper().submitLogin();
        app.userHelper().pause(3000);
        String message = app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));
        app.userHelper().clickOK();
        Assert.assertEquals(message, "Logged in success");


        //String message = wd.findElement(By.xpath("//div[@class='dialog-container']//h2")).getText();
        // click(By.xpath("//*[@type='button']"));

    }*/

    @Test
    public void loginTestPositiveDto() {
        User user = new User().withEmail("skelon+2@bk.ru").withPassword("Qwerty$4");
        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm(user);
        app.userHelper().submitLogin();
        app.userHelper().pause(3000);
        String message = app.userHelper().getText(By.xpath("//div[@class='dialog-container']//h2"));
        Assert.assertEquals(message, "Logged in success");
        app.userHelper().clickOK();

    }

  /*  public void loginTestNegative() {
        click(By.xpath("//a[.=' Log in ']"));//look in TestBase method 'click'
        type(By.id("email"), "skelon+2@bk.ru");
        type(By.id("password"), "Qwerty$41");
        // click(By.xpath("//button[contains(text(),'Y’alla!')]"));
        click(By.xpath("//*[@type='submit']"));
        pause(3000);
        //String message = wd.findElement(By.xpath("//div[@class='dialog-container']//h2")).getText();
        String message = getText(By.xpath("//div[@class='dialog-container']//h2"));
        click(By.xpath("//button[.='Ok']"));
        Assert.assertEquals(message, "Wrong email or password");
        // click(By.xpath("//*[@type='button']"));
    }*/

}