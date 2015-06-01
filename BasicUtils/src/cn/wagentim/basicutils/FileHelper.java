package cn.wagentim.basicutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileHelper
{
	public static final boolean isFileReady(final String filePath)
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

	public static final boolean writeToFile(final String content, final String filePath)
	{
		if( !isFileReady(filePath) || Validator.isNullOrEmpty(content) )
		{
			return false;
		}

		final Path path = Paths.get(filePath);

		try
		{
			Files.write(path, content.getBytes(), StandardOpenOption.WRITE);
		}
		catch (IOException e)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Check the consistency of the data under two different folders 
	 * 
	 * @param directory1
	 * @param directory2
	 * @param report
	 * @return
	 */
	public static final boolean compareFiles(String directory1, String directory2, StringBuffer report)
	{
		if( Validator.isNullOrEmpty(directory2) || Validator.isNullOrEmpty(directory1) )
		{
			report.append(getReportDirectoryIsEmpty());
			return false;
		}
		
		Path p1 = Paths.get(directory1);
		Path p2 = Paths.get(directory2);
		
		if( !Files.exists(p1) || !Files.exists(p2) )
		{
			report.append(getReportDirectoryIsEmpty());
			return false;
		}
		
		if( !Files.isDirectory(p1) || !Files.isDirectory(p2) )
		{
			report.append(getReportIsNotDirectory());
			return false;
		}
		
		File f1 = new File(directory1);
		File f2 = new File(directory2);
		
		compareDirectory(f1, f2, report);
		compareDirectory(f2, f1, report);
		
		return report.length() > 0 ? false : true;
	}

	private static void compareDirectory(File directory1, File direcotry2, StringBuffer report)
	{
		File[] files1 = directory1.listFiles();
		File[] files2 = direcotry2.listFiles();
		
		for(File f : files1)
		{
			File tmp = searchFile(f, files2);
			
			if( null == tmp )
			{
				report.append("[ " + f.getName() + " ]" + " is not exist in [ " + direcotry2 + " ]\n");
			}
			else if( f.isDirectory() && tmp.isDirectory() )
			{
				compareDirectory(f, tmp, report);
			}
		}
	}
	
	private static final File searchFile(File f, File[] files)
	{
		for(File tmp : files)
		{
			if( tmp.getName().equals(f.getName()) )
			{
				return tmp;
			}
		}
		
		return null;
	}
	
	private static String getReportDirectoryIsEmpty()
	{
		return "The Directory does not exist!\n";
	}
	
	private static String getReportIsNotDirectory()
	{
		return "One of the given path is not a directory\n";
	}
	
	public static void main(String[] args)
	{
		String p1 = "G://Pictures";
		String p2 = "J://Pictures";
		StringBuffer sb = new StringBuffer();
		
		FileHelper.compareFiles(p1, p2, sb);
		
		if( sb.length() <= 0 )
		{
			System.out.println("The Files in the given Folders are same!");
		}
		else
		{
			System.out.println(sb.toString());
		}
	}
}
