package cn.wagentim.basicutils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Utility class for handling all file related In/Out 
 * 
 * @author bihu8398
 *
 */
public final class FileHelper
{
	
	public final String readTextFile(final String filePath)
	{
		if( !checkFile(filePath, false) )
		{
			return StringConstants.EMPTY_STRING;
		}
		
		final Path path = Paths.get(filePath);

		List<String> allLines = null;
		
		try
		{
			allLines = Files.readAllLines(path, Charset.forName("utf8"));
		}
		catch (IOException e)
		{
			return StringConstants.EMPTY_STRING;
		}
		
		if( null == allLines )
		{
			return StringConstants.EMPTY_STRING;
		}
		else
		{
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0; i < allLines.size(); i++)
			{
				sb.append(allLines.get(i));
			}
			
			return sb.toString();
		}
	}
	
	public final boolean checkFile(final String filePath, final boolean createMissingDirectory)
	{
		if( !checkDirectoryRecusively(filePath, createMissingDirectory) )
		{
			return false;
		}
		
		final Path path = Paths.get(filePath);

		if(	!Files.exists(path) )
		{
			try
			{
				Files.createFile(path);
			}
			catch (IOException e)
			{
				return false;
			}
		}

		return true;
	}

	public final boolean writeToFile(final String content, final String filePath)
	{
		if( !checkFile(filePath, true) || Validator.isNullOrEmpty(content) )
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
	public final boolean compareFiles(String directory1, String directory2, StringBuffer report)
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

	private void compareDirectory(File directory1, File direcotry2, StringBuffer report)
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
	
	private final File searchFile(File f, File[] files)
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
	
	private String getReportDirectoryIsEmpty()
	{
		return "The Directory does not exist!\n";
	}
	
	private String getReportIsNotDirectory()
	{
		return "One of the given path is not a directory\n";
	}
	
	/**
	 * Check require the file. If file do not exist then we need to create a new one
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean getOrCreateFile(final String filePath)
	{
		if( Validator.isNullOrEmpty(filePath) )
		{
			return false;
		}
		
		if( !checkDirectoryRecusively(filePath, true) )
		{
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
	 * Check the directory. If some of it are missing, then it will create them automatically and recursively
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean checkDirectoryRecusively(final String filePath, final boolean createMissing)
	{
		if( Validator.isNullOrEmpty(filePath) )
		{
			return false;
		}
		
		Path path = Paths.get(filePath);
		
		for( int i = 0; i < 2; i++)
		{
    		if( !Files.isDirectory(path) )
    		{
    		    if( i == 1 )
    		    {
    		        return false;
    		    }
    		    
    		    path = path.getParent();
    		}
    		else
    		{
    		    break;
    		}
		}
		
		if( createMissing )
		{
		    checkDirectoryPathWithCreation(path);
		}
		else
		{
		    return Files.exists(path);
		}
		
		return false;
	}
	
	private void checkDirectoryPathWithCreation(final Path path)
	{
		if( !Files.exists(path) )
		{
		    checkDirectoryPathWithCreation(path.getParent());
		    
			try
			{
				Files.createDirectories(path);
			} 
			catch (IOException e)
			{
			}
		}
		else
		{
			return;
		}
	}
}
