import java.io.IOException;

import com.alibaba.fastjson.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName sendMessageToWechat
 * @Description TODO
 * @Author jyk
 * @Date 2022/6/10
 * @Version 1.0
 */

public class sendMessageToWechat {
	// 微信接口获取
	final static String GET_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
	// 个人企业微信接口参数
	final static String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
	// 企业微信公司ID
	final static String WECOM_CID = "ww661fdf2f73870634";
	// 企业微信公司密钥
	final static String WECOM_SECRET = "O6iha9BtpiNDVw7sP75B0S2GZT8XohWSqtQcgo37950";
	// 应用ID
	final static String AGENT_ID = "1000002";
	// 发送用户，@all代表企业微信里所有人
//	final static String TOUSER = "@all";
	final static String TOUSER = "JiangYanKai";
	//token缓存
	static String ACCESS_TOKEN = "";


	// 发送失败
	final static int ERROR_CODE = 0;
	// 发送成功
	final static int SUCCESS_CODE = 0;
	// 发送状态
	static int STATUS_CODE = ERROR_CODE;


	private static String HttpRestClient(String url, HttpMethod method, JSONObject json) throws IOException {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10 * 1000);
		requestFactory.setReadTimeout(10 * 1000);
		RestTemplate client = new RestTemplate(requestFactory);
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
		headers.setContentType(type);
		HttpEntity<String> requestEntity = new HttpEntity(json.toString(), headers);
		//  执行HTTP请求
		ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
		return response.getBody();
	}

	private static void getAccessToken() {
		HttpMethod method = HttpMethod.POST;
		JSONObject json = new JSONObject();
		json.put("corpid", WECOM_CID);
		json.put("corpsecret", WECOM_SECRET);
		String result = null;
		try {
			result = sendMessageToWechat.HttpRestClient(GET_ACCESS_TOKEN_URL, method, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject obj = JSON.parseObject(result);
		//将Json字符串转化为Json对象
		String access_token = (String) obj.get("access_token");
		//从Json对象中提取键值为“status”的键值对，并将键值保存在“status”字符串中
		ACCESS_TOKEN = access_token;
	}

	private static void sendMessage(String sendText, String access_token) {
		String url = SEND_MESSAGE_URL + access_token;
		HttpMethod method = HttpMethod.POST;
		JSONObject json = new JSONObject();
		JSONObject jsonText = new JSONObject();
		jsonText.put("content", sendText);
		json.put("touser", TOUSER);
		json.put("agentid", AGENT_ID);
		json.put("msgtype", "text");
		json.put("text", jsonText);
		//发送http请求并返回结果
		try {
			String result = sendMessageToWechat.HttpRestClient(url, method, json);
			JSONObject obj = JSON.parseObject(result);
			//将Json字符串转化为Json对象
			if ((int) obj.get("errcode") == 200) {
				//从Json对象中提取键值为“status”的键值对，并将键值保存在“status”字符串中
				STATUS_CODE = SUCCESS_CODE;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendText(String Text) {
		if (Text == null) {
			return;
		}
		if (StringUtils.isBlank(ACCESS_TOKEN)) {
			getAccessToken();
		}
		if (StringUtils.isNotBlank(ACCESS_TOKEN)) {
			sendMessage(Text, ACCESS_TOKEN);
		}
	}

	public static void main(String args[]) {
		for (int i = 0; i < 2; i++) {
			sendMessageToWechat.sendText("测试发送\n");
		}

	}
}