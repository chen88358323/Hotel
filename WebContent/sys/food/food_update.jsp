﻿<%@page import="com.ming.entity.FoodType"%>
<%@page import="java.util.List"%>
<%@page import="com.ming.factory.BeanFactory"%>
<%@page import="com.ming.service.IFoodTypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- 包含公共的JSP代码片段 -->

<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
<link
	href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/sys/style/css/index_1.css" />
</head>
<body>

	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">


				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif" />
				更新新菜品



			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<!-- 表单内容 -->
		<form action="${pageContext.request.contextPath}/FoodServlet?method=update" method="post" enctype="multipart/form-data">
			<!-- 本段标题（分段标题） -->
			<div class="ItemBlock_Title">
				<img width="4" height="7" border="0"
					src="${pageContext.request.contextPath}/sys/style/images/item_point.gif">
				菜品信息&nbsp;
			</div>
			<!-- 本段表单字段 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<div class="ItemBlock2">
						<table cellpadding="0" cellspacing="0" class="mainForm">
							<tr>
								<td width="80px">菜系</td>
								<td>
									<select name="foodType_id" style="width: 80px">
										<%
											IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
											List<FoodType> foodTypeList = foodTypeService.getAll();
											pageContext.setAttribute("foodTypeList", foodTypeList);
										%>

										<c:choose>
											<c:when test="${not empty pageScope.foodTypeList }">
												<c:forEach var="foodType" items="${pageScope.foodTypeList }">
													<option value="${foodType.id }">${foodType.typeName }</option>
												</c:forEach>
											</c:when>
											<c:otherwise>
												无
											</c:otherwise>
										</c:choose>
									</select>
								*<input type="hidden" name="id" value="${requestScope.food.id }" /></td>
							</tr>
							<tr>
								<td width="80px">菜名</td>
								<td><input type="text" name="foodName" class="InputStyle"
									value="${requestScope.food.foodName }" /> *</td>
							</tr>
							<tr>
								<td>价格</td>
								<td><input type="text" name="price" class="InputStyle"
									value="${requestScope.food.price }" /> *</td>
							</tr>
							<tr>
								<td>会员价格</td>
								<td><input type="text" name="mprice" class="InputStyle"
									value="${requestScope.food.mprice }" /> *</td>
							</tr>

							<tr>
								<td>简介</td>
								<td><textarea name="remark" class="TextareaStyle">${requestScope.food.remark }</textarea></td>
							</tr>
							<tr>
								<td width="80px">菜品图片</td>
								<td>
									<img style='max-width: 68px; width: 68px; width: expression(width &amp; gt; 68 ? "68 px " : width "px"); max-width: 68px;'
									src="${requestScope.food.img }">
									<input type="hidden" name="img" value="${requestScope.food.img }">			
									<input type="file" name="imageUrl" /> *</td>
									
							</tr>
						</table>
					</div>
				</div>
			</div>


			<!-- 表单操作 -->
			<div id="InputDetailBar">


				<input type="submit" value="修改" class="FunctionButtonInput">




				<a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
			</div>
		</form>
	</div>
</body>
</html>
