package com.ray.dingtalk.qy.service.contact;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.ray.dingtalk.qy.auth.AuthHelper;
import com.ray.dingtalk.qy.config.Env;
import com.ray.dingtalk.qy.model.contact.Department;

/**@desc  : 部门管理测试类
 * 
 * @author: shirayner
 * @date  : 2017年10月18日 下午1:31:32
 */
public class DepartmentServcieTest {
	private static final Logger log = LogManager.getLogger(DepartmentServcieTest.class);
	

	/** 1.获取部门列表
	 * @throws Exception 
	 * @desc ：
	 *   void
	 */
	@Test
	public void testListDepartement() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		String departmentId="1";
		DepartmentServcie departmentServcie= new DepartmentServcie();      
		JSONObject jsonObject=departmentServcie.listDepartment(accessToken, departmentId);
		log.info(jsonObject);
		
	}
	
	/** 2.获取部门详情
	 * @throws Exception 
	 * @desc ：
	 *   void
	 */
	@Test
	public void testGetDepartementDetail() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		String departmentId="1";
		DepartmentServcie departmentServcie= new DepartmentServcie();      
		JSONObject jsonObject=departmentServcie.getDepartmentDetail(accessToken, departmentId);
		log.info(jsonObject);
		
	}

	/** 3.创建部门
	 * @throws Exception 
	 * @desc ：
	 *   void
	 */
	@Test
	public void testCreateDepartment() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		Department department =new Department();
		department.setName("HEC北京分公司");
		department.setParentid("1");
		
		
		DepartmentServcie departmentServcie= new DepartmentServcie();      
		String departmentId=departmentServcie.createDepartment(accessToken, department);
		log.info(departmentId);
		
	}
	
	
	/** 4.更新部门
	 * 
	 * @throws Exception 
	 * @desc ：
	 *   void
	 */
	@Test
	public void testUpdateDepartment() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		Department department =new Department();
		department.setName("HEC大汉分公司");
		department.setId(53125124);
		
		DepartmentServcie departmentServcie= new DepartmentServcie();      
		departmentServcie.updateDepartment(accessToken, department);
		
		
	}

	
	/** 5.删除部门
	 * @throws Exception 
	 * @desc ：
	 *   void
	 */
	@Test
	public void testDeleteDepartement() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		String departmentId="53181144";
		
		DepartmentServcie departmentServcie= new DepartmentServcie();      
		departmentServcie.deleteDepartment(accessToken, departmentId);
		
	}
	
	/** 6.查询部门的所有上级父部门路径
	 * @throws Exception 
	 * @desc ：
	 *   void
	 */
	@Test
	public void testListParentDepartementsByDept() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		String departmentId="53191107";
		
		DepartmentServcie departmentServcie= new DepartmentServcie();      
		JSONObject jsonObject=departmentServcie.listParentDepartementsByDept(accessToken, departmentId);
		log.info(jsonObject.get("parentIds"));
		
	}
	
	
	/** 7.查询指定用户的所有上级父部门路径
	 * @throws Exception 
	 * @desc ：
	 *   void
	 */
	@Test
	public void testListParentDepartementsByUser() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		String userID="ceishi2";
		
		DepartmentServcie departmentServcie= new DepartmentServcie();      
		JSONObject jsonObject=departmentServcie.listParentDepartementsByUser(accessToken, userID);
		log.info(jsonObject.get("department"));
		
	}

}
