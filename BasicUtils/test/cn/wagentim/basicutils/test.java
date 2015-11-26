package cn.wagentim.basicutils;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class test extends TestCase
{
	private FileHelper fileHelper;
	
	@Before
	public void setup()
	{
		fileHelper = new FileHelper();
	}
	
	@Test
	public void test()
	{
		fail("Not yet implemented");
	}

}
