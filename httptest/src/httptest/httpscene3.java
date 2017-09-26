package httptest;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class httpscene3 {

	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
*/
	
	private  CloseableHttpClient httpClient;
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
	@Test
	public void addNewAddress()
	{
		JSONObject  jsonObject=new JSONObject();
		jsonObject.element("receiverName", "����");

		jsonObject.element("cellPhone", "18387654321");
		jsonObject.element("province", "������");
		jsonObject.element("city", "������");
		jsonObject.element("area", "");
		jsonObject.element("addressDetail", "δ��·380��");
		
		HttpPost httpPost=new HttpPost("http://study-perf.qa.netease.com/fgadmin/address/new");
		httpPost.setHeader("Content-Type","allplcation/json");
		httpPost.setHeader("crsfToken","aaa");
		
		StringEntity httpEntity=new StringEntity(jsonObject.toString(),"utf-8");
		
		try {
			CloseableHttpResponse  httpResponse=httpClient.execute(httpPost);
			HttpEntity  responseEntity=httpResponse.getEntity();
			EntityUtils.toString(responseEntity);
			System.out.println(EntityUtils.toString(responseEntity));
			
			EntityUtils.consume(responseEntity);
			httpClient.close();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
	}
}
