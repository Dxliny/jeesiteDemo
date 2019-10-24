/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tbclass.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * classinfoEntity
 * @author dxliny
 * @version 2019-10-23
 */
public class TbClass extends DataEntity<TbClass> {
	
	private static final long serialVersionUID = 1L;
	private String className;		// c_name
	private String classNum;		// c_num
	private String classDelFlag;		// c_del_flag
	private List<TbStudent> tbStudentList = Lists.newArrayList();		// 子表列表
	
	public TbClass() {
		super();
	}

	public TbClass(String id){
		super(id);
	}

	@Length(min=0, max=5, message="c_name长度必须介于 0 和 5 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Length(min=0, max=11, message="c_num长度必须介于 0 和 11 之间")
	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
	@Length(min=0, max=1, message="c_del_flag长度必须介于 0 和 1 之间")
	public String getClassDelFlag() {
		return classDelFlag;
	}

	public void setClassDelFlag(String classDelFlag) {
		this.classDelFlag = classDelFlag;
	}
	
	public List<TbStudent> getTbStudentList() {
		return tbStudentList;
	}

	public void setTbStudentList(List<TbStudent> tbStudentList) {
		this.tbStudentList = tbStudentList;
	}
}