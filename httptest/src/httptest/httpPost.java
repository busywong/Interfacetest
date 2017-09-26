package httptest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class httpPost {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPost loginhttPost=new HttpPost("http://study-perf.qa.netease.com/common/fgadmin/login");
		loginhttPost.addHeader("Content-Type","application/json");
		
		StringEntity loginEntity= new StringEntity("{\"phoneArea\":\"86\",\"phoneNumber\":\"20000000001\",\"password\":\"netease123\"}","utf-8");
	    //loginhttPost.setEntity(loginEntity);
	     loginhttPost.setEntity(loginEntity);
	     try {
			httpClient.execute(loginhttPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     
	    HttpPost addaddressPost=new HttpPost("http://study-perf.qa.netease.com/fgadmin/address/new");
	    addaddressPost.addHeader("Content_Type", "application/json");
	    //StringEntity addressEntity=

	}

}
