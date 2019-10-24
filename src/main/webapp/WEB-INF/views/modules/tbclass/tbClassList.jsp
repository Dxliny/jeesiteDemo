<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>classinfo管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tbclass/tbClass/">classinfo列表</a></li>
		<shiro:hasPermission name="tbclass:tbClass:edit"><li><a href="${ctx}/tbclass/tbClass/form">classinfo添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tbClass" action="${ctx}/tbclass/tbClass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>c_name：</label>
				<form:input path="className" htmlEscape="false" maxlength="5" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>c_name</th>
				<th>c_num</th>
				<th>c_del_flag</th>
				<shiro:hasPermission name="tbclass:tbClass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbClass">
			<tr>
				<td><a href="${ctx}/tbclass/tbClass/form?id=${tbClass.id}">
					${tbClass.id}
				</a></td>
				<td>
					${tbClass.className}
				</td>
				<td>
					${tbClass.classNum}
				</td>
				<td>
					${tbClass.classDelFlag}
				</td>
				<shiro:hasPermission name="tbclass:tbClass:edit"><td>
    				<a href="${ctx}/tbclass/tbClass/form?id=${tbClass.id}">修改</a>
					<a href="${ctx}/tbclass/tbClass/delete?id=${tbClass.id}" onclick="return confirmx('确认要删除该classinfo吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>