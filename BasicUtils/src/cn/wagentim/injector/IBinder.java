package cn.wagentim.injector;

import java.util.List;

public interface IBinder
{
	<T, E> IBinding bind(T t);
	<T, E> List<IBinding> getBindings();
}
