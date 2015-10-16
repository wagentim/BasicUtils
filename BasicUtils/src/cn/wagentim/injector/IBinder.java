package cn.wagentim.injector;

import java.util.List;

public interface IBinder<T, E>
{
	IBinding<T, E> bind(T t);
	List<IBinding<T, E>> getBindings();
}
