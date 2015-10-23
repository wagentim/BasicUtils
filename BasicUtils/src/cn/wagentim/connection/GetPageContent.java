
package cn.wagentim.connection;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.wagentim.basicutils.StringConstants;
import cn.wagentim.basicutils.Validator;

public class GetPageContent extends AbstractConnector
{
    private String pageContent = StringConstants.EMPTY_STRING;
    private HttpClientContext context = null;
    private CloseableHttpClient httpclient = null;
    private Logger logger = LogManager.getLogger(GetPageContent.class);

    public GetPageContent()
    {
    	context = HttpClientContext.create();
    	context.setCookieStore(getCookieStore());
    	httpclient = HttpClients.createDefault();
    }
    
    public void run(String url)
    {
    	if( Validator.isNullOrEmpty(url) )
    	{
    		logger.error("URL is NULL or Empty");
    		pageContent = StringConstants.EMPTY_STRING;
    		return;
    	}
    	
    	URI uri = null;
		
    	try
		{
			uri = new URI(url);
		} 
		catch (URISyntaxException e1)
		{
			uri = null;
			logger.error("GetPageContent#run Cannot create URI object");
			pageContent = StringConstants.EMPTY_STRING;
			return;
		}
    	
    	logger.info("Process the link: " + url);
    	
        HttpGet httpget = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try
        {
            response = httpclient.execute(httpget, context);
            if( !HttpHelper.isHttpResponseOk(response) )
            {
            	logger.error(response.getStatusLine().getReasonPhrase());
            	pageContent = StringConstants.EMPTY_STRING;
            	return;
            }
            
            pageContent = HttpHelper.getPageContent(response);
            EntityUtils.consume(response.getEntity());
        }
        catch ( IOException e )
        {
        	logger.error("Cannot get required web page!");
        }
        finally
        {
            try
            {
                response.close();
            }
            catch ( IOException e )
            {
            	logger.error("Close Response Error");
            }
        }
    }
    
    public void close()
    {
    	if( null != context )
    	{
    		context = null;
    	}
    	
    	if( null != httpclient )
    	{
    		try
			{
				httpclient.close();
			} 
    		catch (IOException e)
			{
    			logger.error("Close Http Client Error");
			}
    		httpclient = null;
    	}
    }

    public String getPageContent()
    {
        return pageContent;
    }

}
