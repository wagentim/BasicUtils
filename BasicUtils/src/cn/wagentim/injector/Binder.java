package cn.wagentim.injector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Binder<T, E> implements IBinder<T, E>
{
	private final List<IBinding<T, E>> bindings;
	
	public Binder()
	{
		this.bindings = new ArrayList<IBinding<T, E>>();
	}

	@Override
	public IBinding<T, E> bind(T t)
	{
		final Binding<T, E> binding = new Binding<T, E>(t);
		
		if( !isAlreadyExisted(binding) )
		{
			bindings.add(binding);
			return binding;
		}
		
		return null;
	}

	private boolean isAlreadyExisted(Binding<T, E> binding)
	{
		Iterator<IBinding<T, E>> it = bindings.iterator();
		
		while(it.hasNext())
		{
			IBinding<T, E> bind = it.next();
			
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
	public List<IBinding<T, E>> getBindings()
	{
		return bindings;
	}
}
