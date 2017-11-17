package com.ray.dingtalk.qy.service.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ray.dingtalk.qy.auth.AuthHelper;
import com.ray.dingtalk.qy.config.Env;
import com.ray.dingtalk.qy.model.contact.User;

/**@desc  : 成员管理测试类
 * 
 * @author: shirayner
 * @date  : 2017年9月28日 上午10:09:34
 */
public class UserServiceTest {
	
	/**
	 * @desc ：1.获取成员
	 *  
	 * @throws Exception void
	 */
	@Test
	public void testGetUser() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		String userId="manager6777";
		
		UserService us=new UserService();
		us.getUser(accessToken, userId);
		
		
	}
	
	/**
	 * @desc ：2.创建成员
	 *  
	 * @throws Exception void
	 */
	@Test
	public void testCreateUser() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		User user=new User();
		user.setName("测试2");         //王子明
		List<Long> departmentList=new ArrayList<Long>();
		departmentList.add(new Long(53191107));
		user.setDepartment(departmentList);
		user.setUserid("ceishi2");;
		user.setMobile("18771419624"); //18771419627
		
		
		UserService us=new UserService();
		String userId=us.createUser(accessToken, user);
		System.out.println("userId:"+userId);
		
	}
	
	/** 3.更新成员
	 * @desc ：参数说明（如果非必须的字段未指定，则钉钉后台不改变该字段之前设置好的值）
	 *  
	 * @throws Exception void
	 */
	@Test
	public void testUpdateUser() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		User user=new User();
		user.setName("测试专用");
		List<Long> departmentList=new ArrayList<Long>();
		departmentList.add(new Long(1));
		user.setDepartment(departmentList);
		user.setMobile("13764363757");
		user.setUserid("142322032127997575");
		user.setPosition("网管");
		
		
		UserService us=new UserService();
		us.updateUser(accessToken, user);
		
		
	}
	/** 4.删除成员
	 * @desc ：
	 *  
	 * @throws Exception void
	 */
	@Test
	public void testDeleteUser() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		String userId="142322032127997575";
		
		UserService us=new UserService();
		us.deleteUser(accessToken, userId);
		
	}
	
	
	/** 5.批量删除成员
	 * @desc ：
	 *  
	 * @throws Exception void
	 */
	@Test
	public void testBatchDeleteUser() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		//1.1为构造JSon做准备：["ceishi1","ceishi2"]
		List<String> userIdlist=new ArrayList<String>();
		userIdlist.add("ceishi1");
		userIdlist.add("ceishi2");
		
		//1.2为构造JSon做准备：{"useridlist":["ceishi1","ceishi2"]}
		Map< String , List<String> > userIdListMap=new HashMap< String, List<String> >();
		userIdListMap.put("useridlist", userIdlist);
		
		
		UserService us=new UserService();
		us.batchDeleteUser(accessToken, userIdListMap);
		
	}
	
	
	/** 6.获取部门成员(simplelist)
	 * @desc ：
	 *  
	 * @throws Exception void
	 */
	@Test
	public void testListDepartmentUser() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		String departmentId="1";
		
		UserService us=new UserService();
		us.listDepartmentUser(accessToken, departmentId);

	}
	
	/** 7.获取部门成员（详情）
	 * @desc ：
	 *  
	 * @throws Exception void
	 */
	@Test
	public void testListDepartmentUserDetail() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		String departmentId="1";
		
		UserService us=new UserService();
		us.listDepartmentUserDetail(accessToken, departmentId);	
	   
	
	}
	
	/** 8.获取管理员列表
	 * @desc ：
	 *  
	 * @throws Exception void
	 */
	@Test
	public void testListAdmin() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		
		UserService us=new UserService();
		us.listAdmin(accessToken);	

	}
	
	
	
	
}
