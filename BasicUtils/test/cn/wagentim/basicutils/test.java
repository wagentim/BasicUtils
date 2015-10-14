package cn.wagentim.basicutils;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class test extends TestCase
{
	private FileHelper fileHelper;
	
	@Mock Logger logger;
	
	@Before
	public void setup()
	{
		fileHelper = new FileHelper(logger);
	}
	
	@Test
	public void test()
	{
		fail("Not yet implemented");
	}

}
