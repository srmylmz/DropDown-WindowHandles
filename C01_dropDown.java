package Team08_DersAnlatimi.day08_DropDown;

import Team08_DersAnlatimi.Utilities.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class C01_dropDown extends TestBase {
    @Test
    public void test01() {
        //https://trytestingthis.netlify.app/ adresine gidelim
        driver.get("https://trytestingthis.netlify.app/");

        //Firstname ve lastname kısımlarına isim ve soyismimizi yazalım
        //Gender kısmını kendimizi uygun olacak sekilde işaretleyelim

       WebElement name= driver.findElement(By.xpath("(//input)[4]"));
       name.click();
       name.sendKeys("name", Keys.TAB,"username",Keys.TAB,Keys.ARROW_DOWN);

        // "Lets you select only one option" kısmında  "Option 1" i secelim ve secimimizi doğrulayalım
        WebElement ddm1=driver.findElement(By.xpath("(//select)[1]"));
        Select select=new Select(ddm1);
        select.selectByIndex(1);

        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Option 1");

        // "Lets you select multiple options" kısmında  "Option 2" ve"Option 3" ü  secelim
        WebElement ddm2=driver.findElement(By.xpath("(//select)[2]"));
        Select select1=new Select(ddm2);

        select1.selectByValue("option 2");
        select1.selectByVisibleText("Option 3");


        // Sectiğimiz secenekleri konsola yazdıralım ve Seçeneklerden 4 tane seçmediğimizi doğrulayalım
        select1.getAllSelectedOptions().forEach(t-> System.out.println(t.getText()));
        //Option 2
        //Option 3

        Assert.assertNotEquals(4,select1.getAllSelectedOptions().size());

        //Seçtiğimiz seçeneklerin hepsini kaldıralım
        select1.deselectAll();

        //"Start typing and it till automatically guess answer:" bölümünden "Strawberry" işaretleyelim
        WebElement ddm3=driver.findElement(By.xpath("(//input)[12]"));
        ddm3.sendKeys("Strawberry");

    }
}
