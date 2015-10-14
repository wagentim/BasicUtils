package cn.wagentim.basicutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for handling all file related In/Out 
 * 
 * @author bihu8398
 *
 */
public final class FileHelper
{
	private static final Logger logger = LogManager.getLogger(FileHelper.class);
	
	public static final boolean checkFile(final String filePath)
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
		if( !checkFile(filePath) || Validator.isNullOrEmpty(content) )
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
	
	/**
	 * Check require the file. If file do not exist then we need to create a new one
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean getOrCreateFile(final String filePath)
	{
		if( Validator.isNullOrEmpty(filePath) )
		{
			logger.error("FileHelper#getOrCreateFile the given String parameter is Null or Empty");
			return false;
		}
		
		if( !checkDirectoryRecusively(filePath) )
		{
			logger.error("FileHelper#getOrCreateFile Cannot create the missing directoies");
			return false;
		}
		
		Path path = Paths.get(filePath);
		
		if( Files.exists(path) )
		{
			return true;
		}
		
		try
		{
			Files.createFile(path);
			return true;
		} catch (IOException e)
		{
		}
		
		return false;
	}

	/**
	 * Check the directory. If some of it are missing, then it will create them automatically and recusively
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean checkDirectoryRecusively(String filePath)
	{
		if( Validator.isNullOrEmpty(filePath) )
		{
			logger.error("FileHelper#checkDirectory the given String parameter is Null or Empty");
			return false;
		}
		
		Path path = Paths.get(filePath);
		
		if( Files.exists(path) )
		{
			return true;
		}
		
		checkDirectoryPath(path.getParent());
		
		if( !Files.exists(path) )
		{
			try
			{
				Files.createFile(path);
				return true;
			} 
			catch (IOException e)
			{
			}
		}
		
		return false;
	}
	
	private static void checkDirectoryPath(Path path)
	{
		if( !Files.exists(path) )
		{
			checkDirectoryPath(path.getParent());
			try
			{
				Files.createDirectories(path);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			return;
		}
	}
}
