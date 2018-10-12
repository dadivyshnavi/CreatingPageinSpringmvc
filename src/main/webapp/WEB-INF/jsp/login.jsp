<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url var="loginAction" value="/loginAction"></spring:url>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>CharvikentEMP</title>
    <link rel="shortcut icon" href="img/charvikent.png"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <link rel="stylesheet" href="assets/css/styles.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<!--     <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600' rel='stylesheet' type='text/css'> -->
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
    <!--[if lt IE 9]>
        <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.1.0/respond.min.js"></script>
        <script type="text/javascript" src="assets/plugins/charts-flot/excanvas.min.js"></script>
    <![endif]-->

    <!-- The following CSS are included as plugins and can be removed if unused-->

<link rel='stylesheet' type='text/css' href='assets/plugins/charts-morrisjs/morris.css' /> 
<link rel='stylesheet' type='text/css' href='assets/plugins/codeprettifier/prettify.css' /> 
<link rel='stylesheet' type='text/css' href='assets/plugins/form-toggle/toggles.css' /> 
<link rel='stylesheet' type='text/css' href='assets/plugins/datatables/dataTables.css' />

<style type="text/css">
.alert-success, .alert-warning, .alert-danger{color: white !important;}
.alert-success{background-color: #4CAF50 !important;}
.alert-warning{background-color: #ff6600 !important;}
.alert-danger{background-color: #d43f3a !important;}

.your-class::-webkit-input-placeholder {color: #e73d4a !important;}
.your-class::-moz-placeholder {color: #e73d4a !important;}

.default-class::-webkit-input-placeholder {color: #e73d4a !important;}
.default-class::-moz-placeholder {color: #e73d4a !important;}
</style>

<script type='text/javascript' src='assets/js/jquery-1.10.2.min.js'></script>
<script type="text/javascript">
 window.setTimeout(function() {
    $(".msgcss").fadeTo(500, 0).slideUp(500, function(){
        $(this).remove(); 
    });
}, 2000); 
</script>

</head>

<body class="focusedform">
<div class="verticalcenter">
<div><img src="assets/img/charvikent.png" style="width:250px;" class="img-responsive"></div>
<!-- 	<h1 align="center">KHAIBAR GAS</h1> -->
	<div class="panel panel-primary">
		<form  action=login class="form-horizontal" method="POST"  style="margin-bottom: 0px !important;">
		<div class="panel-body">
			<h4 class="text-center" style="margin-bottom: 25px;">Login to get started</h4>
			<c:if test="${param.error ne null}">
				  <div class="msgcss1 row">
									<div align="center" class="form-group">
										<div style="width:80%" class=" msgcss alert alert-danger fadeIn animated">Invalid username and password.</div>
									</div>
								</div>
			
				<%-- <div class="col-sm-12" style="margin-bottom: -1.3em;">
					<div class="form-group">
						<div class="msgcss fadeIn animated alert alert-danger" style="text-align: center;">${msg}</div>
					</div>
				</div> --%>
			</c:if>
			<div class="form-group">
				<div class="col-sm-12">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<input type="text" id="username" name= "username" class="form-control validate"  placeholder="Username" maxlength="10"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						<input type="password" id="password" name="password" class="form-control validate"  placeholder="Password" maxlength="6"/>
					</div>
				</div>
			</div>
		
		 <div class="login-bottom"> 
			<div class="col-sm-6">
		<h6><a href="#" data-toggle="modal" data-target="#passwordModel" onclick="openPasswordModal()" style="color:orange;" class="tag">Forgot password</a>
		</h6>
		</div> 
		
		<div class="panel-footer">
			<div class="pull-right">
				<input type="reset" value="Reset" class="btn btn-default cancel"/>
				<input type="submit" value="Sign-in" id="signin" class="btn btn-primary">
			</div>
		</div>
		</div> 
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		</form>
	</div>
</div>

<div class="modal fade" id="passwordModel" data-backdrop="static" data-keyboard="false" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Password Assistance</h4>
        </div> 
	<div class="modal-body">
          <form action="#" id="resetpassword” method="post" class="login-form">

<div id="firstForm2">

<div class="form-group">
<div class="col-md-5">
<label for="emailId">Registered EmailId :</label>
</div>
<div class="col-md-7">
<input	type="text" name="emailId" id="emailId" onkeydown="removeBorder(this.id)" class="form-control validate2 emailOnly" placeholder="Registered EmailId"/>
</div><div class="clearfix"></div> 
<span class="hasError" id="cemailError" style="font-size: 13px;"></span>
</div>				
</div>
</form>	
 </div>
  <div class="modal-footer">
  <button type="button" id="resetpassword" onclick="resetpassword()" class="btn btn-primary" >Submit</button>
         
        </div>
      </div>
    </div>
  </div> 

<script type='text/javascript' src='js/customValidation.js'></script>
<script type="text/javascript">

$('#signin').click(function() {
	var musername =	$('#username').val();
	var mpass =	$('#password').val();
	if (musername == null || musername == "" || musername == "undefined") {
		alert("Please Enter username")
	return false;
	}
	if (mpass == null || mpass == "" || mpass == "undefined") {
		alert(" Please Enter password")
	return false;
	}
		
	});


function resetpassword()
{
	
var emailid =$("#emailId").val();
if(emailid == "" | emailid == "undefined" | emailid =="null")
{
return false;
}
	
$('#resetpassword').prop('disabled', true);
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
url: "getresetpassword", 
data:formData,
processData: false,  // tell jQuery not to process the data
contentType: false,  // tell jQuery not to set contentType
success: function(result){
if(result==true)
{
	
alert("Your Password sent to registered email and mobile number ")
$('#passwordModel').modal('toggle');
window.location.reload();
}	
else
{
alert("Enter registered emailId");		  				
$('#resetpassword').prop('disabled', false);		  	
}	  		
},
error: function (e) {
console.log(e.responseText);
}
				    
});	
} 
function makeEmptyPasswordModal()
{
	
	$('#emailId').val("");
	$('#emailId').css('border-color', 'none');
}
function openPasswordModal()
{
	$(".cancel1").click();
	makeEmptyPasswordModal();
	$('#passwordModel').modal();
	
}


</script> 
</body>
</html>