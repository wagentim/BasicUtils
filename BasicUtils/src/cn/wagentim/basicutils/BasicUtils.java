package cn.wagentim.basicutils;

public class BasicUtils
{
	public static final boolean isNullOrEmpty(final String input)
	{
		return ( null == input ) || input.isEmpty();
	}

	public static final boolean isNull( final Object object )
	{
		return null == object;
	}
}
