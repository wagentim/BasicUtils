package cn.wagentim.injector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Binder implements IBinder
{
	private final List<IBinding> bindings;
	
	public Binder()
	{
		this.bindings = new ArrayList<IBinding>();
	}

	@Override
	public <T, E> IBinding bind(T t)
	{
		final Binding binding = new Binding(t);
		
		if( !isAlreadyExisted(binding) )
		{
			bindings.add(binding);
			return binding;
		}
		
		return null;
	}

	private <T, E> boolean isAlreadyExisted(Binding binding)
	{
		Iterator<IBinding> it = bindings.iterator();
		
		while(it.hasNext())
		{
			IBinding bind = it.next();
			
			if( null == bind )
			{
				continue;
			}
			
			if( bind.getSource() == binding.getSource() )
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public <T, E> List<IBinding> getBindings()
	{
		return bindings;
	}
}
