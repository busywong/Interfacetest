package httptest;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
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
import org.testng.annotations.Test;



public class HttpScene2 {

	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	*/
	private  CloseableHttpClient httpClient;
	 private Double transportFee;
	 private String address;
	@BeforeClass
	public  void init() throws IOException
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
	public void login()
	{
		HttpPost loginhttpPost=new HttpPost("http://study-perf.qa.netease.com/common/fgadmin/login");
		loginhttpPost.setHeader("Content-Type", "application/json");
		StringEntity loginEntity=new StringEntity("{\"phoneArea\":\"86\",\"phoneNumber\":\"20000000000\",\"password\":\"netease123\"}","utf-8");
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
	public void getaddress()
	{
		HttpGet httpGet=new HttpGet("http://study-perf.qa.netease.com/fgadmin/address/list");
		
		try {
			CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
			HttpEntity responseEntity=httpResponse.getEntity();
			address=EntityUtils.toString(responseEntity);
			System.out.println(EntityUtils.toString(responseEntity));
			EntityUtils.consume(responseEntity);
			
			httpResponse.close();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test(dependsOnMethods="getaddress")
	public void  gettransportFee()
	{
		JSONObject json=JSONObject.fromObject(address);
		
		JSONObject result=json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		
		String id =result.getString("id");
		String addressDetailString= result.getString("province")+"_"+result.getString("city")+"_"+result.getString("area");
		
		String url= String.format("http://study-perf.qa.netease.com/common/getTransportFee?id=%s&addressDetail=%s", id,addressDetailString);
		HttpGet  httpGet=new HttpGet(url);
		
		try {
			CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
			HttpEntity responseEntity=httpResponse.getEntity();
			String feeResultString=EntityUtils.toString(responseEntity);
			System.out.println(feeResultString);
			JSONObject  jsonFee=JSONObject.fromObject(feeResultString);
			transportFee=jsonFee.getDouble("result");
			
			EntityUtils.consume(responseEntity);
			httpResponse.close();
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test(dependsOnMethods="gettransportFee")
	public   void submit()
	{
		
		JSONObject  jsonObject=JSONObject.fromObject(address);
		String skuIds="2";
		JSONObject result= jsonObject.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		String receiverName=result.getString("receiverName");
		String cellPhone=result.getString("cellPhone");
		String addessDetail=result.getString("addessDetail");

		String province=result.getString("province");
		String city=result.getString("city");
		String area=result.getString("area");
		Double fee=transportFee;
		
		JSONObject jsonpost= new JSONObject();
		jsonpost.element("skuIds", skuIds);
		jsonpost.element("skuNumbers", 74966313);
		jsonpost.element("voiceStatus", 0);
		jsonpost.element("needInvoice", 0);
		jsonpost.element("invoiceHead", "");
		jsonpost.element("logisticsCompanyId", 1);
		jsonpost.element("accessSource", "noSource");
		
		
		jsonpost.element("receiverName", receiverName);
		jsonpost.element("cellPhone", cellPhone);
		jsonpost.element("addessDetail", addessDetail);
		jsonpost.element("province", province);
		jsonpost.element("city", city);
		jsonpost.element("area", area);
		jsonpost.element("fee", fee);
	
		//��ʼ��HTTP
		HttpPost  httpPost=new HttpPost("http://study-perf.qa.netease.com/fgadmin/orders/submit");
		httpPost.setHeader("crsfToken","aaa");
		httpPost.setHeader("Content_Type","application/json");
		StringEntity httpEntity =new StringEntity(jsonpost.toString(), "utf-8");
		httpPost.setEntity(httpEntity);
		
		try {
			CloseableHttpResponse  httpResponse=httpClient.execute(httpPost);
			String submitresult=EntityUtils.toString(httpResponse.getEntity());
			System.out.println(submitresult);
			JSONObject jsonresult=JSONObject.fromObject(submitresult);
			//assert д��
			Assert.assertEquals(200, jsonresult.getInt("code"));
	
			EntityUtils.consume(httpResponse.getEntity());
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
