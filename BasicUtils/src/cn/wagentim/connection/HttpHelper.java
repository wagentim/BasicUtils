package cn.wagentim.connection;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public final class HttpHelper
{
	public static boolean isHttpResponseOk(CloseableHttpResponse response)
	{
		StatusLine statusLine = response.getStatusLine();

		if (statusLine.getStatusCode() >= 300)
		{
			return false;
		}

		return true;
	}
	
	public static String getPageContent(final CloseableHttpResponse response) throws IllegalStateException, IOException
	{
		HttpEntity entity = response.getEntity();
		
		return EntityUtils.toString(entity);
	}
}
