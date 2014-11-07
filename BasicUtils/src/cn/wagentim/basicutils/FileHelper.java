package cn.wagentim.basicutils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper
{
	public static final boolean ísFileReady(final String filePath)
	{
		final Path path = Paths.get(filePath);

		if(	!Files.exists(path) )
		{
			try
			{
				Files.createFile(path);
				// TODO check if the directory is not there, the create file method can also create the directory?
			}
			catch (IOException e)
			{
				return false;
			}
		}

		return true;
	}

	public static final boolean writeToFile(InputStream is, final String filePath)
	{
		if( !ísFileReady(filePath) )
		{
			return false;
		}

		

		return true;
	}
}
