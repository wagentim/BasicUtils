package cn.wagentim.connection;

import org.apache.http.impl.client.BasicCookieStore;

public abstract class AbstractConnector extends Thread
{
    private BasicCookieStore cookie = null;

    public void setCookieStore(final BasicCookieStore cookie)
    {
        this.cookie = cookie;
    }

    public BasicCookieStore getCookieStore()
    {
        if( null == cookie )
        {
            cookie = new BasicCookieStore();
        }
        return cookie;
    }
}
