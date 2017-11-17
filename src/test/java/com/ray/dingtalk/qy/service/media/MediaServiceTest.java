package com.ray.dingtalk.qy.service.media;

import java.util.UUID;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.ray.dingtalk.qy.auth.AuthHelper;
import com.ray.dingtalk.qy.config.Env;

/**@desc  : 
 * 上传的媒体文件限制
            图片（image）     :1MB，支持JPG格式
            语音（voice）     ：2MB，播放长度不超过60s，AMR格式
            普通文件（file）：10MB
 * 
 * @author: shirayner
 * @date  : 2017年10月16日 上午9:22:38
 */
public class MediaServiceTest {

	@Test
	public void testUploadIamge() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		System.out.println("accessToken:"+accessToken);
		String type="image";
		String filePath="D:/img/1.jpg";
		//String filePath="D:/img/2.png";
		JSONObject returnJson=MediaService.uploadImage(accessToken, type, filePath);
		System.out.println("returnJson:"+returnJson.toJSONString());
	
	}
	
	@Test
	public void testUploadMedia() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		System.out.println("accessToken:"+accessToken);
		String type="image";
		String filePath="D:/img/1.jpg";
		//String filePath="D:/img/2.png";
		JSONObject returnJson=MediaService.uploadMedia(accessToken, type, filePath);
		System.out.println("returnJson:"+returnJson.toJSONString());
	
	}
	
	@Test
	public void testDownloadMedia() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
        //String mediaId="@lADPBY0V4pscJ1TNBQDNA-U";
       // String mediaId="@lADPBY0V4pwlFeLNBDjNB4A";
        String mediaId="@lADPBbCc1QwC00nNBlTNBDg";
        String nonceStr=UUID.randomUUID().toString();
		//String fileDir="D:/img/downM1.jpg";
		String fileDir=System.getProperty("user.dir").replaceAll("\\\\", "/")+"/WebContent/img/"+mediaId+"_"+nonceStr+".jpg";

		System.out.println("fileDir:"+fileDir);
		
		MediaService.downloadMedia(accessToken, mediaId, fileDir);
		
	
	}
	
	
	
	
}
