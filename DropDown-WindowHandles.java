package techproed.day14_Actions_Faker;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.Select;
import techproed.utilities.TestBase;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class SlaytÖrnekGenelTekrarBİTMEDİ extends TestBase {

    /**
     * Test01 :
     * 1- amazon gidin https://www.amazon.com/
     * 2. Arama kutusunun solundaki dropdown menuyu handle edip listesini ekrana yazdırın
     * 3. dropdown menude 40 eleman olduğunu doğrulayın
     * Test02
     * 1. dropdown menuden elektronik bölümü seçin
     * 2. arama kutusuna iphone yazip aratin ve bulunan sonuç sayısını yazdırın
     * 3. sonuc sayisi bildiren yazinin iphone icerdigini test edin
     * 4. ikinci ürüne relative locater kullanarak tıklayin
     * 5. ürünün title'ni ve fiyatını variable’a assign edip ürünü sepete ekleyelim
     * Test03
     * 1. yeni bir sekme açarak amazon anasayfaya gidin
     * 2. dropdown’dan bebek bölümüne secin
     * 3. bebek puset aratıp bulundan sonuç sayısını yazdırın
     * 4. sonuç yazsının puset içerdiğini test edin
     * 5-üçüncü ürüne relative locater kullanarak tıklayin
     * 6-title ve fiyat bilgilerini assign edelim ve ürünü sepete ekleyin
     * Test 4
     * 1-sepetteki ürünlerle eklediğimiz ürünlerin aynı olduğunu isim ve fiyat olarak doğrulayın
     */
    @Test
    public void test01() {


        //1- amazon gidin https://www.amazon.com/
        driver.get("https://www.amazon.com/");

        //2. Arama kutusunun solundaki dropdown menuyu handle edip listesini ekrana yazdırın
        WebElement dropDown = driver.findElement(By.xpath("//select"));
        Select select = new Select(dropDown);
        select.getOptions().forEach(t -> System.out.println(t.getText()));

        //3. dropdown menude 40 eleman olduğunu doğrulayın
        System.out.println("Dropdown Sayısı : " + select.getOptions().size());//28
        Assert.assertNotEquals(select.getOptions().size(), 40);

        //1. dropdown menuden elektronik bölümü seçin
        select.selectByIndex(10);

        //2. arama kutusuna iphone yazip aratin ve bulunan sonuç sayısını yazdırın
        driver.findElement(By.xpath("(//input)[4]")).sendKeys("iphone", Keys.ENTER);

        //3. sonuc sayisi bildiren yazinin iphone icerdigini test edin
        WebElement aramaSonucu = driver.findElement(By.cssSelector("[class='a-section a-spacing-small a-spacing-top-small']"));
        String sonucSayısı = aramaSonucu.getText().split(" ")[3];
        System.out.println(sonucSayısı);//6000

        //4. ikinci ürüne relative locater kullanarak tıklayin
        WebElement iphone= driver.findElement(By.xpath("(//h2//a)[2]"));
        String iphoneIsmi=iphone.getText();
        System.out.println("iphone ismi:"+iphoneIsmi);
        iphone.click();
        //5. ürünün title'ni ve fiyatını variable’a assign edip ürünü sepete ekleyelim
        String title = driver.getTitle();

        String fiyatAna = driver.findElement(By.cssSelector("[class='a-price-whole']")).getText();
        String fiyatKusurat = driver.findElement(By.xpath("(//*[@class='a-price-fraction'])[6]")).getText();

        String fiyatIphone = fiyatAna + "." + fiyatKusurat;
        System.out.println("iphoneFiyatı:"+fiyatIphone);

        WebElement sepetEkle = driver.findElement(By.id("submit.add-to-cart"));
        sepetEkle.click();

        //1. yeni bir sekme açarak amazon anasayfaya gidin
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.amazon.com/");

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());//İKİNCİ SAYFAYA GECTİK
        //2. dropdown’dan bebek bölümüne secin
        WebElement dropDown2 = driver.findElement(By.xpath("//select"));
        Select select1 = new Select(dropDown2);
        select1.selectByIndex(3);

        //3. bebek puset aratıp bulundan sonuç sayısını yazdırın
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("bebek puset", Keys.ENTER);

        //4. sonuç yazsının puset içerdiğini test edin
        WebElement puset = driver.findElement(By.xpath("(//*[@class='sg-col-inner'])[1]"));
        Assert.assertTrue(puset.getText().contains("puset"));

        //5-üçüncü ürüne relative locater kullanarak tıklayin
       WebElement bebekPuset= driver.findElement(By.xpath("(//h2//a)[3]"));
        String pusetIsmi=bebekPuset.getText();
        System.out.println("PusetIsmi"+pusetIsmi);
        bebekPuset.click();



        //6-title ve fiyat bilgilerini assign edelim ve ürünü sepete ekleyin
        String title2 = driver.getTitle();

        String fiyatAna2 = driver.findElement(By.xpath("(//*[@class='a-price-whole'])[6]")).getText();
        String fiyatKusurat2 = driver.findElement(By.xpath("(//*[@class='a-price-fraction'])[6]")).getText();

        String fiyatPuset = fiyatAna2 + "." + fiyatKusurat2;
        System.out.println("pusetFiyatı:"+fiyatPuset);

        driver.findElement(By.xpath("//*[@id='add-to-cart-button']")).click();

        //1-sepetteki ürünlerle eklediğimiz ürünlerin aynı olduğunu isim ve fiyat olarak doğrulayın
        driver.findElement(By.id("nav-cart-count")).click();

        WebElement pusetSepetFiyat=driver.findElement(By.xpath("(//p)[1]"));
        String pusetSepetFiyatt=pusetSepetFiyat.getText().replace("$","");
        System.out.println("pusetSepetFiyatı:"+pusetSepetFiyatt);
            Assert.assertEquals(pusetSepetFiyatt,fiyatPuset);

        WebElement iphoneSepetFiyat=driver.findElement(By.xpath("(//*[@class='a-spacing-mini'])[4]"));
        String iphoneSepetFiyatt=iphoneSepetFiyat.getText().replace("$","");
        System.out.println("iphoneSepetFiyatı:"+iphoneSepetFiyatt);
            Assert.assertEquals(iphoneSepetFiyatt,fiyatIphone);

        String pusetSepetIsmii=driver.findElement(By.xpath("(//*[@class='a-truncate-cut'])[1]")).getText();
        System.out.println(pusetSepetIsmii);
            Assert.assertEquals(pusetIsmi.substring(0,5),pusetSepetIsmii.substring(0,5));

        String iphoneSepetİsmii=driver.findElement(By.xpath("(//*[@class='a-truncate-cut'])[2]")).getText();
        System.out.println("iphoneSepetIsmi:"+iphoneSepetİsmii);
            Assert.assertEquals(iphoneIsmi.substring(0,5),iphoneSepetİsmii.substring(0,5));






    }
}















