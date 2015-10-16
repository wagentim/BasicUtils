package cn.wagentim.injector;

public interface IFactory
{
	<T, E> E createInstance(T source, IBinder<?, ?> binder);
}
