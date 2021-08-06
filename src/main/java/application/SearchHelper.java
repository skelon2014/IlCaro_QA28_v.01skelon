package application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchHelper extends HelperBase {

    public SearchHelper(WebDriver wd) {
        super(wd);
    }

    public void typeSearchCurrentMonth(String city, String dateFrom, String dateTo) {
        fillInputCity(city);
        typeInputPeriod(dateFrom, dateTo);
    }

    private void fillInputCity(String city) {
        type(By.id("city"), city);
        pause(1000);
        click(By.xpath("//div[@class='pac-item']"));//
    }

    private void typeInputPeriod(String dateFrom, String dateTo) {
        //   click(By.cssSelector("div.cdk-overlay-container"));
        type(By.id("dates"), dateFrom + "-" + dateTo);

        int i = (int) (System.currentTimeMillis() / 1000 % 60);
        String screenshot = "src/test/screenshots/screen-" + i + ".png";
        takeScreenshot(screenshot);

        click(By.cssSelector("div.cdk-overlay-container"));//close field, that closed YALLA
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("div.search-results"));
    }

    public boolean isDateInPath() {
        WebElement warning = wd.findElement(By.xpath("//div[@class='ng-star-inserted']"));
        String warningTxt = warning.getText();
        new WebDriverWait(wd, 30)
                .until(ExpectedConditions.textToBePresentInElement(warning, warningTxt));
        return warningTxt.contains("before today");
    }

    public boolean isButtonYallaInactive() {
        WebElement yallaButton = wd.findElement(By.xpath("//button[@type='submit']"));
        return !yallaButton.isEnabled();
    }

    public void fillSearchForCurrentMonth(String city, String dateFrom, String dateTo) {
        fillInputCity(city);
        selectPeriodCurrentMonth(dateFrom, dateTo);
    }

    private void selectPeriodCurrentMonth(String dateFrom, String dateTo) {
        click(By.id("dates"));
        //dateFrom 07/29/2021    dateTo 07/31/2021 we need onle 29 and 31
        String[] dateF = dateFrom.split("/");
        String[] dateT = dateTo.split("/");
           //dateF[1] == 29   //div[text()=' 29 ']
           //dateT[1] == 31   //div[text()=' 31 ']
           //  click(By.xpath("//div[text()=' 29 ']"));
           //   click(By.xpath("//div[text()=' 31 ']"));

        String dateLocatorFrom;
        String dateLocatorTo;
        dateLocatorFrom = String.format("//div[text()=' %s ']",dateF[1]);
        dateLocatorTo = String.format("//div[text()=' %s ']",dateT[1]);
        click(By.xpath(dateLocatorFrom));
        click(By.xpath(dateLocatorTo));


    }

    public void backToHome() {
        click(By.xpath("//a[@href='/']"));
        new WebDriverWait(wd,10)
                .until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".search-container"))));

    }
}
