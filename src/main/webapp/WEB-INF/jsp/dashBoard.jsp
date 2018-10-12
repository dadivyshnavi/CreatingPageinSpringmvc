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
  <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> --> 
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script type="text/javascript"
	src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css">






 

<title>Insert title here</title>
</head>
<body>
<p>Hello World</p>
<div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar text-center">
					      			<input type="button" value="Checkin"  id="Checkin" onclick="checkInAndOut(1)"  class="btn-primary btn">
					      			<input type="button" value="Checkout" id="Checkout" onclick="checkInAndOut(2)" class="btn-primary btn"/>
					      			<input type="button" data-toggle="modal" data-target="#requestModel" onclick="openRequestModel()" value="Request For Leave" class="btn-primary btn"/>
					      			<!-- <input type="button" value="Leave Approved" class="btn-primary btn"/>
					      			<input type="button" value="Leaves Rejected" class="btn-primary btn"/> -->
		
				      			</div>
				      		</div>
				      	</div>
			      	</div>

<div class="modal fade" id="requestModel" data-backdrop="static" data-keyboard="false" role="dialog">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <h1 class="modal-title">Request for Leave Assistance</h1>
    </div> 
<div class="modal-body">
      <form:form action="getRequestforleave" commandName="requestLeave"  method="post" class="login-form">

<div id="firstForm2">

<div class="form-group">
<div class="col-md-5">
<label for="emailId">Registered EmailId :</label>
</div>
<div class="col-md-7">
<form:input	type="text" name="emailId" path="emailId" onkeydown="removeBorder(this.id)" class="form-control validate emailOnly" placeholder="Registered EmailId"/>
</div><div class="clearfix"></div> 
<span class="hasError" id="cemailError" style="font-size: 13px;"></span>
</div>
							<div class="col-md-6">
							<div class="form-group">
							<label for="focusedinput" class="col-md-3 control-label">From Date<span class="impColor">*</span></label>
							<div class="col-md-6">
							<form:input type="text" path="fromDate" class="form-control validate" />
							</div></div>
							</div>

							<div class="col-md-6">
							<div class="form-group">
							<label for="focusedinput" class="col-md-3 control-label">To Date<span class="impColor">*</span></label>
							<div class="col-md-6">
							<form:input type="text" path="toDate" class="form-control validate" />
							</div></div>
							</div>

										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
											<div class="form-group">
											    <label  class="col-sm-4 control-label">Description<span style="color: red;">*</span></label>
											    <div class="col-lg-8">
											    <form:hidden path="id"  />
													<form:textarea path="description" class="form-control validate onlyCharacters" size="30" maxlength="100"></form:textarea>
													<span class="discription_error" id="name_error"></span>
												</div>
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
<!-- <div class="modal-footer">onclick="requestleave();"
<input  id="requestleave"  type="submit" class="btn btn-primary" value="Submit" />
     
    </div> -->
</form:form>	
</div>
  </div>
</div>
</div> 


<!-- <script type='text/javascript' src='js/customValidation.js'></script> -->



<script>
function checkInAndOut(x) {
var actionid =0;
alert(x);

$.ajax({
		type : "POST",
		url : "checkinout",
		data :"actionid="+x,
		dataType : "text",
		beforeSend : function() {
          $.blockUI({ message: 'Please wait' });
       }, 
		success : function(data) {
			alert(data)
			  /*  if(actionid==1)
				{
				$('#Checkin').prop('disabled', false);
				$('#Checkout').prop('disabled', true);
				}
			else 
				{
				$('#Checkin').prop('disabled', true);
				$('#Checkout').prop('disabled', false);
				}    */
			
		},
		complete: function () {
         
         $.unblockUI();
    },
		error :  function(e){$.unblockUI();console.log(e);}
		
	});


}
//$("#requestleave").click()
function requestleave()
{
	
var emailid =$("#emailId").val();
if(emailid == "" | emailid == "undefined" | emailid =="null")
{
return false;
}
	
$('#requestleave').prop('disabled', true);
 idArrayCmt11 = $.makeArray($('.validate2').map(function() {
	return this.id;
	}));
validation = true;
$.each(idArrayCmt11, function(i, val) {
	var value = $("#" + idArrayCmt11[i]).val();
	var placeholder = $("#" + idArrayCmt11[i]).attr('placeholder');
if (value == null || value == "" || value == "undefined") {
		$('style').append(styleBlock);
		$("#" + idArrayCmt11[i] ).attr("placeholder", placeholder);
		$("#" + idArrayCmt11[i] ).css('border-color','#e73d4a');
		$("#" + idArrayCmt11[i] ).css('color','#e73d4a');
		$("#" + idArrayCmt11[i] ).addClass('placeholder-style your-class');
		 var id11 = $("#" + idArrayCmt11[i]+"_chosen").length;
		if ($("#" + idArrayCmt11[i]+"_chosen").length)
		{
			$("#" + idArrayCmt11[i]+"_chosen").children('a').css('border-color','#e73d4a');
		}			
validation = false;
	} 
});
if(validation) {
	
}else {
	return false;
}
var emailid=$('#emailId').val();   
var formData = new FormData();
formData.append('emailId',emailid);
console.log(formData);
 $.ajax({
type:"POST",			
url: "getRequestforleave", 
data:formData,
processData: false,  // tell jQuery not to process the data
contentType: false,  // tell jQuery not to set contentType
success: function(result){
 if(result==true)
{
	
alert("Your Request sent to registered email")
$('#requestModal').modal('toggle');
window.location.reload();
}	
else
{
alert("Enter registered emailId");		  				
$('#requestleave').prop('disabled', false);		  	
}	  		
},
error: function (e) {
console.log(e.responseText);
} 
				    
});	
} 
function makeEmptyRequestModal()
{
	
	$('#emailId').val("");
	$('#emailId').css('border-color', 'none');
	$('#fromDate').val("");
	$('#fromDate').css('border-color', 'none');
	$('#toDate').val("");
	$('#toDate').css('border-color', 'none');
	$('#description').val("");
	$('#description').css('border-color', 'none');
	
}
function openRequestModal()
{
	$(".cancel1").click();
	makeEmptyRequestModal();
	$('#requestModel').modal();
	
}
</script>
<script type="text/javascript">
$('#fromDate').datetimepicker({

	useCurrent : false,
	format : 'DD-MMM-YYYY',
	showTodayButton : true,
	sideBySide : true,
	
	toolbarPlacement : 'top',
	focusOnShow : false,

});

$('#toDate').datetimepicker({

	useCurrent : false,
	format : 'DD-MMM-YYYY',
	showTodayButton : true,
	sideBySide : true,
	
	toolbarPlacement : 'top',
	focusOnShow : false,

});

</script>
</body>
</html>