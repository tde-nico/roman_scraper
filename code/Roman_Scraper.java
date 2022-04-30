import java.util.ArrayList;

public class Roman_Scraper
{
	private ArrayList<String>	dinasties;
	private ArrayList<String>	dinasty_links;
	private	Scraper				scraper;
	private String				url;
	private String				dinasty_link_tree;

	public Roman_Scraper(String link)
	{
		dinasties = new ArrayList<String>();
		dinasty_links = new ArrayList<String>();
		scraper = new Scraper();
		url = link;
	}

	public void	exit()
	{
		scraper.exit();
		System.out.println("\n\n[+] Done");
	}

	public void	initDinaties()
	{
		dinasty_links = scraper.hrefFilterScrape(url, "Dinastia", "mw-headline");
		dinasty_links.forEach(link -> dinasties.add(link.split("/")[link.split("/").length -1]));
	}

	public void scrapeDinasty(int index)
	{
		ArrayList<String> 	dinasty_links_tree;
		String				link = dinasty_links.get(index);
		ArrayList<String>	names;
		//Dinasty_Node		d;

		dinasty_links_tree = scraper.hrefXpathFilterScrape(link, "Albero_genealogico", "vedi-anche noprint", "table");
		print(dinasty_links_tree);
		System.out.println();
		dinasty_link_tree = dinasty_links_tree.get(0);
		names = scraper.hrefXpathScrape(dinasty_link_tree, "td[@colspan='2']"); 
		//names = scraper.textXpathScrape(dinasty_link_tree, "td[@colspan='2']");
		for (String name : names)
		{
			/*name = name.replaceAll("[^A-Za-z0-9]", " ").replaceAll(" +", " ").strip();
			if (name.equals(""))		// get via href ?
				continue ;*/
			
			System.out.println("\n|"+name+"|\n");	
			
			//scraper.hrefXpathFilterScrape(name, "Albero_genealogico", "sinottico", "");
		}
		print(scraper.hrefFilterScrape(names.get(0), "", "sinottico"));
	}

	public ArrayList<String> getDinasties()
	{
		return (dinasties);
	}

	public ArrayList<String> getDinasty_links()
	{
		return (dinasty_links);
	}

	public static void print(ArrayList<String> array)
	{
		array.forEach(element -> System.out.println(element));
	}
	public static void main(String args[])
	{
		Roman_Scraper r = new Roman_Scraper("https://it.wikipedia.org/wiki/Imperatori_romani");
		r.initDinaties();
		print(r.getDinasties());
		System.out.println();
		print(r.getDinasty_links());
		System.out.println();
		r.scrapeDinasty(0);
		r.exit();
	}	
}
