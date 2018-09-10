package com.ray.dingtalk.qy.model.workrecord;

import java.io.Serializable;
import java.util.List;

public class WorkRecord {
	private String userid;
	private Long create_time;
	private String title;
	private String url;
	private List<FormItemVo> formItemList;
	private String Ltitle;
	private String Lcontent;

	public static class FormItemVo implements Serializable {
		private static final long serialVersionUID = 8276475759545143625L;

		private String content;


		private String title;

		public String getContent() {
			return this.content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getTitle() {
			return this.title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<FormItemVo> getFormItemList() {
		return formItemList;
	}

	public void setFormItemList(List<FormItemVo> formItemList) {
		this.formItemList = formItemList;
	}

	public String getLtitle() {
		return Ltitle;
	}

	public void setLtitle(String ltitle) {
		Ltitle = ltitle;
	}

	public String getLcontent() {
		return Lcontent;
	}

	public void setLcontent(String lcontent) {
		Lcontent = lcontent;
	}





}
