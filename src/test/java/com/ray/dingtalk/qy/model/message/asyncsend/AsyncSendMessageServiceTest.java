package com.ray.dingtalk.qy.model.message.asyncsend;

import org.junit.Test;

import com.ray.dingtalk.qy.auth.AuthHelper;
import com.ray.dingtalk.qy.config.Env;
import com.ray.dingtalk.qy.service.message.asyncsend.AsyncSendMessageService;

/**@desc  : 
 * 
 * @author: shirayner
 * @date  : 2017年10月13日 下午2:36:27
 */
public class AsyncSendMessageServiceTest {

	@Test
	public void testSendNotice() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		AsyncSendMessageService asm=new AsyncSendMessageService();
		asm.sendMessage(accessToken);
	}
	
/*	
	@Test
	public void testSendMessage() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		String msgtype ="text";
		Number agent_id=128838526;
		String msgcontent="发送异步消息测试";
		Boolean to_all_user=true;
		
		AsyncMessage1111 asyncMessage=new AsyncMessage1111();
		asyncMessage.setAgent_id(agent_id);
		asyncMessage.setMsgtype(msgtype);
		asyncMessage.setMsgcontent(msgcontent);
		asyncMessage.setTo_all_user(to_all_user);
		
		
		AsyncSendMessageService.sendAsyncMessage(accessToken, asyncMessage);
	}
	*/
	
}
