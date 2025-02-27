package base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import org.testng.Assert;
import utils.PlaywrightDriver;

public class BasePage {
  public static Page page;

  public BasePage() {
    page = PlaywrightDriver.getPage();
  }

  public void click(String locatorKey) {

    try {
      page.locator(PlaywrightDriver.OR.getProperty(locatorKey)).click();
    } catch (Throwable t) {

      Assert.fail(t.getMessage());
    }
  }

  public void mouseHover(String locatorKey) {

    try {
      page.hover(PlaywrightDriver.OR.getProperty(locatorKey));
    } catch (Throwable t) {

      Assert.fail(t.getMessage());
    }
  }

  public boolean isElementPresent(String locatorKey) {

    try {
      page.waitForSelector(
          PlaywrightDriver.OR.getProperty(locatorKey),
          new Page.WaitForSelectorOptions().setTimeout(2000));

      return true;
    } catch (Throwable t) {

      return false;
    }
  }

  public void type(String locatorKey, String value) {
    try {
      page.locator(PlaywrightDriver.OR.getProperty(locatorKey)).fill(value);
    } catch (Throwable t) {

      Assert.fail(t.getMessage());
    }
  }

  public void select(String locatorKey, String value) {
    try {
      page.selectOption(
          PlaywrightDriver.OR.getProperty(locatorKey), new SelectOption().setLabel(value));
    } catch (Throwable t) {

      Assert.fail(t.getMessage());
    }
  }
  public void popup(String plocator,String popupLocator){
    Page popup=page.waitForPopup(()->{
      page.locator(plocator).click();
    });
    popup.locator(popupLocator).click();
  }

  public void alerts(String locator){
    page.onceDialog(
            dialog -> {
              try {
                Thread.sleep(5000);
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
              dialog.accept();
              System.out.println(dialog.message());

            });
    page.locator(locator).click();
  }

  public void keyboardEvent(String keyName){
    page.keyboard().press(keyName);
  }

  public void sliders(String locator){
    Locator slider= page.locator(locator);
    page.mouse().move(slider.boundingBox().x+slider.boundingBox().width/2,slider.boundingBox().y+slider.boundingBox().height/2);
    page.mouse().down();
    page.mouse().move(slider.boundingBox().x+ 300,slider.boundingBox().y+slider.boundingBox().height/2);
    page.mouse().up();
  }
  public void droppable(String dragLocator,String dropLocator){
    Locator draggable= page.locator(dragLocator);
    Locator droppable= page.locator(dropLocator);
    page.mouse().move(draggable.boundingBox().x+draggable.boundingBox().width/2,draggable.boundingBox().y+draggable.boundingBox().height/2);
    page.mouse().down();
    page.mouse().move(droppable.boundingBox().x+ droppable.boundingBox().width/2,droppable.boundingBox().y+droppable.boundingBox().height/2);
    page.mouse().up();
  }
}