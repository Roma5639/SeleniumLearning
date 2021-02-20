package com.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

public class BasePage {

    public WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //Navigation through pages
    public void navigateToSite(String site){
        driver.get(site);
    }

    public void backToPreviousPage(){
        driver.navigate().back();
    }

    public void navigateToPreviousTab(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public void reloadPage(){
        driver.navigate().refresh();
    }

    public void switchFrame(int frameName){
        driver.switchTo().frame(frameName);
    }

    //Waiters
    public void waitForElementIsVisible(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementIsClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //Manipulations with cursor/mouse
    /**
     * Click the button
     * @param element
     */
    public void mouseClick(WebElement element){
        element.click();
    }

    public void mouseRightClick (WebElement element){
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

    public void mouseOver(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void dragAndDrop (WebElement from, WebElement to){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(from, to).build().perform();
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void moveSlider(WebElement element, int position){
        Actions actions = new Actions(driver);
        int width = element.getSize().getWidth();
        actions.clickAndHold(element).moveByOffset(-width/2, 0).moveByOffset(position, 0).click().perform();
    }

    /**
     * Set user`s input to the implicitly declared variable
     * @param element
     * @param userInput
     */
    public void setUserInput(WebElement element, String userInput){
        element.sendKeys(userInput);
    }

    public String getElementText(WebElement element){
        return element.getText();
    }

    public boolean elementContainsFollowingText(WebElement element, String expectedText){
        return element.getText().contains(expectedText);
    }

    public void selectFromDropDown(WebElement element, int dropDownIndex){
        Select dropDown = new Select(element);
        dropDown.selectByIndex(dropDownIndex);
    }

    //Manipulations with alerts
    public void submitAlert(){
        driver.switchTo().alert().accept();
    }

    public void sendDataToAlert(String userInput){
        driver.switchTo().alert().sendKeys(userInput);
    }

    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }

    public void keyPress(WebElement element, Keys keys){
        element.sendKeys(keys);
    }

}
