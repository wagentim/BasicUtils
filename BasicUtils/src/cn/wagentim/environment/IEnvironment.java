package cn.wagentim.environment;

import cn.wagentim.injector.Injector;

public interface IEnvironment
{
	void setInjector(Injector injector);
	Injector getInjector();
}
