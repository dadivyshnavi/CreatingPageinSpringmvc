<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="Notifications">Home</a></li>
		<li>Inbox</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="background-color:  white !important; padding-top: 15PX;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Mails List</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
						
					</div>
					
					<div class="panel-body collapse in">
					<span id="PasswordSuccessmsg"></span>
					<!--  <input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label> -->						<div class="table-responsive" id="tableId">
							<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
								<thead><tr><th>Employee ID</th><th>Email</th><th>Subject</th> <th>Description</th></thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div> 

</div>

</body>
 <script type="text/javascript">
  var editFields=0;
  var listOrders1 = ${allOrders1};
  if (listOrders1 != "") {
  	displayTable(listOrders1);
  }
  function displayTable(listOrders) {
  	$('#tableId').html('');
  	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
  			+ '<thead><tr><th>Emp Id</th><th>EmailId</th><th>Subject</th><th>Description</th><tbody></tbody></table>';
  	$('#tableId').html(tableHead);
  	serviceUnitArray = {};
  			var msgIncrement = 0;
  	 $.each(listOrders,function(i, orderObj)  { 
  			 
  			//$('#notificationTableHeader').html('');
  		 if(orderObj.status == "1"){
  			/* var deleterow = "<a class='deactivate' onclick='deleteMails("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>";
  			var cls="activecss"; */
  			var view1 ="<a class='view viewIt' href='viewMailNotifications?id="+ orderObj.empId+ "&rowid="+orderObj.id+">"+ orderObj.empId + "</a>"
  			var unread = "<tr>"
  	  			+ "<td title='"+orderObj.empId+"'><a href=viewMailNotifications?id='"+ orderObj.empId+ "'&rowid='"+orderObj.id+"'>"+ orderObj.empId + "</a></td>"
  	  			+ "<td title='"+orderObj.emailId+"'><b>"+ orderObj.emailId + "</b></td>"
  	  			+ "<td title='"+orderObj.subject+"'><b>"+ orderObj.subject + "</b></td>"
  	  			+ "<td title='"+orderObj.description+"'><b>"+ orderObj.description + "</b></td>"
  	  			+ "</tr>";
  			$(unread).appendTo("#tableId table tbody");
  			
  			
  			//For Notification 
  			/* msgIncrement++;
  			
  			var notification = "<tr>"
  	  			+ "<td title='"+orderObj.empId+"'><b>"+ orderObj.empId+ "</b></td>" 
  	  			+ "<td title='"+orderObj.subject+"'><b>"+ orderObj.subject + "</b></td>"
  	  			+ "</tr>";
  			
  			//$(notification).appendTo("#notificationTableHeader table tbody");
  			
  			 $("#notificationTableHeader tbody").append(notification);
  			 $("#noOfMessages").text(msgIncrement);
  			 */
  			
  			
  		}else{  
  			/* var deleterow = "<a class='activate' onclick='deleteMails("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>";
  			var cls="inactivecss"; */
  		   
  		var edit = "<a class='edit editIt' onclick='editNotification("+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>" 
  		var view1 ="<a class='view viewIt' href='viewMailNotifications?id="+ orderObj.empId+ "&rowid="+orderObj.id+">"+ orderObj.empId + "</a>"
  		serviceUnitArray[orderObj.id] = orderObj;
  		var tblRow = "<tr>"
  			+ "<td title='"+orderObj.empId+"'><a href=viewMailNotifications?id='"+ orderObj.empId+ "'&rowid='"+orderObj.id+"'>"+ orderObj.empId + "</a></td>" 
  			+ "<td title='"+orderObj.emailId+"'>"+ orderObj.emailId + "</td>"
  			+ "<td title='"+orderObj.subject+"'>"+ orderObj.subject + "</td>"
  			+ "<td title='"+orderObj.description+"'>"+ orderObj.description + "</td>"
  			/* + "<td title='"+orderObj.emailId+"'>"+ orderObj.emailId + "</td>"
  			+ "<td title='"+orderObj.role+"'>"+ orderObj.role + "</td>"
  			+ "<td title='"+orderObj.shift+"'>"+ orderObj.shift + "</td>"
  			+ "<td title='"+orderObj.aadharNo+"'>"+ orderObj.aadharNo + "</td>"
  			+ "<td title='"+orderObj.emergencyNo+"'>"+ orderObj.emergencyNo + "</td>"
  			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
  			+ "<td ><a style='cursor:pointer' onclick='getPasswordModal("+ orderObj.id +")'>Change Password</a></td> */
  			+ "</tr>";
  		$(tblRow).appendTo("#tableId table tbody");
  		}
  	 });
  	if(isClick=='Yes') $('.datatables').dataTable();
  	
  }
  
  
</script>
<script type="text/javascript">
$("#pageName").text("Primary Notifications");
$(".notification").addClass("active");
</script>
</html>