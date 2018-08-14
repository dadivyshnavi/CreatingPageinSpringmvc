<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Page</title>
</head>
<body>
<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="background-color:  white !important; padding-top: 15PX;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Student List</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
					<span id="PasswordSuccessmsg"></span>
					<input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
						<div class="table-responsive" id="tableId">
							<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
								<thead><tr><th>First  Name</th><th>Last Name</th><th></th></tr></thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
<!-- 		<a class="btn btn-info btn-lg"  onclick="PopupFillingStation();">Add Gas</a><br><br> -->
		<div class="row" id="moveTo">
			<div class="col-md-12 col-sm-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Add Student</h4>
						
						
					</div>
					
					
		<form:form modelAttribute="studentForm" action="student" class="form-horizontal " method="Post" enctype="multipart/form-data">
	                  <form:hidden path="id"/>	
						<div class="col-md-6"><br>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">First Name<span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="fname" class="form-control validate onlyCharacters" placeholder="Enter First Name" maxlength="20"/>
									</div>
								</div></div>
								<div class="col-md-6"><br>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Last Name<span class="impColor">*</span></label>
								<div class="col-md-6">
										<form:input path="lname" class="form-control validate onlyCharacters" placeholder="Enter Last Name" maxlength="20"/>
									</div>
								</div>
								</div>
								<div class="col-md-6"><br>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Mobile No<span class="impColor">*</span></label>
								<div class="col-md-6">
										<form:input path="mobile" class="form-control validate numericOnly" placeholder="Enter Mobileno" maxlength="10"/>
									</div>
								</div>
								</div>
								<div class= "col-md-6"><br>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Course<span class="impColor">*</span></label>
								<div class="col-md-6">
										<form:select path="course" class="form-control validate" onfocus="removeBorder(this.id)">
										<form:option value="">-- Select Course --</form:option>
											<form:options items="${roles}"/>
										</form:select>
									</div>
								</div>
								</div>
								
								<div class="col-md-6"><br>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">DateOfBirth<span class="impColor">*</span></label>
									 <form:input type="datetime-local" path="dob" />
								<div class="col-md-6">
									</div>
								</div>
								</div>
								<div class= "col-md-6"><br>
								<div class="form-group">
									<label class="col-md-3 control-label no-padding-right">Files<span class="impColor">*</span></label>
								<div class="col-md-6">
										 <input type="file" name="file1" id="file1" multiple/>
										
									</div>
								</div>
								</div>
									<div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar text-center">
					      			<input type="submit" id="submit1" value="Submit" class="btn-primary btn"/>
					      			<input type="reset" value="Reset" class="btn-danger btn cancel"/>
				      			</div>
				      		</div>
				      	</div>
			      	</div>
			</form:form>
</div>
</div>
</div>
</div>


</body>
<script type="text/javascript">
var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>First  Name</th><th>Last Name</th><th>Mobile No </th><th>Course</th><th>Date Of Birth</th><th>Files</th><th style="text-align: center;">Options</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick='deleteStudent("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>";
			var cls="activecss";
		}else{  
			var deleterow = "<a class='activate' onclick='deleteStudent("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>";
			var cls="inactivecss";
		}
		var edit = "<a class='edit editIt' onclick='editStudent("	+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr class='"+ cls +"'>"
			/* + "<td title='"+orderObj.id+"'>"+ orderObj.id + "</td>" */
			
			+ "<td title='"+orderObj.fname+"'>"+ orderObj.fname + "</td>"
			+ "<td title='"+orderObj.lname+"'>"+ orderObj.lname + "</td>"
			+ "<td title='"+orderObj.mobile+"'>"+ orderObj.mobile + "</td>"
			+ "<td title='"+orderObj.course+"'>"+ orderObj.course + "</td>"
			+ "<td title='"+orderObj.dob+"'>"+ orderObj.dob + "</td>"
			+ "<td title='"+orderObj.files+"'>"+ orderObj.files + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
			/* + "<td ><a style='cursor:pointer' onclick='getPasswordModal("+ orderObj.id +")'>Change Password</a></td>" */ 
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	if(isClick=='Yes') $('.datatables').dataTable();
	
}

function deleteStudent(id,status){
	var checkstr=null;
	if(status == 0){
		 checkstr = confirm('Are you sure you want to Deactivate?');
	}else{
		 checkstr = confirm('Are you sure you want to Activate?');
	}
	if(checkstr == true){
		var formData = new FormData();
	    formData.append('id', id);
	    formData.append('status', status);
		$.fn.makeMultipartRequest('POST', 'deleteStudent', false, formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			toolTips(); //calling tool tips defined at header
			$('#inActive').prop('checked', false);
		});
	}
}

function inactiveData() {
	var status="0";
	if($('#inActive').is(":checked") == true){
		status="0";
	}else{
		status="1";
	     }
		var formData = new FormData();
		formData.append('status', status);
		
		$.fn.makeMultipartRequest('POST', 'inActiveStudents', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			console.log(jsonobj.allOrders1);
				});
		
}
function editStudent(id) {
	
	$("#id").val(serviceUnitArray[id].id);
	
	$("#fname").val(serviceUnitArray[id].fname);
	$("#lname").val(serviceUnitArray[id].lname);
	$("#mobile").val(serviceUnitArray[id].mobile);
	$("#course").val(serviceUnitArray[id].courseid);
	$("#dob").val(serviceUnitArray[id].dob);
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
	
	

}
/* this is code for files limitation */
document.getElementById("file1").onchange = function () {
alert("Vyshnavi here");
var reader = new FileReader();
    if(this.files[0].size>1000){
        alert("file Size should not be greater than 5mb");
        $("#file1").attr("src","blank");
       // $("#file1").hide();  
        $('#file1').wrap('<form>').closest('form').get(0).reset();
        $('#file1').unwrap();     
        return false;
    }	
    /* if(this.files[0].type.indexOf("image")==-1){
        alert("Invalid Type");
        $("#file1").attr("src","blank");
        //$("#file1").hide();  
       $('#file1').wrap('<form>').closest('form').get(0).reset();
      //  $('#file1').unwrap();         
        return false;
    }    */
    reader.onload = function (e) {
        // get loaded data and render thumbnail.
        document.getElementById("menu_image").src = e.target.result;
        $("#file1").show(); 
    };

    // read the image file as a data URL.
    reader.readAsDataURL(this.files[0]);
};

$("#pageName").text("Student Master");
$(".student").addClass("active"); 
</script>



</html>