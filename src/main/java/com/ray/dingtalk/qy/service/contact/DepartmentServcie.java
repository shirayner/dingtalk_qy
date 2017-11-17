package com.ray.dingtalk.qy.service.contact;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ray.dingtalk.qy.model.contact.Department;
import com.ray.dingtalk.qy.util.HttpHelper;

/**@desc  : 部门管理业务类
 * 
 * @author: shirayner
 * @date  : 2017年10月18日 下午12:39:21
 */
public class DepartmentServcie {
	//1.获取部门列表
	private static final String LIST_DEPARTMENT_URL="https://oapi.dingtalk.com/department/list?access_token=ACCESS_TOKEN&id=ID";
	//2.获取部门详情
	private static final String GET_DEPARTMENTDETAIL_URL="https://oapi.dingtalk.com/department/get?access_token=ACCESS_TOKEN&id=ID";
	//3.创建部门
	private static final String CREATE_DEPARTMENT_URL="https://oapi.dingtalk.com/department/create?access_token=ACCESS_TOKEN";
	//4.更新部门
	private static final String UPDATE_DEPARTMENT_URL="https://oapi.dingtalk.com/department/update?access_token=ACCESS_TOKEN";
	//5.删除部门
	private static final String DELETE_DEPARTMENT_URL="https://oapi.dingtalk.com/department/delete?access_token=ACCESS_TOKEN&id=ID";
	//6.查询部门的所有上级父部门路径
	private static final String LIST_PARENTDEPTS_BYDEPT_URL="https://oapi.dingtalk.com/department/list_parent_depts_by_dept?access_token=ACCESS_TOKEN&id=ID";
	//7.查询指定用户的所有上级父部门路径
	private static final String LIST_PARENTDEPTS_BYUSER_URL="https://oapi.dingtalk.com/department/list_parent_depts?access_token=ACCESS_TOKEN&userId=USERID";

	
	

	/** 1.获取部门列表
	 * @desc ：获取指定部门下的部门列表
	 *  
	 * @param accessToken
	 * @param departementId 父部门id
	 * @return
	 * @throws Exception JSONObject
	 */
	public JSONObject listDepartment(String accessToken,String departmentId) throws Exception {
		//1.准备请求url
		String url=LIST_DEPARTMENT_URL.replace("ACCESS_TOKEN", accessToken).replace("ID", departmentId);

		//2.调用接口，发送请求
		JSONObject jsonObject=HttpHelper.doGet(url);
		System.out.println(jsonObject);

		//3.解析请求结果
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

	/** 2.获取部门详情
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param departementId 部门id
	 * @return
	 * @throws Exception JSONObject
	 */
	public JSONObject getDepartmentDetail(String accessToken,String departmentId) throws Exception {
		//1.准备请求url
		String url=GET_DEPARTMENTDETAIL_URL.replace("ACCESS_TOKEN", accessToken).replace("ID", departmentId);

		//2.调用接口，发送请求
		JSONObject jsonObject=HttpHelper.doGet(url);
		System.out.println(jsonObject);

		//3.解析请求结果
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


	/** 3.创建部门
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param department
	 * @return
	 * @throws Exception String
	 */
	public String createDepartment(String accessToken,Department department) throws Exception {
		//1.准备POST请求参数
		Object data=JSON.toJSON(department);
		System.out.println(data);

		//2.获取请求url
		String url=CREATE_DEPARTMENT_URL.replace("ACCESS_TOKEN", accessToken);

		//3.发起POST请求，获取返回结果
		JSONObject jsonObject=HttpHelper.doPost(url, data);
		System.out.println("jsonObject:"+jsonObject.toString());

		//4.解析结果，获取UserId
		String departmentId="";
		if (null != jsonObject) {  
			departmentId=jsonObject.getString("id");
			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

		return departmentId;
	}


	/** 4.更新部门
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param department    其中id是必须的
	 * @return
	 * @throws Exception String
	 */
	public void updateDepartment(String accessToken,Department department) throws Exception {
		//1.准备POST请求参数
		Object data=JSON.toJSON(department);
		System.out.println(data);

		//2.获取请求url
		String url=UPDATE_DEPARTMENT_URL.replace("ACCESS_TOKEN", accessToken);

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

	/**5.删除部门
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param departmentId
	 * @throws Exception void
	 */
      public void deleteDepartment(String accessToken,String departmentId) throws Exception {
    	//1.准备请求url
  		String url=DELETE_DEPARTMENT_URL.replace("ACCESS_TOKEN", accessToken).replace("ID", departmentId);

  		//2.调用接口，发送请求
  		JSONObject jsonObject=HttpHelper.doGet(url);
  		System.out.println(jsonObject);

  		//3.解析请求结果
  		if (null != jsonObject) {  
  		
  			//5.错误消息处理
  			if (0 != jsonObject.getInteger("errcode")) {  
  				int errCode = jsonObject.getInteger("errcode");
  				String errMsg = jsonObject.getString("errmsg");
  				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
  			}  
  		}   

  	
      }

      /** 6.查询部门的所有上级父部门路径
  	 * @desc ：
  	 *  返回的结果按顺序依次为其所有父部门的ID，直到根部门
  	 * @param accessToken
  	 * @param departementId 部门id
  	 * @return
  	 * @throws Exception JSONObject
  	 */
  	public JSONObject listParentDepartementsByDept(String accessToken,String departmentId) throws Exception {
  		//1.准备请求url
  		String url=LIST_PARENTDEPTS_BYDEPT_URL.replace("ACCESS_TOKEN", accessToken).replace("ID", departmentId);

  		//2.调用接口，发送请求
  		JSONObject jsonObject=HttpHelper.doGet(url);
  		System.out.println(jsonObject);

  		//3.解析请求结果
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
  	
  	
  	/** 7.查询指定用户的所有上级父部门路径
  	 * @desc ：
  	 *  返回的结果按顺序依次为其所有父部门的ID，直到根部门
  	 * @param accessToken
  	 * @param departementId 部门id
  	 * @return
  	 * @throws Exception JSONObject
  	 */
  	public JSONObject listParentDepartementsByUser(String accessToken,String userID) throws Exception {
  		//1.准备请求url
  		String url=LIST_PARENTDEPTS_BYUSER_URL.replace("ACCESS_TOKEN", accessToken).replace("USERID", userID);
  		
  		//2.调用接口，发送请求
  		JSONObject jsonObject=HttpHelper.doGet(url);
  		System.out.println(jsonObject);
  		
  		//3.解析请求结果
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
