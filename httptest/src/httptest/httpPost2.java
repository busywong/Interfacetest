package httptest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


public class httpPost2 {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		CloseableHttpClient  httpClient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost("http://study-perf.qa.netease.com/common/fgadmin/login");
		
		httpPost.setHeader("Content-Type", "application/json");
		StringEntity entity=new StringEntity("{\"phoneArea\":\"86\",\"phoneNumber\":\"20000000000\",\"password\":\"netease123\"}","utf-8");
		
		httpPost.setEntity(entity);
		
		// get response 
		 try {
			httpClient.execute(httpPost);
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			//HttpEntity entity2=response.getEntity();
			//System.out.println(EntityUtils.toString(entity2));
			//EntityUtils.consume(entity2);
		
		// HttpContext localContext = new BasicHttpContext(); 
		 //CookieStore cookies = new BasicCookieStore(); 

		// localContext.setAttribute(ClientContext.COOKIE_STORE,cookies); 
		 //����ջ��ַ����
		HttpPost httpPost2=new  HttpPost("http://study-perf.qa.netease.com/fgadmin/address/new"); 
		
		httpPost2.setHeader("Content-Type", "application/json");
		//httpPost2.setHeader("Cookie", localContext);
		StringEntity newaddressEntity= new StringEntity("{\"id\":\"\",\"receiverName\":\"����\",\"cellPhone\":\"12345678999\",\"addressDetail\":\"�㽭��ѧ\",\"province\":\"�㽭ʡ\",\"city\":\"������\",\"area\":\"������\"}","utf-8");
		httpPost2.setEntity(newaddressEntity);
		try {
			CloseableHttpResponse httppost2Response=httpClient.execute(httpPost2);
			HttpEntity responseEntity=httppost2Response.getEntity();
			
			System.out.println(EntityUtils.toString(responseEntity));
			
			EntityUtils.consume(responseEntity);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
		
		
	}
	
	
	public  void init() throws  IOException
	{
		
	}

}
