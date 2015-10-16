package cn.wagentim.injector;

public class Binding<T, E> implements IBinding<T, E>
{
	private T source;
	private E target;
	private boolean singleton = false;
	
	public Binding(T t)
	{
		this.source = t;
	}

	@Override
	public IBinding<T, E> to(E e)
	{
		this.target = e;
		return this;
	}

	@Override
	public T getSource()
	{
		return source;
	}

	@Override
	public E getTarget()
	{
		return target;
	}

	@Override
	public void asSingleton()
	{
		singleton = true;
	}

	@Override
	public boolean isSingleton()
	{
		return singleton;
	}
	
}
