import java.util.ArrayList;

public class Dinasty_Node
{
	private String				name;
	private ArrayList<Dinasty_Node>	consorts;
	private ArrayList<Dinasty_Node>	sons;

	public	Dinasty_Node(String _name)
	{
		name = _name;
		consorts = new ArrayList<Dinasty_Node>();
		sons = new ArrayList<Dinasty_Node>();
	}
	
	public String	getName()
	{
		return (name);
	}

	public boolean	searchRelative(String _name)	// can cause infinite loops
	{
		for (Dinasty_Node consort : consorts)
		{
			if (consort.searchRelative(_name))
				return (true);
		}
		for (Dinasty_Node son : sons)
		{
			if (son.searchRelative(_name))
				return (true);
		}
		if (name.equals(_name))
			return (true);
		return (false);
	}

	public boolean	searchConsort(String _name)
	{
		for (Dinasty_Node consort : consorts)
		{
			if (consort.getName().equals(_name))
				return (true);
		}
		return (false);
	}

	public boolean	searchSon(String _name)
	{
		for (Dinasty_Node consort : consorts)
		{
			if (consort.getName().equals(_name))
				return (true);
		}
		return (false);
	}

	public void	addSon(Dinasty_Node new_son)
	{
		if (!searchSon(new_son.getName()))
			sons.add(new_son);
	}

	public void	addConsort(Dinasty_Node new_consort)
	{
		if (!searchConsort(new_consort.getName()))
			consorts.add(new_consort);
	}
}
