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
<p>Hello World</p>
<div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar text-center">
					      			<input type="button" value="Checkin"  id="Checkin" onclick="checkInAndOut(1)"  class="btn-primary btn">
					      			<input type="button" value="Checkout" id="Checkout" onclick="checkInAndOut(2)" class="btn-primary btn"/>
					      			<input type="button" value="Request For Leave" class="btn-primary btn"/>
					      			<input type="button" value="Leave Approved" class="btn-primary btn"/>
					      			<input type="button" value="Leaves Rejected" class="btn-primary btn"/>
				      			</div>
				      		</div>
				      	</div>
			      	</div>
</body>


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
</script>

</html>