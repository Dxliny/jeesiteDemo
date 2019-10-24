/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tbclass.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.tbclass.entity.TbClass;
import com.thinkgem.jeesite.modules.tbclass.dao.TbClassDao;
import com.thinkgem.jeesite.modules.tbclass.entity.TbStudent;
import com.thinkgem.jeesite.modules.tbclass.dao.TbStudentDao;

/**
 * classinfoService
 * @author dxliny
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class TbClassService extends CrudService<TbClassDao, TbClass> {

	@Autowired
	private TbStudentDao tbStudentDao;
	
	public TbClass get(String id) {
		TbClass tbClass = super.get(id);
		tbClass.setTbStudentList(tbStudentDao.findList(new TbStudent(tbClass)));
		return tbClass;
	}
	
	public List<TbClass> findList(TbClass tbClass) {
		return super.findList(tbClass);
	}
	
	public Page<TbClass> findPage(Page<TbClass> page, TbClass tbClass) {
		return super.findPage(page, tbClass);
	}
	
	@Transactional(readOnly = false)
	public void save(TbClass tbClass) {
		super.save(tbClass);
		for (TbStudent tbStudent : tbClass.getTbStudentList()){
			if (tbStudent.getId() == null){
				continue;
			}
			if (TbStudent.DEL_FLAG_NORMAL.equals(tbStudent.getDelFlag())){
				if (StringUtils.isBlank(tbStudent.getId())){
					tbStudent.setStuClassId(tbClass);
					tbStudent.preInsert();
					tbStudentDao.insert(tbStudent);
				}else{
					tbStudent.preUpdate();
					tbStudentDao.update(tbStudent);
				}
			}else{
				tbStudentDao.delete(tbStudent);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TbClass tbClass) {
		super.delete(tbClass);
		tbStudentDao.delete(new TbStudent(tbClass));
	}
	
}