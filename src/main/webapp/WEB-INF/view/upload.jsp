<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Ezcloud System</title>
</head>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery.mloading.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">

<script src="js/jquery-3.1.1.min.js" charset="utf-8"></script>
<script src="js/jquery-ui.min.js" charset="utf-8"></script>
<script src="js/jquery.mloading.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<script charset='utf-8'>

$(function() {
    console.log( "ready!" );
    
 	// ajax file upload
	$('form').ajaxForm({
	 success: function() {
		 //getContractsWithDate();
		 $("body").mLoading('hide');
	 },
	    complete: function(xhr) {
	 }
	});
    
});

function goWithUpload() {
	$("body").mLoading({
		  text:"Uploading...",
	});
}


</script>

<body style="overflow-y: auto;">
	<div id="header">
		<form id="target" method="POST" action="uploadFile" enctype="multipart/form-data">
			<button class="replace">엑셀파일선택</button><input type="file" name="file" class="upload">
			<input type="submit" value="업로드" onclick="goWithUpload()">
		</form>	
	</div>
	
	<div style="padding:10px;margin-top:20px;background-color:#1abc9c;">
		<table id="contractTb" border=1>
		</table>
	</div>
</body>
</html>