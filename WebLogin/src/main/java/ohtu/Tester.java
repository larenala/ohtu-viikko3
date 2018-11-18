package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        sleep(1);
        
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akap");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        
        element.submit();
        
        sleep(2);
        
         
        element = driver.findElement(By.name("username"));
        element.sendKeys("fdjkfsdofsdfjdofsdfs5d");
        element = driver.findElement(By.name("password"));
        element.sendKeys("psswrd");
        element = driver.findElement(By.name("login"));
              
        sleep(1);
        
        element.submit();

        sleep(2);
        
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        
        
        sleep(2);
        
        Random r = new Random();  
        element = driver.findElement(By.name("username"));
        element.sendKeys("arto"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("ajiam");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ajiam");
        
        sleep(2);
        
        element.submit();
        
        sleep(3);
        
        driver.get("http://localhost:4567/ohtu");
        
        element = driver.findElement(By.linkText("logout"));
        sleep(2);
        element.click();
        
        sleep(2);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
