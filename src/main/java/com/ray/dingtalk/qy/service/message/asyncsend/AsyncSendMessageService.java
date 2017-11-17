package com.ray.dingtalk.qy.service.message.asyncsend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ray.dingtalk.qy.model.message.asyncsend.AsyncMessage;
import com.ray.dingtalk.qy.util.HttpHelper;

/**@desc  : 主动发送异步消息
 * 
 * @author: shirayner
 * @date  : 2017年10月13日 下午1:39:46
 */
public class AsyncSendMessageService {
	private static final String SEND_ASYNCSENDMESSAGE_URL="https://eco.taobao.com/router/rest";



	public void sendMessage(String accessToken) throws Exception{
		//1.封装请求参数
		String method="dingtalk.corp.message.corpconversation.asyncsend";
		String session =accessToken;
		String timestamp= String.valueOf( System.currentTimeMillis() / 1000 );
		String v="2.0";

		String msgtype ="text";
		Number agent_id=128838526;
		String msgcontent="发送异步消息测试";
		Boolean to_all_user=true;

		AsyncMessage cca=new AsyncMessage();
		cca.setMethod(method);
		cca.setSession(session);
		cca.setTimestamp(timestamp);
		cca.setV(v);

		cca.setMsgtype(msgtype);
		cca.setAgent_id(agent_id);
		cca.setMsgcontent(msgcontent);
		cca.setTo_all_user(to_all_user);

		//1.准备POST请求参数
		Object data=JSON.toJSON(cca);      //将javaBean 转为 json
		System.out.println(data);

		//2.获取请求url
		String url=SEND_ASYNCSENDMESSAGE_URL;

		//3.发起POST请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doPost(url, data);
		System.out.println("jsonObject:"+jsonObject.toString());

		//4.解析结果，获取
		if (null != jsonObject) {  

			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   		

	}
/*

	public static JSONObject sendAsyncMessage(String accessToken,  AsyncMessage1111 asyncMessage) throws Exception  {
		String url=SEND_ASYNCSENDMESSAGE_URL;
		//1.生成一个请求
		HttpPost httpPost = new HttpPost(url);

		//2.配置请求的属性
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
		httpPost.setConfig(requestConfig);

		//设置数据传输格式
		//httpPost.addHeader("Content-Type", "application/json");
		
		
        // 3.1准备请求参数
		String method="dingtalk.corp.message.corpconversation.asyncsend";
		String session =accessToken;
		String timestamp= String.valueOf( System.currentTimeMillis() / 1000 );
		String v="2.0";

		String msgtype ="text";
		Number agent_id=128838526;
		String msgcontent="发送异步消息测试";
		Boolean to_all_user=true;
		
		
		// 4.1 设置请求的header
		//公共参数
		httpPost.addHeader("method",method);
		httpPost.addHeader("session",session);
		httpPost.addHeader("timestamp",timestamp);
		httpPost.addHeader("v",v);
		

		//3.发起请求，获取响应信息	
		//3.1 创建httpClient 
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;

		try {
			//3.2 发起请求，获取响应信息	
			StringEntity requestEntity = new StringEntity(JSON.toJSONString(asyncMessage), "utf-8");
			httpPost.setEntity(requestEntity);

			response = httpClient.execute(httpPost, new BasicHttpContext());

			if (response.getStatusLine().getStatusCode() != 200) {

				System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
						+ ", url=" + url);
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String resultStr = EntityUtils.toString(entity, "utf-8");

				JSONObject result = JSON.parseObject(resultStr);
				if (result.getInteger("errcode") == 0) {
					//result.remove("errcode");
					//result.remove("errmsg");
					return result;
				} else {
					System.out.println("request url=" + url + ",return value=");
					System.out.println(resultStr);
					int errCode = result.getInteger("errcode");
					String errMsg = result.getString("errmsg");
					throw new Exception("error code:"+errCode+", error message:"+errMsg); 
				}
			}
		} catch (IOException e) {
			System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (response != null) try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
*/

}
