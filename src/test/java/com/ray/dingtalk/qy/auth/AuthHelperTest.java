package com.ray.dingtalk.qy.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.ray.dingtalk.qy.config.Env;

/**@desc  : 
 * 
 * @author: shirayner
 * @date  : 2017年9月27日 下午11:42:04
 */
public class AuthHelperTest {
	private static final Logger log = LogManager.getLogger(AuthHelperTest.class);
	
	
	@Test
	public void testGetAccessToken() throws Exception {
		
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		log.info("accessToken:"+accessToken);
	}
	
	@Test
	public void testGetJsapiTicket() throws Exception {
		
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		log.info("accessToken:"+accessToken);
		
		String ticket=AuthHelper.getJsapiTicket(accessToken);
		log.info("ticket:"+ticket);
	}
	
	
	@Test
	public void testLog4j() {

		
		
		
	}
	
}
