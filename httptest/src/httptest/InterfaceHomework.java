package httptest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class InterfaceHomework {

	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	*/
	private CloseableHttpClient httpClient;
	@BeforeClass
	public void init()
	{
	httpClient=HttpClients.createDefault();
	}
	@AfterClass
	public void teardown()
	{
		try {
			httpClient.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	@Test
	public  void skulist()
	{
		HttpGet httpGet=new HttpGet("http://study-perf.qa.netease.com/common/skuList");
		httpGet.setHeader("Content-Type","application/json");
		try {
			CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
			String responseString =EntityUtils.toString(httpResponse.getEntity());
			System.out.println(responseString);
			EntityUtils.consume(httpResponse.getEntity());
			//httpClient.close();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/

	@DataProvider
	public    Object[][] data()
	{
		
		return new Object[][]
				{
				{2},{1},{9999},{"1"}
				};
		
	}
	
	
	@Test(dataProvider="data")
	public void skulistgoodsid(Object newdata)
	{
		
		String url=String.format("http://study-perf.qa.netease.com/common/skuList?goodsId=%s", newdata);
		HttpGet httpGet=new HttpGet(url);
		
		httpGet.setHeader("Content-Type","application/json");
		try {
			
			CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
			HttpEntity responseEntity=httpResponse.getEntity();
			String responseString =EntityUtils.toString(responseEntity);
			System.out.println(responseString);
			
			//Assert.assertTrue(responseString.contains(String.valueOf(code)));
			EntityUtils.consume(responseEntity);
			//httpClient.close();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

	public void  addnewaddress()
	{
		
	}
	
	

}


