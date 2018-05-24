<%@page import="cn.edu.nyist.bookmanv1.vo.BookVo"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.nyist.bookmanv1.vo.TypeVo"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>书籍修改</title>
<!-- 1、告诉浏览器表缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container-fluid  well">
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" role="form" method="post" action="doBookEdit" id="bookAddFrm" enctype="multipart/form-data">
			
					<%
						if (request.getAttribute("msg") != null) {
					%>
					<div class="alert alert-warning" role="alert"><%=request.getAttribute("msg")%></div>
					<%
						}
					BookVo bookVo=(BookVo)request.getAttribute("bookVo");
					%>
						  <input type="hidden"    value="<%=bookVo.getId()%>" name="id">
					<div class="form-group">

						<label for="inputName" class="col-sm-2 control-label"> 书名: </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputName" name="name" value="<%=bookVo == null||bookVo.getName()==null ? "" : bookVo.getName()%>" />
						</div>
					</div>
					<div class="form-group">

						<label for="textAreaDescri" class="col-sm-2 control-label"> 描述: </label>
						<div class="col-sm-10">
							<textarea name="descri" class="form-control" id="textAreaDescri"  >
							<%=bookVo == null||bookVo.getDescri()==null ? "" : bookVo.getDescri()%>
							
							</textarea>
						</div>
					</div>

					<div class="form-group">

						<label for="inputPhoto" class="col-sm-2 control-label"> 图片: </label>
						<div class="col-sm-4">
							<input type="file" class="form-control" id="inputPhoto" name="photo"/>
							
						</div>
						<div  class="col-sm-6">
						    <% if(bookVo!=null&&bookVo.getPhoto()!=null){
						    	
						    	%>
						    	<img  src="upload/<%=bookVo.getPhoto()%>">
						    	<% 
						    } %>
						</div>
					</div>
					<div class="form-group">

						<label for="inputPrice" class="col-sm-2 control-label"> 价格: </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPrice" name="price"   value='<%=bookVo == null ? "" : bookVo.getPrice()%>' />
						</div>
					</div>
					<div class="form-group">

						<label for="inputPubDate" class="col-sm-2 control-label"> 出版时间: </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPubDate" name="pubDate"    value='<%=bookVo == null||bookVo.getPubDate()==null ? "" : bookVo.getPubDate()%>'/>
						</div>
					</div>
					<div class="form-group">

						<label for="inputAuthor" class="col-sm-2 control-label"> 作者: </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputAuthor" name="author"  value='<%=bookVo == null||bookVo.getAuthor()==null ? "" : bookVo.getAuthor()%>'/>
						</div>
					</div>
					<div class="form-group">

						<label for="selectTid" class="col-sm-2 control-label"> 类型: </label>
						<div class="col-sm-10">
							<select name="tid" class="form-control" id="selectTid">
							</select>
						</div>
					</div>
					<div class="form-group">

						<label for="inputVcode" class="col-sm-2 control-label"> 验证码 </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="inputVcode" name="vcode" maxlength="4" />

						</div>
						<div class="col-sm-4">
							<img alt="" src="vcode.png" id="vcodeImg" title="单击换图片">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">

							<button type="submit" class="btn btn-default">修改</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
	<script type="text/javascript" src="bower_components/jquery-validation/dist/jquery.validate.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#vcodeImg").click(function(evt) {
				//不加不会换
				this.src = "vcode.png?t=" + Math.random();
			});
			//日期控件
			$('#inputPubDate').datepicker({
				format : 'yyyy-mm-dd',//日期格式
				language : 'zh-CN',//提示中文界面
				autoclose : true
			//自动关闭
			});
			//添加验证
			$("#bookAddFrm")
					.validate(
							{
								rules : {
									name : "required",
									price : {
										required : true,
										number : true
									}
								},
								messages : {
									name : "书名必填",
								
									price : {
										required : "必填",
										number : "必须是数字"
									}
								},
								errorElement : "em",
								errorPlacement : function(error, element) {
									// Add the `help-block` class to the error element
									error.addClass("help-block");
									error.addClass("alert-warning");
									if (element.prop("type") === "checkbox") {
										error.insertAfter(element
												.parent("label"));
									} else {
										error.insertAfter(element);
									}
								},
								highlight : function(element, errorClass,
										validClass) {
									$(element).parents(".col-sm-6").addClass(
											"has-error").removeClass(
											"has-success");
								},
								unhighlight : function(element, errorClass,
										validClass) {
									$(element).parents(".col-sm-6").addClass(
											"has-success").removeClass(
											"has-error");
								}
							});
		});
		function fillSel(types) {
			var  tid=<%=bookVo.getTid()%>;
			var sel = document.getElementById("selectTid");
			for (var i = 0; i < types.length; i++) {
				var op=new Option(types[i].name, types[i].id);
				sel.appendChild(op);
				if (tid==types[i].id) {
					op.selected=true;
				}
			}
		}
	</script>
	<!-- iframe处于安全考虑不允许执行JavaScript -->
	<iframe src="findAllTypes" style="display: none;"></iframe>
</body>
</html>