import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scraper
{
	public static void main(String args[])
	{
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		System.out.println("Hello, World!");
        WebDriver driver = new ChromeDriver();
		//driver.navigate().to("https://it.wikipedia.org/wiki/Imperatori_romani");
		driver.get("https://it.wikipedia.org/wiki/Imperatori_romani");

		System.out.println("\n\n[+] Start 1\n\n");
		WebElement wd = driver.findElement(By.className("toc"));
		System.out.println(wd.getText());
		WebElement emp = wd.findElement(By.linkText("Elenco cronologico degli imperatori romani"));
		//WebElement emp = wd.findElement(By.xpath("//a[@href ='#Elenco_cronologico_degli_imperatori_romani']"));
		emp.click();
		System.out.println(emp.getText());

		/*
		System.out.println("\n\n[+] Start 2\n\n");
		List<WebElement> words = driver.findElements(By.className("toclevel-1 tocsection-1"));
		words.forEach(word -> System.out.println(word.getText()));
		*/

        driver.quit(); 
		System.out.println("\n\n[+] Finish");
	}
}