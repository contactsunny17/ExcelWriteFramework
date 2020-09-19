package scenarios.myTestsRoughWork;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class RestAPI_Test_1 {

	@Test(enabled = false)
	public  void m1() {

		HashMap<String, String> headers = new HashMap();
		headers.put("authorization", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
		Response resp = RestAssured.given().when().headers(headers).get("http://192.168.1.100:3000/data?val=sunny");
		System.out.println(resp.statusCode());
		System.out.println(resp.asString());

		String body = "{\r\n" + 
				"    \"name\" : \"ram\",\r\n" + 
				"    \"returnData\": [\r\n" + 
				"        {\r\n" + 
				"            \"FirstName\": \"ravan\",\r\n" + 
				"            \"LastName\": \"dayal\",\r\n" + 
				"            \"Mobile\": 9370366406,\r\n" + 
				"            \"Company\": \"WellsFargo\",\r\n" + 
				"            \"Designation\":\"SDET\",\r\n" + 
				"            \"IsWorking\": false\r\n" + 
				"        }\r\n" + 
				"    ]\r\n" + 
				"}";
		headers.put("Content-Type", "application/json");
		Response post = RestAssured.given().when().headers(headers).body(body).post("http://192.168.1.100:3000/api");
		System.out.println(post.getStatusCode());
		System.out.println(post.asString());

		// TODO - check if the item has been created or not
		Response getRespOfCreatedItem = RestAssured.given().when().headers(headers).get("http://192.168.1.100:3000/data?val=ram");
		//		if(getRespOfCreatedItem.statusCode()== 200) {
		//			System.err.println( "expected status code is: " + getRespOfCreatedItem.getStatusCode());
		//		}else {
		//			System.err.println("Status code Mismatch");
		//		}
		Assert.assertEquals(getRespOfCreatedItem.getStatusCode(), 200, "Status code missmatch");

		// TODO - check the content , if the expected content is available or not
		Response respCreatedContntCheck = RestAssured.given().when().headers(headers).get("http://192.168.1.100:3000/data?val=ram");
		Assert.assertEquals(respCreatedContntCheck.getStatusCode(), 200, "Status code missmatch");
		String expected = "ravan";
		String strBody = respCreatedContntCheck.asString();
		String strActual = JsonPath.parse(strBody).read("$..FirstName").toString();
		System.out.println(strActual);
		JSONParser parser = new JSONParser();
		JSONArray array = null;
		String actual = "";
		try {
			array = (JSONArray) parser.parse(strActual);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
			actual = array.get(i).toString();
		}
		Assert.assertEquals(actual,"ravan");
	}
	@Test(enabled = false)
	public void m2() {
		HashMap<String,String> headers = new HashMap();
		headers.put("authorization", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
		Response resp = RestAssured.given().when().headers(headers).get("http://192.168.1.100:3000/data?val=sunny");
		System.out.println(resp.statusCode());
	}
	@Test
	public void m3() {

		String filePath = "D:/work/selenium/workspace/sunny_practice/src/test/resources/json/jsonFile.json";
		String data = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			StringBuilder sb = new StringBuilder();
			try {
				String line = br.readLine();
				
				while(line !=null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
//					br.close();(By This print one line only)
				}
				data = sb.toString();
				System.out.println(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HashMap<String,String> headers = new HashMap();
		headers.put("authorization", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
		headers.put("Content-Type", "application/json");
		Response post = RestAssured.given().when().headers(headers).body(data).post("http://192.168.1.107:3000/api");
		System.out.println(post.getStatusCode());
		
		// verify the created item
	}
}

