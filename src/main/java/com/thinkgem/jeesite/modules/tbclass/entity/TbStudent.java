/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tbclass.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * classinfoEntity
 * @author dxliny
 * @version 2019-10-23
 */
public class TbStudent extends DataEntity<TbStudent> {
	
	private static final long serialVersionUID = 1L;
	private TbClass stuClassId;		// s_class_id 父类
	private String stuName;		// s_name
	private String stuAge;		// s_age
	private String stuSex;		// s_sex
	
	public TbStudent() {
		super();
	}

	public TbStudent(String id){
		super(id);
	}

	public TbStudent(TbClass stuClassId){
		this.stuClassId = stuClassId;
	}

	@Length(min=1, max=5, message="s_class_id长度必须介于 1 和 5 之间")
	public TbClass getStuClassId() {
		return stuClassId;
	}

	public void setStuClassId(TbClass stuClassId) {
		this.stuClassId = stuClassId;
	}
	
	@Length(min=0, max=5, message="s_name长度必须介于 0 和 5 之间")
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	@Length(min=0, max=11, message="s_age长度必须介于 0 和 11 之间")
	public String getStuAge() {
		return stuAge;
	}

	public void setStuAge(String stuAge) {
		this.stuAge = stuAge;
	}
	
	@Length(min=0, max=1, message="s_sex长度必须介于 0 和 1 之间")
	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	
}