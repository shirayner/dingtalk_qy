package com.ray.dingtalk.qy.service.message.notice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.ray.dingtalk.qy.auth.AuthHelper;
import com.ray.dingtalk.qy.config.Env;

/**@desc  : 
 * 
 * @author: shirayner
 * @date  : 2017年9月28日 下午1:16:08
 */
public class NoticeServiceTest {

	@Test
	public void testSendNotice() throws Exception {
		
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		String userId="manager6777";
		String content="ray,你的快递到了";
	
		NoticeService  ns=new NoticeService();
		ns.sendTextNotice(accessToken, userId, content);
		
	}
	
	@Test
	public void testSendMarkdownNotice() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		String userId="manager6777";
		String title="代办事项";
		String title2="费控审批";
		String content="你目前有8条新的代办产生，请及时处理啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = simpleDateFormat.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

		String text="##### "+title2+" \n ###### "+content+"  "+date;
		//String text  ="# 这是支持markdown的文本 \n## 标题2  \n* 列表1 \n![alt 啊](https://img.alicdn.com/tps/TB1XLjqNVXXXXc4XVXXXXXXXXXX-170-64.png)";
		
		NoticeService  ns=new NoticeService();
		ns.sendMarkdownNotice(accessToken, userId, title, text);
	}
	
	@Test
	public void testSendLinkNotice() throws Exception {
		
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		String userId="manager6777";
		String messageUrl="www.baidu.com";
		String picUrl="";
		String title="费控审批";
		String text="你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看<a href=\"http://work.weixin.qq.com\">邮件中心视频实况</a>，聪明避开排队。";
	
		NoticeService  ns=new NoticeService();
		ns.sendLinkNotice(accessToken, userId, messageUrl, picUrl, title, text);
		
	}
	
}
