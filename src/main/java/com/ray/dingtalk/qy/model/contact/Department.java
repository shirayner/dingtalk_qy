package com.ray.dingtalk.qy.model.contact;

/**@desc  : 
 * 
 * @author: shirayner
 * @date  : 2017年10月18日 下午2:14:20
 */
public class Department {
	//部门id
	private long id;
	//是	部门名称。长度限制为1~64个字符。不允许包含字符‘-’‘，’以及‘,’。
	private String name;
	//是	父部门id。根部门id为1
	private String parentid;
	//否	在父部门中的次序值。order值小的排序靠前
	private String order;
	//否	是否创建一个关联此部门的企业群，默认为false
	private Boolean createDeptGroup;
	
	
	//否	是否隐藏部门, true表示隐藏, false表示显示
	private Boolean deptHiding;
	//否	可以查看指定隐藏部门的其他部门列表，如果部门隐藏，则此值生效，
	//取值为其他的部门id组成的的字符串，使用 | 符号进行分割。总数不能超过200。
	private String deptPerimits;
	//否	可以查看指定隐藏部门的其他人员列表，如果部门隐藏，则此值生效，取值为其他的人员userid组成的的字符串，
	//使用| 符号进行分割。总数不能超过200。
	private String userPerimits;
	//否	是否本部门的员工仅可见员工自己, 为true时，本部门员工默认只能看到员工自己
	private Boolean outerDept;
	//否	本部门的员工仅可见员工自己为true时，可以配置额外可见部门，值为部门id组成的的字符串，
	//使用|符号进行分割。总数不能超过200。
	private String outerPermitDepts;
	//否	本部门的员工仅可见员工自己为true时，可以配置额外可见人员，值为userid组成的的字符串，
	//使用|符号进行分割。总数不能超过200。
	private String outerPermitUsers;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Boolean getCreateDeptGroup() {
		return createDeptGroup;
	}
	public void setCreateDeptGroup(Boolean createDeptGroup) {
		this.createDeptGroup = createDeptGroup;
	}
	public Boolean getDeptHiding() {
		return deptHiding;
	}
	public void setDeptHiding(Boolean deptHiding) {
		this.deptHiding = deptHiding;
	}
	public String getDeptPerimits() {
		return deptPerimits;
	}
	public void setDeptPerimits(String deptPerimits) {
		this.deptPerimits = deptPerimits;
	}
	public String getUserPerimits() {
		return userPerimits;
	}
	public void setUserPerimits(String userPerimits) {
		this.userPerimits = userPerimits;
	}
	public Boolean getOuterDept() {
		return outerDept;
	}
	public void setOuterDept(Boolean outerDept) {
		this.outerDept = outerDept;
	}
	public String getOuterPermitDepts() {
		return outerPermitDepts;
	}
	public void setOuterPermitDepts(String outerPermitDepts) {
		this.outerPermitDepts = outerPermitDepts;
	}
	public String getOuterPermitUsers() {
		return outerPermitUsers;
	}
	public void setOuterPermitUsers(String outerPermitUsers) {
		this.outerPermitUsers = outerPermitUsers;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}



}
