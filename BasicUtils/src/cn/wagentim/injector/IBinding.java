package cn.wagentim.injector;

public interface IBinding
{
	<T, E> IBinding to(E t);
	<T, E> T getSource();
	<T, E> E getTarget();
	void asSingleton();
	boolean isSingleton();
}
