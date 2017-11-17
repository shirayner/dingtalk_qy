package com.ray.dingtalk.qy.service.contact;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ray.dingtalk.qy.model.contact.User;
import com.ray.dingtalk.qy.util.HttpHelper;

/**@desc  : 成员管理业务类
 * 
 * @author: shirayner
 * @date  : 2017年9月28日 上午9:53:51
 */
public class UserService {
	//1.获取成员详情 接口
	private static final String GET_USER_URL="https://oapi.dingtalk.com/user/get?access_token=ACCESS_TOKEN&userid=ID";
	//2.创建成员 接口
	private static final String CREATE_USER_URL="https://oapi.dingtalk.com/user/create?access_token=ACCESS_TOKEN";
	//3.更新成员
	private static final String UPDATE_USER_URL="https://oapi.dingtalk.com/user/update?access_token=ACCESS_TOKEN";
	//4.删除成员
	private static final String DELETE_USER_URL="https://oapi.dingtalk.com/user/delete?access_token=ACCESS_TOKEN&userid=ID";
	//5.批量删除成员
	private static final String BATCHDELETE_USER_URL="https://oapi.dingtalk.com/user/batchdelete?access_token=ACCESS_TOKEN";
	//6.获取部门成员(simplelist)
	private static final String LIST_DEPARTMENTUSER_URL="https://oapi.dingtalk.com/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENTID";
	//7.获取部门成员（详情）
	private static final String LIST_DEPARTMENTUSERDETAIL_URL="https://oapi.dingtalk.com/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENTID";
	//8.获取管理员列表
	private static final String LIST_ADMIN_URL="https://oapi.dingtalk.com/user/get_admin?access_token=ACCESS_TOKEN";
	//9.根据unionid获取成员的userid
	private static final String GET_USERID_BYUNIONID_URL="https://oapi.dingtalk.com/user/getUseridByUnionid?access_token=ACCESS_TOKEN&unionid=UNIONID";
	//10.通过CODE换取用户身份
	private static final String GET_USERINFO_BYCODE_URL="https://oapi.dingtalk.com/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";

	/** 1.根据userid获取成员详情
	 * @desc ：获取成员详情
	 *   参考文档： https://open-doc.dingtalk.com/docs/doc.htm?spm=0.0.0.0.jjSfQQ&treeId=371&articleId=106816&docType=1#s0
	 * @param accessToken
	 * @param userId 
     * return JSONObject  返回用户详情
	 * @throws Exception 
	 */
	public JSONObject getUser(String accessToken, String userId) throws Exception {

		//1.获取请求url
		String url=GET_USER_URL.replace("ACCESS_TOKEN", accessToken).replace("ID", userId);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());
		//3.解析结果，获取User
		if (null != jsonObject) {  
			
			//4.请求成功,则返回jsonObject
  			if (0==jsonObject.getInteger("errcode")) {
  				return jsonObject;
  			}
  			
  			//5.错误消息处理
  			if (0 != jsonObject.getInteger("errcode")) {  
  				int errCode = jsonObject.getInteger("errcode");
  				String errMsg = jsonObject.getString("errmsg");
  				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
  			}  
			
		}   

		return null;
	}



	/**2.创建成员
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param user
	 * @return  返回用户id
	 * @throws Exception String
	 */
	public String createUser(String accessToken,User user) throws Exception {
		//1.准备POST请求参数
		Object data=JSON.toJSON(user);
		System.out.println(data);

		//2.获取请求url
		String url=CREATE_USER_URL.replace("ACCESS_TOKEN", accessToken);

		//3.发起POST请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doPost(url, data);
		System.out.println("jsonObject:"+jsonObject.toString());

		//4.解析结果，获取UserId
		String userId="";
		if (null != jsonObject) {  
			userId=jsonObject.getString("userid");
			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

		return userId;
	}


	/**3.更新成员
	 * @desc ：
	 *  参数说明（如果非必须的字段未指定，则钉钉后台不改变该字段之前设置好的值）
	 * @param accessToken
	 * @param user
	 * @return
	 * @throws Exception String
	 */
	public void updateUser(String accessToken,User user) throws Exception {
		//1.准备POST请求参数
		Object data=JSON.toJSON(user);
		System.out.println(data);

		//2.获取请求url
		String url=UPDATE_USER_URL.replace("ACCESS_TOKEN", accessToken);

		//3.发起POST请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doPost(url, data);
		System.out.println("jsonObject:"+jsonObject.toString());

		//4.解析结果
		if (null != jsonObject) {  

			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

	}

	/** 4.删除成员
	 *
	 * @param accessToken
	 * @param userId void
	 * @throws Exception 
	 */
	public JSONObject deleteUser(String accessToken, String userId) throws Exception {

		//1.获取请求url
		String url=DELETE_USER_URL.replace("ACCESS_TOKEN", accessToken).replace("ID", userId);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());
		//3.解析结果，获取User
		if (null != jsonObject) {  
			//4.请求成功,则返回jsonObject
			if (0==jsonObject.getInteger("errcode")) {
				return jsonObject;
			}
			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

		return null;
	}


	/** 5.批量删除成员
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param user
	 * @throws Exception void
	 */
	public void batchDeleteUser(String accessToken,Map< String , List<String> > userIdlistMap) throws Exception {
		//1.准备POST请求参数
		Object data=JSON.toJSON(userIdlistMap);
		System.out.println(data);

		//2.获取请求url
		String url=BATCHDELETE_USER_URL.replace("ACCESS_TOKEN", accessToken);

		//3.发起POST请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doPost(url, data);
		System.out.println("jsonObject:"+jsonObject.toString());

		//4.解析结果
		if (null != jsonObject) {  

			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

	}



	/** 6.获取部门成员(simplelist)
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param departmentId
	 * return userList 用户列表
	 * @throws Exception void
	 */
	public JSONObject listDepartmentUser(String accessToken, String departmentId) throws Exception {

		//1.获取请求url
		String url=LIST_DEPARTMENTUSER_URL.replace("ACCESS_TOKEN", accessToken).replace("DEPARTMENTID", departmentId);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());


		//3.解析结果，获取User
		if (null != jsonObject) {  
			//4.请求成功,则返回jsonObject
  			if (0==jsonObject.getInteger("errcode")) {
  				return jsonObject;
  			}
  			
			
			
			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   
		
		return null;
	}



	/** 7.获取部门成员（详情）
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param departmentId
	 * return userList 用户列表
	 * @throws Exception void
	 */
	public JSONObject listDepartmentUserDetail(String accessToken, String departmentId) throws Exception {

		//1.获取请求url
		String url=LIST_DEPARTMENTUSERDETAIL_URL.replace("ACCESS_TOKEN", accessToken).replace("DEPARTMENTID", departmentId);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());
		
	
		//3.解析结果，获取User
		if (null != jsonObject) {  
			//4.请求成功,则返回jsonObject
  			if (0==jsonObject.getInteger("errcode")) {
  				return jsonObject;
  			}
			
			//4.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}  
		
		return null;
	}

	/**8.获取管理员列表
	 * @desc ：
	 *  
	 * @param accessToken
	 * @return adminList  管理员列表
	 * @throws Exception JSONObject
	 */
	public JSONObject listAdmin(String accessToken) throws Exception {

		//1.获取请求url
		String url=LIST_ADMIN_URL.replace("ACCESS_TOKEN", accessToken);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());


		//3.解析结果，获取adminList
		if (null != jsonObject) {  
			//4.请求成功,则返回jsonObject
  			if (0==jsonObject.getInteger("errcode")) {
  				return jsonObject;
  			}
  			
			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

		return null;
	}

	/** 9.根据unionid获取成员的userid
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param unionId
	 * @return  用户id
	 * @throws Exception JSONObject
	 */
	public String getUserIdByUnionId(String accessToken, String unionId) throws Exception {

		//1.获取请求url
		String url=GET_USERID_BYUNIONID_URL.replace("ACCESS_TOKEN", accessToken).replace("UNIONID", unionId);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());
		//3.解析结果，获取User
		if (null != jsonObject) {  
			//4.请求成功,则返回jsonObject
			if (0==jsonObject.getInteger("errcode")) {
				return jsonObject.getString("userid");
			}
			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

		return null;
	}


	/** 10.通过CODE换取用户身份
	 * @desc ：钉钉服务器返回的用户信息为：
	 * userid	员工在企业内的UserID
	 * deviceId	手机设备号,由钉钉在安装时随机产生
	 * is_sys	是否是管理员
	 * sys_level	级别，0：非管理员 1：超级管理员（主管理员） 2：普通管理员（子管理员） 100：老板
	 *  
	 * @param accessToken
	 * @param code
	 * @throws Exception void
	 */
	public JSONObject getUserInfo(String accessToken,String code) throws Exception {

		//1.获取请求url
		String url=GET_USERINFO_BYCODE_URL.replace("ACCESS_TOKEN", accessToken).replace("CODE", code);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());

		//3.解析结果，获取User
		if (null != jsonObject) {  
			//4.请求成功,则返回jsonObject
			if (0==jsonObject.getInteger("errcode")) {
				return jsonObject;
			}
			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

		return null;
	}



}
