import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scraper
{
	private WebDriver driver;

	public Scraper()
	{
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("\n[+] Init\n");
	}

	public void exit()
	{
		driver.quit(); 
		System.out.println("\n[+] Quit");
	}

	public List<WebElement> classScrape(String url, String className)
	{
		//driver.navigate().to("https://it.wikipedia.org/wiki/Imperatori_romani");
		//data = driver.findElement(By.className("toc"));
		//data = driver.findElement(By.className("mw-headline"));
		//List<WebElement> datas = driver.findElements(By.className("mw-headline"));
		//List<WebElement> datas = driver.findElements(By.linkText("Dinastia"));
		//datas.forEach(word -> word.findElement());
		//List<WebElement> datas = driver.findElements(By.tagName("a"));
		//datas.forEach(word -> System.out.println(word.getText()));
		//System.out.println(wd.getText());
		/*
		WebElement emp = wd.findElement(By.linkText("Elenco cronologico degli imperatori romani"));
		//WebElement emp = wd.findElement(By.xpath("//a[@href ='#Elenco_cronologico_degli_imperatori_romani']"));
		emp.click();
		System.out.println(emp.getText());
		*/
		/*
		System.out.println("\n\n[+] Start 2\n\n");
		List<WebElement> words = driver.findElements(By.className("toclevel-1 tocsection-1"));
		words.forEach(word -> System.out.println(word.getText()));
		*/
		driver.get(url);
		//System.out.println(className);
		//List<WebElement> a = driver.findElements(By.className(className));
		//System.out.println(a.size());
		return (driver.findElements(By.className(className)));
	}

	public ArrayList<String>	hrefFilter(List<WebElement> list, String filter)
	{
		ArrayList<String>		links = new ArrayList<String>();
		Iterator<WebElement>	i_scraped = list.iterator();
		Iterator<WebElement>	i_tag;
		WebElement				tag;

		while(i_scraped.hasNext()) {
			i_tag = i_scraped.next().findElements(By.tagName("a")).iterator();
			while(i_tag.hasNext()) {
				tag = i_tag.next();
				if (tag.getAttribute("href") != null && tag.getAttribute("href").contains(filter))
					links.add(tag.getAttribute("href"));
			}
		}
		return (links);
	}

	public ArrayList<String>	hrefFilterScrape(String url, String filter, String className)
	{
		List<WebElement>	scraped_class;
		/*if (filter.equals("Albero_genealogico"))
		{
			driver.get(url);
			scraped_class = driver.findElements(By.xpath("//table[@class = '"+className+"']"));
		}
		else*/
		scraped_class = classScrape(url, className);
		//List<WebElement> 	datas = driver.findElements(By.className("mw-headline"));
		/*Iterator<WebElement>	i_scraped = scraped_class.iterator();
		Iterator<WebElement>	i_tag;
		WebElement				tag;

		while(i_scraped.hasNext()) {
			i_tag = i_scraped.next().findElements(By.tagName("a")).iterator();
			while(i_tag.hasNext()) {
				tag = i_tag.next();
				if (tag.getAttribute("href") != null && tag.getAttribute("href").contains(filter))
					links.add(tag.getAttribute("href"));
			}
		}
		return (links);*/
		return (hrefFilter(scraped_class, filter));
	}

	public ArrayList<String>	hrefXpathFilterScrape(String url, String filter, String className, String xpath)
	{
		List<WebElement>	elements;

		driver.get(url);
		elements = driver.findElements(By.xpath("//" + xpath + "[@class = '" + className + "']"));
		return (hrefFilter(elements, filter));
	}
}