package com.ray.dingtalk.qy.model.message.asyncsend;

/**@desc  : 
 * 
 * 参考文档地址：https://open-doc.dingtalk.com/docs/
 * doc.htm?spm=a219a.7629140.0.0.hZSBgV&treeId=385&
 * articleId=28915&docType=2
 * 
 * @author: shirayner
 * @date  : 2017年10月13日 下午1:53:40
 */
public class AsyncMessage {
	//1.公共参数
	//必须 ,API接口名称
    private String method;
    //必须 ,钉钉提供的授权Token
    private String session;
    //必须，时间戳，格式为yyyy-MM-dd HH:mm:ss，时区为GMT+8，例如：2015-01-01 12:00:00。
    //淘宝API服务端允许客户端请求最大时间误差为10分钟。
    private String timestamp;
    //必须，API协议版本，可选值：2.0。
    private String v;
    private String format;
    
    
    //2.请求参数
    
    private String msgtype;
    //必须，微应用的id
    private Number agent_id;
    //必须，与msgtype对应的消息体,参见文档
    private String msgcontent;
    
    //否
    private String []	 userid_list;
    //否
    private Boolean to_all_user;
    
    
    
    
    
    
    
    
    
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public Number getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(Number agent_id) {
		this.agent_id = agent_id;
	}
	public String getMsgcontent() {
		return msgcontent;
	}
	public String[] getUserid_list() {
		return userid_list;
	}
	public void setUserid_list(String[] userid_list) {
		this.userid_list = userid_list;
	}
	public Boolean getTo_all_user() {
		return to_all_user;
	}
	public void setTo_all_user(Boolean to_all_user) {
		this.to_all_user = to_all_user;
	}
	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}


	
}
