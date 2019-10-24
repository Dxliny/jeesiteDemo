/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tbclass.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tbclass.entity.TbClass;

/**
 * classinfoDAO接口
 * @author dxliny
 * @version 2019-10-23
 */
@MyBatisDao
public interface TbClassDao extends CrudDao<TbClass> {
	
}