package httptest;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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
	
	@DataProvider
	public Object[][]data()
	{
		return new  Object[][] 
		{
				{},{},
		};
	}
	
	
	
	@Test
	public void login()
	{
		HttpPost loginhttpPost=new HttpPost("http://study-perf.qa.netease.com/common/fgadmin/login");
		loginhttpPost.setHeader("Content-Type", "application/json");
		StringEntity loginEntity=new StringEntity("{\"phoneArea\":\"86\",\"phoneNumber\":\"20000000009\",\"password\":\"netease123\"}","utf-8");
		loginhttpPost.setEntity(loginEntity);	
		try {
			CloseableHttpResponse loginresponse=httpClient.execute(loginhttpPost);
			
			
			System.out.println(EntityUtils.toString(loginresponse.getEntity()));
			//EntityUtils.consume(loginresponse.getEntity());
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test(dependsOnMethods="login")
	public void addNewAddress()
	{
		
		JSONObject  jsonObject=new JSONObject();
		jsonObject.element("id","");
		jsonObject.element("receiverName","wong2");
		jsonObject.element("cellPhone","18387654321");
		jsonObject.element("province","北京市");
		jsonObject.element("city","西城区");
		jsonObject.element("area","");
		jsonObject.element("addressDetail","未名路380号");
		System.out.println(jsonObject.toString());
		HttpPost httpPost=new HttpPost("http://study-perf.qa.netease.com/fgadmin/address/new");
		httpPost.setHeader("Content-Type","application/json");
		httpPost.setHeader("crsfToken","aab");
		
		StringEntity httpEntity=new StringEntity(jsonObject.toString(),"utf-8");
		httpPost.setEntity(httpEntity);
		
		try {
			CloseableHttpResponse  httpResponse=httpClient.execute(httpPost);
			HttpEntity  responseEntity=httpResponse.getEntity();
			
			//响应实体转化为String 对象
			String resultString=EntityUtils.toString(responseEntity);
			
			
			System.out.println(resultString);
			
			//string对象解析为jsons数据
			JSONObject jsonresult=JSONObject.fromObject(resultString);
			
			//json解析库  提供方法  解析key来获取对应的value数值。
			Assert.assertEquals(jsonresult.getInt("code"), 200);
			Assert.assertEquals(jsonresult.get("message"), "success");
			EntityUtils.consume(responseEntity);
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	@Test(dependsOnMethods="addNewAddress")
	public void deleteaddress()
	{
		/*先做 获取所有地址的请求get，  获取第一个地址，并取出该地址的id 
		 * 获取id 后将id传入  delete post请求中  执行post请求即可
		 * 
		 * */
		
		HttpGet httpGet=new HttpGet("http://study-perf.qa.netease.com/fgadmin/address/list");
		//StringEntity responseEntity= httpGe
		
	}
	
	
}
