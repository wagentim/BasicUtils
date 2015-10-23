package cn.wagentim.basicutils;
import java.util.Random;

public final class PassGenerator
{
	private static final String CASE_LOW = "abcdefghijklmnopqrstuvwxyz";
	private static final String CASE_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMBER = "0123456789";
	private static final String SPECIAL_SYMBOLS = "!_?@";
	
	private boolean needSpecialSymbol = false;
	private boolean needUpCase = false;
	private int specialSymbolLocation = 1;
	private int length = 8;
	private boolean needNumber = true;
	
	public String getPass()
	{
		StringBuffer result = new StringBuffer();
		Random random = new Random();
		
		if( needSpecialSymbol )
		{
			char ssymbol = SPECIAL_SYMBOLS.charAt(random.nextInt(SPECIAL_SYMBOLS.length() - 1 ));
			result.append(ssymbol);
		}
		
		int restAmount = length - ( needSpecialSymbol ? 1 : 0 ) - ( needUpCase ? 1 : 0 ) - ( needNumber ? 1 : 0 );
		int count  = 0;
		
		while( count < restAmount )
		{
			char c = CASE_LOW.charAt(random.nextInt(CASE_LOW.length() - 1 ));
			
			if(needSpecialSymbol && specialSymbolLocation == 0)
			{
				result.insert(0, c);
			}
			else
			{
				result.append(c);
			}
			
			count++;
		}
		
		if( needUpCase )
		{
			char c = CASE_UPPER.charAt(random.nextInt(CASE_UPPER.length() - 1 ));
			
			int index = random.nextInt(length - 1);
			if( 0 == index )
			{
				index = 1;
			}
			
			result.insert(index, c);
		}
		
		if( needNumber )
		{
			char c = NUMBER.charAt(random.nextInt(NUMBER.length() - 1 ));
			
			int index = random.nextInt(length - 1);
			if( 0 == index )
			{
				index = 1;
			}
			
			result.insert(index, c);
		}
		
		return result.toString();
	}
	
	public static void main(String[] args)
	{
		PassGenerator pg = new PassGenerator();
		int i = 1;
		while(i > 0)
		{
			System.out.println(pg.getPass());
			i--;
		}
	}
}
