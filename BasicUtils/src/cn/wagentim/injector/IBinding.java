package cn.wagentim.injector;

public interface IBinding<T, E>
{
	IBinding<T, E> to(E t);
	T getSource();
	E getTarget();
	void asSingleton();
	boolean isSingleton();
}
