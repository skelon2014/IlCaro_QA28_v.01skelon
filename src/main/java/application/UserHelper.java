package application;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
      //  click(By.xpath("//a[@class='navigation-link ng-star-inserted'][normalize-space()='Log in']"));
        click(By.xpath("//a[.=' Log in ']"));

    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"),email);
        type(By.id("password"),password);
    }

    public void fillLoginForm(User user) {
        type(By.id("email"),user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void submitLogin() {
        click(By.xpath("//*[@type='submit']"));
    }

    public void clickOK() {
        click(By.xpath("//button[.='Ok']"));
    }

    public boolean isLogined() {
        return wd.findElements(By.xpath("//a[.=' Log in ']")).size()>0;
    }
    public int isExist(){

        return wd.findElements(By.xpath("By.xpath(\"//div[@class='dialog-container']//h2\")")).size();
    }
    //=======================================================
    public void openRegistrationForm(){
        click(By.xpath("//a[.=' Sign up ']"));
    }
    public void fillRegistrationForm(User user){
        type(By.id("name"),user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
        click(By.cssSelector("label[for='terms-of-use']"));
        click(By.cssSelector("button[type='submit']"));
    }



    public void logout() {
        click((By.xpath("//a[.=' Logout ']")));
    }
}
