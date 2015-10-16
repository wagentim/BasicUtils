package cn.wagentim.injector;

public class Binding implements IBinding
{
	
	private Object source;
	private Object target;
	private boolean singleton = false;

	public <T> Binding(T t)
	{
		source = t;
	}
	
	@Override
	public <T, E> IBinding to(E t)
	{
		target = t;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, E> T getSource()
	{
		return (T) source;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, E> E getTarget()
	{
		return (E) target;
	}

	@Override
	public void asSingleton()
	{
		this.singleton = true;
	}

	@Override
	public boolean isSingleton()
	{
		return this.singleton;
	}
	
	
	
}
