package cn.wagentim.basicutils;
import java.util.Random;

public final class PassGenerator
{
	private static final String low_case = "abcdefghijklmnopqrstuvwxyz";
	private static final String up_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String number = "0123456789";
	private static final String special_symbols = "!_?@";
	
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
			char ssymbol = special_symbols.charAt(random.nextInt(special_symbols.length() - 1 ));
			result.append(ssymbol);
		}
		
		int restAmount = length - ( needSpecialSymbol ? 1 : 0 ) - ( needUpCase ? 1 : 0 ) - ( needNumber ? 1 : 0 );
		int count  = 0;
		
		while( count < restAmount )
		{
			char c = low_case.charAt(random.nextInt(low_case.length() - 1 ));
			
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
			char c = up_case.charAt(random.nextInt(up_case.length() - 1 ));
			
			int index = random.nextInt(length - 1);
			if( 0 == index )
			{
				index = 1;
			}
			
			result.insert(index, c);
		}
		
		if( needNumber )
		{
			char c = number.charAt(random.nextInt(number.length() - 1 ));
			
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
