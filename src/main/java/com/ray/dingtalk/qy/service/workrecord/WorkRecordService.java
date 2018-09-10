package com.ray.dingtalk.qy.service.workrecord;

import com.alibaba.fastjson.JSONObject;
import com.ray.dingtalk.qy.model.workrecord.WorkRecord;
import com.ray.dingtalk.qy.model.workrecord.WorkRecord.FormItemVo;
import com.ray.dingtalk.qy.util.HttpHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 代办事项
 *   1.发送代办，返回record_id。 然后将 record_id 与单据id,user_id进行关联
 *   2.更新代办。审批单据，然后根据单据id,找出record_id,user_id,然后更新代办。
 *
 * 注：代办内容重复次数过多，会导致代办发布出去。
 *
 */
public class WorkRecordService {
	public static final String  DEFAULT_TASKNAME="hecGroup";

	/** 1.发送代办 **/
	public static final String WORK_RECORD_ADD_URL = "https://oapi.dingtalk.com/topapi/workrecord/add?access_token=";

	/** 2.更新待办 **/
	public static final String WORK_RECORD_UPDATE_URL = "https://oapi.dingtalk.com/topapi/workrecord/update?access_token=";


	/**
	 * 1.发送代办消息
	 * @param accessToken
	 * @param workRecord
	 * @return record_id
	 */
	public static String addWorkRecord(String accessToken ,WorkRecord workRecord)  {
		//1.获取请求url
		String url=WORK_RECORD_ADD_URL + accessToken;

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=null;
		try {
			jsonObject = HttpHelper.doPost(url, workRecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//3.解析结果，获取accessToken
		String record_id="";  
		if (null != jsonObject) {  
			//4.错误消息处理
			if (jsonObject.getInteger("errcode")!=null && 0 != jsonObject.getInteger("errcode")) {  

				System.out.println("===========================================");
				System.out.println("jsapiTicket请求失败,错误信息如下：");
				System.out.println(jsonObject.toJSONString());

				record_id = jsonObject.toJSONString();

				//5.成功获取jsapiTicket
			}else {
				record_id=jsonObject.getString("record_id");
			}  

		}


		return record_id;
	}

	/** 1.2发送代办消息
	 *
	 * @param accessToken
	 * @param userId
	 * @param title
	 * @param url
	 * @param Ltitle
	 * @param Lcontent
	 * @return
	 */
	public static String addWorkRecord(String accessToken ,String userId,String title,String url,String Ltitle,String Lcontent ){
		WorkRecord workRecord =new WorkRecord();
		workRecord.setUserid(userId);
		workRecord.setCreate_time(new Date().getTime());
		workRecord.setTitle(title);
		workRecord.setUrl(url);
		workRecord.setLtitle(Ltitle);

		List<FormItemVo> formItemList =new ArrayList<FormItemVo>();
		FormItemVo obj3 = new FormItemVo();
		obj3.setTitle(Ltitle);
		//设置链接消息的内容，由于钉钉相同消息每天只能发送一次，故给消息加上时间前缀
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = simpleDateFormat.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		Lcontent =Lcontent +"  "+date;
		workRecord.setLcontent(Lcontent);
		obj3.setContent(Lcontent);
		formItemList.add(obj3);
		workRecord.setFormItemList(formItemList);

		return addWorkRecord(accessToken, workRecord);
	}


	/**
	 *2.更新代办
	 * @param accessToken
	 * @param userId
	 * @param recordId
	 * @return
	 */
	public static boolean updateWorkRecord(String accessToken,String userId, String recordId){
		//1.获取请求url
		String url=WORK_RECORD_UPDATE_URL + accessToken;

		//2.请求json
		JSONObject postData=new JSONObject();
		postData.put("userid", userId);
		postData.put("record_id", recordId);


		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=null;
		try {
			jsonObject = HttpHelper.doPost(url, postData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//3.解析结果，获取accessToken
		boolean result=false;
		if (null != jsonObject) {
			//4.错误消息处理
			if (jsonObject.getInteger("errcode")!=null && 0 != jsonObject.getInteger("errcode")) {

				System.out.println("===========================================");
				System.out.println("jsapiTicket请求失败,错误信息如下：");
				System.out.println(jsonObject.toJSONString());

				//5.成功获取jsapiTicket
			}else {
				result=jsonObject.getBoolean("result");
			}

		}


		return result;
	}


}
