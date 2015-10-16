package cn.wagentim.injector;


public final class Injector
{
	private IFactory factory = null;
	private IBinder<?, ?> binder = null;
	
	private Injector(final IFactory factory, final IBinder<?, ?> binder)
	{
		this.factory = factory;
		this.binder = binder;
	}
	
	public static Injector createInjector(final IFactory factory, final IBinder<?, ?> binder)
	{
		return new Injector(factory, binder);
	}

	public <T, E> E getInstance(T source)
	{
		return factory.createInstance(source, binder);
	}
}
