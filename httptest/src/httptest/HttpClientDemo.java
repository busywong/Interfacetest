package httptest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientDemo {
	public static void main(String[] srgs) throws IOException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet("https://www.baidu.com");
			response = httpclient.execute(httpGet);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			
			if(entity != null) {
				System.out.println(EntityUtils.toString(entity, "UTF-8"));
			}
			EntityUtils.consume(entity);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			response.close();
			httpclient.close();
		
			
			
			
		}
	}
}
