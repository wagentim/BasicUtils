package cn.wagentim.connection;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class GetFile extends AbstractConnector
{

	private final CloseableHttpClient httpClient;
	private final HttpContext context;
	private final HttpGet httpget;

	public GetFile(
			CloseableHttpClient httpClient, HttpGet httpget)
	{
		this.httpClient = httpClient;
		this.context = HttpClientContext.create();
		this.httpget = httpget;
	}

	public void run()
	{
		try
		{
			CloseableHttpResponse response = httpClient.execute(httpget,
					context);
			try
			{
				if (response.getStatusLine().getStatusCode() < 300)
				{
					HttpEntity entity = response.getEntity();
					long fileSize = entity.getContentLength();
					String filePath = getFilePath();
					String fileName = "a";
					if( fileSize > 0 )
					{
//						InputStream is = entity.getContent();
//						File file = new File(filePath);
//						FileOutputStream fos = new FileOutputStream(file);
//						byte[] cache = new byte[1024];
//						int read;
//						while( (read = is.read(cache)) > 0 )
//						{
//							fos.write(cache);
//						}
					}
					else
					{
					}
					EntityUtils.consume(entity);
				}
			} finally
			{
				response.close();
			}
			
		} catch (ClientProtocolException ex)
		{
			// Handle protocol errors
		} catch (IOException ex)
		{
			// Handle I/O errors
		}
	}

	private String getFilePath()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
