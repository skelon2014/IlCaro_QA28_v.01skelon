package application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[.=' Log in ']"));

    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"),email);
        type(By.id("password"),password);
    }

    public void submitLogin() {
        click(By.xpath("//*[@type='submit']"));
    }

    public void clickOK() {
        click(By.xpath("//button[.='Ok']"));
    }
}
