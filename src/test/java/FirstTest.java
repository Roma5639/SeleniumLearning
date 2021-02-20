import com.base.BaseTest;
import com.page.LoginPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void testSearchField() {
        LoginPage testSearchField = new LoginPage(driver);
        testSearchField.navigateToSite("https://www.google.com/");
        String title = driver.getTitle();
        Assert.assertEquals(true, title.equals("Google"));
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("usa");
    }

    @Test
    public void testMailLogin() {
        LoginPage testMailLogin = new LoginPage(driver);
        testMailLogin.navigateToSite("https://www.google.com/");
        driver.findElement(By.xpath("//div[@class='gb_h gb_i']/a[@class='gb_g']")).click();

        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(By.xpath("//li[@class='h-c-header__nav-li g-mail-nav-links'][2]")).click();
// Perform the click operation that opens new window
        driver.switchTo().window(winHandleBefore);
        driver.close();
// Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Перейдіть у Gmail']")));
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("testfort56@gmail.com");
        driver.findElement(By.xpath("//div[@class='VfPpkd-RLmnJb']")).click();

    }
}