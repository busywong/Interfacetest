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

public class Interfacehomework2 {
	CloseableHttpClient httpClient;	
	
	
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
	public void login()
	{
		//CloseableHttpClient httpClient=HttpClients.createDefault();
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
		
		
		HttpPost addnewaddressPosta=new HttpPost("http://study-perf.qa.netease.com/fgadmin/address/new");
		
		addnewaddressPosta.setHeader("Content-Type","application/json");
		//StringEntity posteEntity=
		
		
		/*
		String url="http://study-perf.qa.netease.com/common/skuList?goodsId=%s";
		String.format(url, newdata);
		HttpGet httpGet=new HttpGet(url);
		*/
	}
	
	@Test(dependsOnMethods="login")
	public void addnewaddress()
	{
		HttpPost addnewaddressPost=new HttpPost();
	}
	int id0;
	@Test(dependsOnMethods="addNewAddress")
	public void deleteaddress()
	{
		/*先做 获取所有地址的请求get，  获取第一个地址，并取出该地址的id 
		 * 获取id 后将id传入  delete post请求中  执行post请求即可
		 * 验证时需要 请求所有地址查看数据库中是否删除
		 * */
		
		HttpGet httpGet=new HttpGet("http://study-perf.qa.netease.com/fgadmin/address/list");
		try {
			CloseableHttpResponse getaddressresponse=httpClient.execute(httpGet);
			HttpEntity  responseEntity=getaddressresponse.getEntity();
			String responseString=EntityUtils.toString(responseEntity);
			
			JSONObject responsejson=JSONObject.fromObject(responseString);
			JSONObject list0json=responsejson.getJSONObject("result").getJSONArray("list").getJSONObject(0);
			 id0 =list0json.getInt("id");
			 
			 Assert.assertEquals(responsejson.getInt("code"), 400);

			 Assert.assertEquals(responsejson.getString("message"), "最多只能添加6个地址");
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpPost deletePost=new HttpPost("http://study-perf.qa.netease.com/fgadmin/address/delete");
		deletePost.setHeader("Content-Type","application/json");
		deletePost.setHeader("crsfToken","crsfToken");
		String deleteid=String.format("id:%s", id0);
		//StringEntity deletepoStringEntity=new StringEntity("id:"+id0,"utf-8");
		StringEntity deleteEntity=new StringEntity(deleteid,"utf-8");
		
		deletePost.setEntity(deleteEntity);
		
		try {
			CloseableHttpResponse deleteResponse=httpClient.execute(deletePost);
			HttpEntity responseEntity=deleteResponse.getEntity();
			String responseString=EntityUtils.toString(responseEntity);
			System.out.println(responseString);
			
			JSONObject responsejsona=JSONObject.fromObject(responseString);
			;
			
			Assert.assertEquals(responsejsona.getInt("code"), 200);

			Assert.assertEquals(responsejsona.getString("message"), "success");
			//进一步验证
			
			
			EntityUtils.consume(responseEntity);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test(dependsOnMethods="deleteaddress")
	public void addnewaddress2()
	{
		
		JSONObject jsonbodya=new JSONObject();
		//jsonbodya.element(key, value);
		//jsonbodya.element("id","");
		jsonbodya.element("receiverName","wong2");
		jsonbodya.element("cellPhone","18387654321");
		jsonbodya.element("province","浙江省");
		jsonbodya.element("city","杭州市");
		jsonbodya.element("area","滨江区");
		jsonbodya.element("addressDetail","西兴街道1215号");
		HttpPost addnewaddresspost2=new HttpPost("http://study-perf.qa.netease.com/fgadmin/address/new");
		addnewaddresspost2.setHeader("crsfToken","aa");
		addnewaddresspost2.setHeader("Content-Type","application/json");
		String jsonString=jsonbodya.toString();
		StringEntity addnewaddresspost2Entity=new StringEntity(jsonString,"utf-8");
		
		try {
			CloseableHttpResponse addnewaddressResponse=httpClient.execute(addnewaddresspost2);
			HttpEntity addnewaddressresponseEntity=addnewaddressResponse.getEntity();
			String addnewaddressresponseString=EntityUtils.toString(addnewaddressresponseEntity);
			
			System.out.println(addnewaddressresponseString);
			
			JSONObject addnewaddressresponsejson=JSONObject.fromObject(addnewaddressresponseString);
			Assert.assertEquals(addnewaddressresponsejson.getInt("code"), 200);
			Assert.assertEquals(addnewaddressresponsejson.getString("message"), "success");
			
			EntityUtils.consume(addnewaddressresponseEntity);
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
