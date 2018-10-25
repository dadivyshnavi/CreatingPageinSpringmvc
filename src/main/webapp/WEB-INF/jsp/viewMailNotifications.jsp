<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style type="text/css">
.fa-square:before {
    content: "\f0c8";
}
.fa-status-box {
    font-size: 1.8em;
    line-height: 0.73em;
    vertical-align: -25%;
}
.status-90-color {
    color: #c9ccc4 !important;
    background-color: #c9ccc4;
}
.status-10-color {
    color: #fcbdbd !important;
    background-color: #fcbdbd;
}
.status-80-color {
    color: #d2f5b0 !important;
    background-color: #d2f5b0;
}
.panel-body {

	border: 1px solid #4f8edc !important;
	padding: 0px !important;
}
.btn-white.btn-primary.active, .btn-white.btn-primary:active, .btn-white.btn-primary:focus, .btn-white.btn-primary:hover, .open>.btn-white.btn-primary.active.dropdown-toggle, .open>.btn-white.btn-primary.dropdown-toggle {
    background-color: #eaf2f8!important;
    border-color: #8aafce !important;
    color: #537c9f!important;
}
.widget-toolbox {
    background-color: #EEE;
}
.widget-toolbox.padding-8 {
    padding: 8px;
}

.widget-toolbox:first-child {
    padding: 2px;
    border-bottom: 1px solid #CCC;
}
.btn-sm {
	margin:8px;
	letter-spacing: 1px;
	line-height: 1.6;
}
.tr {
	background:#166eaf;
	color:#ffffff;
}
</style>

<!-- Body starts here -->

	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li class="">View mail Details</li>
				</ul><!-- /.breadcrumb -->
			</div>
			
			<div class="page-content">
				<div class="row">
				
				<!-- view-->
			<div class="container">
				<div class="col-md-12">
				<div class="panel panel-primary">
					<div style="margin:0 auto;" class="panel-heading">
						<h4>View Mail Details</h4>
						<div class="options">
							<a href="" class="panel-collapse"><i class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div class="panel panel-body collapse in">
						<br>
						<div class="col-md-2"></div>
						<div class="col-md-8">
						
						<div class="table-responsive">
								<table class="table table-bordered priority prioritybg" style="border: 1px solid #0460a4; width:;" id="viewMailNotificationTable">						
      <c:forEach var="listOuter" items="${test2}">
              <c:forEach var="listInner" items="${listOuter}">
                 <tr><td>${listInner.key}</td><td>${listInner.value}</td></tr>
              </c:forEach>
         
      </c:forEach>
</table>
</div>
</div>
	<div class="col-md-2"></div>
</div>
</div>
</div>
<div align="center"><button onclick="goBack()" class="btn btn-primary"> <i class="fa fa-step-backward"></i> Back  </button></div>	
</div>
</div>
</div>
</div>
</div>

<!-- Body ends here -->

	 <link rel="stylesheet" type="text/css" href="http://charvikent.com/mantis/css/dropzone-4.3.0.min.css" /> 
<script type="text/javascript">
$(".notification").addClass("active");
$("#pageName").text("View Mail Details");
function goBack() 
{
    window.history.go(-1);
}
</script>