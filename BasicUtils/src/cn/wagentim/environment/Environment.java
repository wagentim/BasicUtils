package cn.wagentim.environment;

import cn.wagentim.injector.Injector;

/**
 * 
 * @author bihu8398
 *
 */
public final class Environment implements IEnvironment
{
	private Injector injector = null;;
	
	public static IEnvironment createInstance()
	{
		return new Environment();
	}
	
	@Override
	public void setInjector(Injector injector)
	{
		if( null != injector )
		{
			this.injector = injector;
		}
	}

	@Override
	public Injector getInjector()
	{
		return injector;
	}
	
	
}
