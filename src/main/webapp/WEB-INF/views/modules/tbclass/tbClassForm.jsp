<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>classinfo管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tbclass/tbClass/">classinfo列表</a></li>
		<li class="active"><a href="${ctx}/tbclass/tbClass/form?id=${tbClass.id}">classinfo<shiro:hasPermission name="tbclass:tbClass:edit">${not empty tbClass.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="tbclass:tbClass:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbClass" action="${ctx}/tbclass/tbClass/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">c_name：</label>
			<div class="controls">
				<form:input path="className" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">c_num：</label>
			<div class="controls">
				<form:input path="classNum" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">c_del_flag：</label>
			<div class="controls">
				<form:input path="classDelFlag" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">tb_student：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>s_name</th>
								<th>s_age</th>
								<th>s_sex</th>
								<shiro:hasPermission name="tbclass:tbClass:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="tbStudentList">
						</tbody>
						<shiro:hasPermission name="tbclass:tbClass:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#tbStudentList', tbStudentRowIdx, tbStudentTpl);tbStudentRowIdx = tbStudentRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="tbStudentTpl">//<!--
						<tr id="tbStudentList{{idx}}">
							<td class="hide">
								<input id="tbStudentList{{idx}}_id" name="tbStudentList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="tbStudentList{{idx}}_delFlag" name="tbStudentList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="tbStudentList{{idx}}_stuName" name="tbStudentList[{{idx}}].stuName" type="text" value="{{row.stuName}}" maxlength="5" class="input-small "/>
							</td>
							<td>
								<input id="tbStudentList{{idx}}_stuAge" name="tbStudentList[{{idx}}].stuAge" type="text" value="{{row.stuAge}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<input id="tbStudentList{{idx}}_stuSex" name="tbStudentList[{{idx}}].stuSex" type="text" value="{{row.stuSex}}" maxlength="1" class="input-small "/>
							</td>
							<shiro:hasPermission name="tbclass:tbClass:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#tbStudentList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var tbStudentRowIdx = 0, tbStudentTpl = $("#tbStudentTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(tbClass.tbStudentList)};
							for (var i=0; i<data.length; i++){
								addRow('#tbStudentList', tbStudentRowIdx, tbStudentTpl, data[i]);
								tbStudentRowIdx = tbStudentRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="tbclass:tbClass:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>