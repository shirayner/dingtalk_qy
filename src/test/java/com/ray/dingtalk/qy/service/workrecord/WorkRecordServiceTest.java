package com.ray.dingtalk.qy.service.workrecord;

import com.ray.dingtalk.qy.auth.AuthHelper;
import com.ray.dingtalk.qy.config.Env;
import com.ray.dingtalk.qy.model.workrecord.WorkRecord;
import com.ray.dingtalk.qy.model.workrecord.WorkRecord.FormItemVo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class WorkRecordServiceTest {
	private String accessToken ;
	
	@Before
	public void init() throws Exception {
		accessToken = AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
	}
	
	

	@Test
	public void testAddWorkRecord() {
		WorkRecord workRecord =new WorkRecord();
		workRecord.setUserid("manager6777");
		workRecord.setCreate_time(System.currentTimeMillis() / 1000);
		workRecord.setTitle("代办事项");
		workRecord.setUrl("https://www.cnblogs.com/shirui/");
		workRecord.setLtitle("表单标题");
		workRecord.setLcontent("表单内容");
		
		 List<FormItemVo> formItemList =new ArrayList<FormItemVo>();
		 FormItemVo obj3 = new FormItemVo();
		 obj3.setTitle("标题");
		 obj3.setContent("内容");
		 formItemList.add(obj3);
		 workRecord.setFormItemList(formItemList);
		
		String result = WorkRecordService.addWorkRecord(accessToken, workRecord);
		System.out.println("result:"+result);
	}

	@Test
	public void testAddWorkRecord2() {

		String userId ="manager6777";
		String title = "费控审批";
		String url = "https://www.cnblogs.com/shirui/";
		String Ltitle = "";
		String Lcontent ="您有一条报销单带审批，请前往审批页面进行审批！啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊。";


		String record_id = WorkRecordService.addWorkRecord( accessToken , userId, title, url, Ltitle, Lcontent );

		System.out.println("result:"+record_id);
	}


	@Test
	public void testUpdateWorkRecord() {

		String userId ="manager6777";
		String recordId ="record76f239ce37b47b9ed84f1c4472ec7689";

		boolean result = WorkRecordService.updateWorkRecord( accessToken, userId,  recordId);

		System.out.println("result:"+result);
	}



}
