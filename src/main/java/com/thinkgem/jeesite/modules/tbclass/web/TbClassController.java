/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tbclass.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.tbclass.entity.TbClass;
import com.thinkgem.jeesite.modules.tbclass.service.TbClassService;

/**
 * classinfoController
 * @author dxliny
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/tbclass/tbClass")
public class TbClassController extends BaseController {

	@Autowired
	private TbClassService tbClassService;
	
	@ModelAttribute
	public TbClass get(@RequestParam(required=false) String id) {
		TbClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tbClassService.get(id);
		}
		if (entity == null){
			entity = new TbClass();
		}
		return entity;
	}
	
	@RequiresPermissions("tbclass:tbClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(TbClass tbClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TbClass> page = tbClassService.findPage(new Page<TbClass>(request, response), tbClass); 
		model.addAttribute("page", page);
		return "modules/tbclass/tbClassList";
	}

	@RequiresPermissions("tbclass:tbClass:view")
	@RequestMapping(value = "form")
	public String form(TbClass tbClass, Model model) {
		model.addAttribute("tbClass", tbClass);
		return "modules/tbclass/tbClassForm";
	}

	@RequiresPermissions("tbclass:tbClass:edit")
	@RequestMapping(value = "save")
	public String save(TbClass tbClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tbClass)){
			return form(tbClass, model);
		}
		tbClassService.save(tbClass);
		addMessage(redirectAttributes, "保存classinfo成功");
		return "redirect:"+Global.getAdminPath()+"/tbclass/tbClass/?repage";
	}
	
	@RequiresPermissions("tbclass:tbClass:edit")
	@RequestMapping(value = "delete")
	public String delete(TbClass tbClass, RedirectAttributes redirectAttributes) {
		tbClassService.delete(tbClass);
		addMessage(redirectAttributes, "删除classinfo成功");
		return "redirect:"+Global.getAdminPath()+"/tbclass/tbClass/?repage";
	}

}