<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<title>EZCloud</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- <link rel="stylesheet" type="text/css" href="css/common.css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.mloading.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">

<script src="js/jquery-3.1.1.min.js" charset="utf-8"></script>
<script src="js/canvasjs.min.js"></script>

<script src="js/jquery-ui.min.js" charset="utf-8"></script>
<script src="js/jquery.mloading.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<body class="w3-light-grey">

<!-- Top container -->
<div id="topMenu" class="w3-bar w3-top w3-black w3-large" style="z-index:4">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  <span class="w3-bar-item w3-right">Ezcloud</span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
  <div class="w3-container w3-row">
    <div class="w3-col s8 w3-bar">
      <span>Welcome, <strong>Mike</strong></span><br>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i></a>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
    </div>
  </div>
  <hr>
  <div class="w3-container">
    <h5>Dashboard</h5>
  </div>
  <div class="w3-bar-block">
    <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
    <a href="#" data-menu="1" onclick="selectMenu(this)" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-users fa-fw"></i>  Overview</a>
    <a href="#" data-menu="2" onclick="selectMenu(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-eye fa-fw"></i>  Upload</a>
    <a href="#" data-menu="3" onclick="selectMenu(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i>  Database</a>
    <a href="#" data-menu="4" onclick="selectMenu(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bullseye fa-fw"></i>  Parking</a>
    <a href="#" data-menu="5" onclick="selectMenu(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-codepen fa-fw"></i>  Get data</a>
    <!-- 
    <a href="#" onclick="selectMenu(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-diamond fa-fw"></i>  Orders</a>
    <a href="#" onclick="selectMenu(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i>  News</a>
    <a href="#" onclick="selectMenu(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bank fa-fw"></i>  General</a>
    <a href="#" onclick="selectMenu(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-history fa-fw"></i>  History</a>
    <a href="#" onclick="selectMenu(this)" class="w3-bar-item w3-button w3-padding"><i class="fa fa-cog fa-fw"></i>  Settings</a><br><br>
     -->
  </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div id="content1" class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
  </header>

  <div class="w3-row-padding w3-margin-bottom">
    <div class="w3-quarter">
      <div class="w3-container" style="height:230px">
      	<div id="chartContainer1"></div>
      </div>
    </div>
    <div class="w3-quarter">
      <div class="w3-container" style="height:230px">
        <div id="chartContainer2"></div>
      </div>
    </div>
    <div class="w3-quarter">
      <div class="w3-container" style="height:230px">
        <div id="chartContainer3"></div>
      </div>
    </div>
    <div class="w3-quarter">
      <div class="w3-container" style="height:230px">
        <div id="chartContainer4"></div>
      </div>
    </div>
  </div>

  <hr/><hr/><hr/><hr/>

  <div class="w3-panel">
    <div class="w3-row-padding" style="margin:0 -16px">
      <div class="w3-third" style="width:800px">
      	<div id="chartContainer5" style="height: 230px; max-width: 800px; margin: 0px auto;"></div>
      </div>
      <div class="w3-twothird" style="width:770px">
      	<div id="chartContainer6" style="height: 230px; max-width: 770px; margin: 0px auto;"></div>
      </div>
    </div>
  </div>

  <!-- Footer -->
  <footer class="w3-container w3-padding-16 w3-light-grey">
    <h4>FOOTER</h4>
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
  </footer>

  <!-- End page content -->
</div>

<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////// -->

<!-- !PAGE CONTENT! -->
<div id="content2" class="w3-main" style="margin-left:300px;margin-top:43px;display:none;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
  </header>

  <div class="w3-container">
    <h5>엑셀 파일 업로드</h5>
    <form id="target" method="POST" action="uploadFile" enctype="multipart/form-data">
		<button class="w3-button w3-dark-grey replace">엑셀파일선택<i class="fa fa-arrow-right"></i></button><input type="file" name="file" class="upload">
		<input class="w3-button w3-dark-grey" type="submit" value="업로드" onclick="goWithUpload()">
	</form>
	
    <!-- 
    <button class="w3-button w3-dark-grey">파일선택 <i class="fa fa-arrow-right"></i></button>
    <button class="w3-button w3-dark-grey">업로드 <i class="fa fa-arrow-up"></i></button>
    -->
  </div>

  <!-- Footer -->
  <footer class="w3-container w3-padding-16 w3-light-grey">
    <h4>FOOTER</h4>
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
  </footer>

  <!-- End page content -->
</div>

<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////// -->

<!-- !PAGE CONTENT! -->
<div id="content3" class="w3-main" style="margin-left:300px;margin-top:43px;display:none;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
  </header>
  
  <div class="w3-panel">
    <div class="w3-row-padding" style="margin:0 -16px">
    
    	<div class="w3-quarter">
		<div class="w3-card">
		  <div class="w3-container w3-indigo">
		    <h5>DB INFO</h5>
		  </div>
		  <div class="w3-container w3-text-indigo" id="dbInfoName"><!-- <h4>Parking</h4> --></div>
		  <ul class="w3-ul">
		    <li id="dbInfoList">
		      <!-- 
		      <p>Collection count : 3</p>
		      <p>Collection size : 104kb</p>
		      <p>Data size : 120kb</p>
		      <p>Storage size : 200kb</p>
		      <p>Indexes : 3</p>
		      -->
		    </li>
		  </ul>
		  <div class="w3-container w3-indigo w3-xlarge">&nbsp;<span class="w3-right">&nbsp;</span></div>
		  <!-- <div class="w3-container w3-indigo w3-xlarge">&laquo;<span class="w3-right">&raquo;</span></div>  -->
		</div>
		</div>
		
		<div class="w3-threequarter">
      	<div class="w3-card">
		  <div class="w3-container w3-cyan">
		       <h6>
		          <input id="radioTerm" class="w3-radio" type="radio" name="searchMode" value="term" checked>
		                 검색기간설정 : <input type="date" id="sDate" style="width:150px;"> ~ <input type="date" id="eDate" style="width:150px;"> 
		          &nbsp;&nbsp;
		          
		          <input id="radioKey" class="w3-radio" type="radio" name="searchMode" value="key">
		          <label for="column">컬럼설정 : </label>
				  <select id="collectionKeys" name="column">
				  <!-- 
				    <option value="usa">Name</option>
				    <option value="usa">Addr</option>
				    <option value="usa">No</option>
				  -->
				  </select>
				  &nbsp;&nbsp;검색어설정 : <input id="searchText" type="text" style="width:100px;height:30px;" onkeypress="if(event.keyCode==13) {goMongoSearch(); return false;}">
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="w3-btn w3-ripple w3-blue w3-padding-small" style="width:100px;height:36px;" onClick="goMongoSearch()">검색</button>
				  <button id="btnShowMode" class="w3-btn w3-ripple w3-amber w3-padding-small w3-right" style="width:100px;height:36px;" onClick="switchShowMode()">그래프</button>
		       </h6>
		  </div>
		  <!-- <div class="w3-container w3-text-cyan"><h4>parkingZone</h4><h4>pubdata</h4><h4>pubdata2</h4></div> -->
		  <!-- //////////////////////////////////////////// FOR LIST ///////////////////////////////////////////////////////////// -->
		  <div id="divForList">
		  	  <table id="docList" class="w3-table w3-bordered w3-striped w3-border test w3-hoverable"></table>
		  	  <!-- 
			  <ul class="w3-ul">
			    <li id="docList">
			    </li>
			  </ul>
			   -->
			  <div class="w3-container w3-cyan w3-xlarge w3-center">
			  	<span class="w3-left"><button class="w3-btn w3-ripple w3-blue w3-padding-small w3-right" style="width:100px;height:30px;font-size:18px;" onClick="goPrevSearch()">Prev</button></span>
			  	<span id="currentPage" style="font-size:20px"><!-- 총20페이지  --></span>
			  	<span class="w3-right"><button class="w3-btn w3-ripple w3-blue w3-padding-small w3-right" style="width:100px;height:30px;font-size:18px;" onClick="goNextSearch()">Next</button></span>
			  </div>
		  </div>
		  <!-- <div class="w3-container w3-cyan w3-xlarge">&nbsp;<span class="w3-right">&nbsp;</span></div> -->
		  <!-- //////////////////////////////////////////// FOR LIST ///////////////////////////////////////////////////////////// -->
		  
		  <!-- //////////////////////////////////////////// FOR GRAPH ///////////////////////////////////////////////////////////// -->
		  <div id="divForGraph" style="display:none">
		  	  <hr/>
			  <div id="chartContainer7" style="height: 300px; max-width: 950px; margin: 0px auto;"></div>
			  <hr/>
			  <!-- <div class="w3-container w3-cyan w3-xlarge">&nbsp;<span class="w3-right">&nbsp;</span></div> -->
			  
			  <div class="w3-container w3-cyan w3-xlarge w3-center">
			  	<span class="w3-right">
			  		<h6>
						<select id="selGraphByDate" class="w3-select" name="option">
						<!--
						  <option value="" disabled selected>날짜선택</option>
						  <option value="1">2018-04-12</option>
						  <option value="2">2018-04-13</option>
						  <option value="3">2018-04-14</option>
						 -->
						</select>
					</h6>
			  	</span>
			  </div>
			  
		  </div>
		  <!-- ///////////////////////////////////////////// FOR GRAPH //////////////////////////////////////////////////////////// -->
		  
		</div>
      </div>
		
    </div>
    
    <hr/>
    
    <div class="w3-row-padding" style="margin:0 -16px">
    
      <div class="w3-quarter">
      	<div class="w3-card">
		  <div class="w3-container w3-blue">
		    <h5>COLLECTIONS LIST</h5>
		  </div>
		  <div id="collectionNamesList" class="w3-container w3-text-blue"><!-- <h4>parkingZone</h4><h4>pubdata</h4><h4>pubdata2</h4> --></div>
		  
		  <div class="w3-container w3-deep-purple">
		    <h5>COLLECTIONS INFO</h5>
		  </div>
		  <ul class="w3-ul">
		    <li id="clInfoList">
		      <!-- 
		      <p>Name : parkingZone</p>
		      <p>Count : 3</p>
		      <p>Size : 104kb</p>
		      <p>Storage size : 120kb</p>
		      <p>Indexes : 2</p>
		      -->
		    </li>
		  </ul>
		  
		  <div class="w3-container w3-deep-purple w3-xlarge">&nbsp;<span class="w3-right">&nbsp;</span></div>
		</div>
      </div>
      
    </div>
    
  </div>

  <!-- Footer -->
  <footer class="w3-container w3-padding-16 w3-light-grey">
    <h4>FOOTER</h4>
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
  </footer>

  <!-- End page content -->
</div>

<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////// -->

<!-- !PAGE CONTENT! -->
<div id="content4" class="w3-main" style="margin-left:300px;margin-top:43px;display:none;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
  </header>

  <div class="w3-container">
    <h5>주차장 리스트</h5>
    <table id="parkingList" class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
      <!-- 
      <tr>
        <td>United States</td>
        <td>65%</td>
      </tr>
      <tr>
        <td>UK</td>
        <td>15.7%</td>
      </tr>
      <tr>
        <td>Russia</td>
        <td>5.6%</td>
      </tr>
      <tr>
        <td>Spain</td>
        <td>2.1%</td>
      </tr>
      <tr>
        <td>India</td>
        <td>1.9%</td>
      </tr>
      <tr>
        <td>France</td>
        <td>1.5%</td>
      </tr>
       -->
    </table><br>
    <button onClick="prevPage()" class="w3-button w3-dark-grey"><i class="fa fa-arrow-left"></i>  Prev</button>
    <button onClick="nextPage()" class="w3-button w3-dark-grey">Next  <i class="fa fa-arrow-right"></i></button>
  </div>

  <!-- Footer -->
  <footer class="w3-container w3-padding-16 w3-light-grey">
    <h4>FOOTER</h4>
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
  </footer>

  <!-- End page content -->
</div>

<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////// -->

<!-- !PAGE CONTENT! -->
<div id="content5" class="w3-main" style="margin-left:300px;margin-top:43px;display:none;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
  </header>

  <div class="w3-container">
    <h5>각 주차장 데이터 가져오기</h5>
         기간설정 : <input type="date" id="getDataSDate" style="width:150px;"> ~ <input type="date" id="getDataEDate" style="width:150px;">&nbsp;&nbsp;
     <button class="w3-button w3-purple w3-round-large" onclick="getData()">가져오기</button>
  </div>

  <!-- Footer -->
  <footer class="w3-container w3-padding-16 w3-light-grey">
    <h4>FOOTER</h4>
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
  </footer>

  <!-- End page content -->
</div>

<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////// -->

<script>

var currentPage = 0;
var currentDBPage = 0;
var searchModeVal = 0; // 0: term search, 1: key search
var currentSelectedColl = "";
var showMode = 0; // 0: list, 1: graph
var chart7 = null;
var totalDBPage = 0;
var parsedAggData = [];

function switchShowMode() {
	if(0 == showMode) {	// show list
		$('#divForList').css("display", "none");
		$('#divForGraph').css("display", "block");
		$('#btnShowMode').html("리스트");
		showMode = 1;
	} else {			// show graph
		$('#divForList').css("display", "block");
		$('#divForGraph').css("display", "none");
		$('#btnShowMode').html("그래프");
		showMode = 0;
	}
}

function prevPage() {
	if(0 == currentPage) {
		currentPage = 0;
	} else {
		currentPage = currentPage - 1;
	}
	
	getParkingZoneJson(currentPage)
}

function nextPage() {
	currentPage = currentPage + 1;
	
	getParkingZoneJson(currentPage)
}

$(function() {
    //console.log( "ready!" );
    
    // ajax file upload
	$('form').ajaxForm({
	 success: function() {
		 //getContractsWithDate();
		 $("body").mLoading('hide');
	 },
	    complete: function(xhr) {
	 }
	});
    
	var chart = new CanvasJS.Chart("chartContainer1", {
		width:230,
		height:230,
		theme: "light2",
		animationEnabled: true,
		title:{
			text: "전체",
			horizontalAlign: "center"
		},
		data: [{
			type: "doughnut",
			indexLabelPlacement: "inside",
			showInLegend: true,
			startAngle: 60,
			//innerRadius: 60,
			indexLabelFontSize: 17,
			indexLabelFontColor: "black",
			indexLabel: "{label} #percent%",
			toolTipContent: "<b>{label}:</b> {y} (#percent%)",
			dataPoints: [
				{ y: 67, label: "점유", name: "점유" },
				{ y: 33, label: "가능", name: "가능" }
			]
		}]
	});
	chart.render();
	
	var chart2 = new CanvasJS.Chart("chartContainer2", {
		width:230,
		height:230,
		theme: "light2",
		animationEnabled: true,
		title:{
			text: "인제공영",
			horizontalAlign: "center"
		},
		data: [{
			type: "doughnut",
			indexLabelPlacement: "inside",
			showInLegend: true,
			startAngle: 60,
			//innerRadius: 60,
			indexLabelFontSize: 17,
			indexLabelFontColor: "black",
			indexLabel: "{label} #percent%",
			toolTipContent: "<b>{label}:</b> {y} (#percent%)",
			dataPoints: [
				{ y: 28, label: "점유", name: "점유" },
				{ y: 72, label: "가능", name: "가능" }
			]
		}]
	});
	chart2.render();
	
	var chart3 = new CanvasJS.Chart("chartContainer3", {
		width:230,
		height:230,
		theme: "light2",
		animationEnabled: true,
		title:{
			text: "제주공항",
			horizontalAlign: "center"
		},
		data: [{
			type: "doughnut",
			indexLabelPlacement: "inside",
			showInLegend: true,
			startAngle: 60,
			//innerRadius: 60,
			indexLabelFontSize: 17,
			indexLabelFontColor: "black",
			indexLabel: "{label} #percent%",
			toolTipContent: "<b>{label}:</b> {y} (#percent%)",
			dataPoints: [
				{ y: 67, label: "점유", name: "점유" },
				{ y: 33, label: "가능", name: "가능" }
			]
		}]
	});
	chart3.render();
	
	var chart4 = new CanvasJS.Chart("chartContainer4", {
		width:230,
		height:230,
		theme: "light2",
		animationEnabled: true,
		title:{
			text: "제주시청",
			horizontalAlign: "center"
		},
		data: [{
			type: "doughnut",
			indexLabelPlacement: "inside",
			showInLegend: true,
			startAngle: 60,
			//innerRadius: 60,
			indexLabelFontSize: 17,
			indexLabelFontColor: "black",
			indexLabel: "{label} #percent%",
			toolTipContent: "<b>{label}:</b> {y} (#percent%)",
			dataPoints: [
				{ y: 10, label: "점유", name: "점유" },
				{ y: 90, label: "가능", name: "가능" }
			]
		}]
	});
	chart4.render();
	
	var chart5 = new CanvasJS.Chart("chartContainer5", {
		theme: "light2",
		animationEnabled: true,
		title:{
			text: "주차장 사용율 예측 데이터, 1시간"
		},	
		axisY: {
			title: "출입",
			titleFontColor: "#4F81BC",
			lineColor: "#4F81BC",
			labelFontColor: "#4F81BC",
			tickColor: "#4F81BC"
		},
		axisY2: {
			title: "출차",
			titleFontColor: "#C0504E",
			lineColor: "#C0504E",
			labelFontColor: "#C0504E",
			tickColor: "#C0504E"
		},	
		toolTip: {
			shared: true
		},
		legend: {
			cursor:"pointer",
			itemclick: toggleDataSeries
		},
		data: [{
			type: "column",
			name: "출입",
			legendText: "출입",
			showInLegend: true, 
			dataPoints:[
				{ label: "인제공영", y: 26 },
				{ label: "제주공항", y: 30 },
				{ label: "제주시청", y: 15 }
			]
		},
		{
			type: "column",	
			name: "출차",
			legendText: "출차",
			axisYType: "secondary",
			showInLegend: true,
			dataPoints:[
				{ label: "인제공영", y: 10 },
				{ label: "제주공항", y: 27 },
				{ label: "제주시청", y: 99 }				
			]
		}]
	});
	chart5.render();

	function toggleDataSeries(e) {
		if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
			e.dataSeries.visible = false;
		}
		else {
			e.dataSeries.visible = true;
		}
		chart5.render();
	}
	
	var chart6 = new CanvasJS.Chart("chartContainer6", {
		animationEnabled: true,
		theme: "light2",
		title:{
			text: "기간별 주차율 예측 데이터"
		},
		axisY:{
			includeZero: false
		},
		data: [{        
			type: "line",       
			dataPoints: [
				{ y: 450 },
				{ y: 414},
				{ y: 520, indexLabel: "highest",markerColor: "red", markerType: "triangle" },
				{ y: 460 },
				{ y: 450 },
				{ y: 500 },
				{ y: 480 },
				{ y: 480 },
				{ y: 410 , indexLabel: "lowest",markerColor: "DarkSlateGrey", markerType: "cross" },
				{ y: 500 },
				{ y: 480 },
				{ y: 510 }
			]
		}]
	});
	chart6.render();
	
	chart7 = new CanvasJS.Chart("chartContainer7", {
		width:950,
		height:300,
		animationEnabled: true,
		theme: "light2",
		axisY:{
			includeZero: false
		},
		data: [{        
			type: "line",
			showInLegend: true,
			legendText: "온도",
			dataPoints: [
			]
		},
		{        
			type: "line",
			showInLegend: true,
			legendText: "습도",
			dataPoints: [
			]
		}]
	});
	chart7.render();
	
	///////////// set canvasjs style ///////////////////
	$('.canvasjs-chart-canvas').css("box-shadow", "1px 1px 1px #000");
	
	//////////// DB page radio check event ////////////////////
	$('#collectionKeys').prop('disabled', true);
    $('#searchText').prop('disabled', true);
    	
    $('#sDate').prop('disabled', true);
    $('#eDate').prop('disabled', true);
	
	$('input[type=radio][name=searchMode]').change(function() {
        if (this.value == 'term') {
        	searchModeVal = 0;
        	currentDBPage = 0;
        	$('#collectionKeys').prop('disabled', true);
        	$('#searchText').prop('disabled', true);
        	
        	$('#sDate').prop('disabled', false);
        	$('#eDate').prop('disabled', false);
        }
        else if (this.value == 'key') {
        	searchModeVal = 1;
        	currentDBPage = 0;
        	$('#sDate').prop('disabled', true);
        	$('#eDate').prop('disabled', true);
        	
        	$('#collectionKeys').prop('disabled', false);
        	$('#searchText').prop('disabled', false);
        }
    });
	
	////////////// DB page select date selected check event /////////////////
	$( "#selGraphByDate" ).change(function() {
		$( "#selGraphByDate option:selected" ).each(function() {
			resetChart7();
			
			for(var i = 0; i < parsedAggData.length; i++) {
				var indate = parsedAggData[i].indate;
				
				if($(this).text() == indate) {
					var tmpData = parsedAggData[i].t1h;
			    	tmpData *= 1;	// 문자열을 숫자로 형변환
			    	chart7.options.data[0].dataPoints.push({y: tmpData, label:parsedAggData[i].intime});
			    	tmpData = parsedAggData[i].reh;
			    	tmpData *= 1;
			    	chart7.options.data[1].dataPoints.push({y: tmpData, label:parsedAggData[i].intime});
				}
			}
        
    		chart7.render();
		});
	});
	
});

function getData() {
	$("body").mLoading({
		  text:"Loading data...",
	});
	
	// "/pkd/getManage/{startDate}/{endDate}"
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/pkd/getManage/" + $('#getDataSDate').val() + "/" + $('#getDataEDate').val(),
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
        	console.log("SUCCESS : ", data);
        	$("body").mLoading('hide');
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        	$("body").mLoading('hide');
        }
    });
}

function goWithUpload() {
	$("body").mLoading({
		  text:"Uploading...",
	});
}

function selectMenu(myObj) {
	$(myObj).siblings().removeClass("w3-blue");
	$(myObj).addClass("w3-blue");
	
	var selectedMenu = $(myObj).data("menu");
	
	switch(selectedMenu) {
		case 1 :
			$('#content1').siblings().css("display", "none");
			$('#content1').css("display", "block");
			$('#topMenu').css("display", "block");
			console.log("clicked Overview!");
			break;
		case 2 :
			$('#content2').siblings().css("display", "none");
			$('#content2').css("display", "block");
			$('#topMenu').css("display", "block");
			console.log("clicked Upload!");
			break;
		case 3 :
			$('#content3').siblings().css("display", "none");
			$('#content3').css("display", "block");
			$('#topMenu').css("display", "block");
			console.log("clicked Database!");
			getDBInfo();
			getCollectionNames();
			//getCollectionInfo("parkingZone");
			//getCollectionKeys("parkingZone");
			break;
		case 4 :
			$('#content4').siblings().css("display", "none");
			$('#content4').css("display", "block");
			$('#topMenu').css("display", "block");
			console.log("clicked Parking!");
			currentPage = 0;
			getParkingZoneJson(currentPage);
			break;
		case 5 :
			$('#content5').siblings().css("display", "none");
			$('#content5').css("display", "block");
			$('#topMenu').css("display", "block");
			console.log("clicked Upload!");
			break;
		default :
			break;
	}
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function goPrevSearch() {
	if(0 == currentDBPage) {
		currentDBPage = 0;
	} else {
		currentDBPage = currentDBPage - 1;
	}
	
	$('#currentPage').html((currentDBPage + 1) + "/" + totalDBPage);
	goMongoSearch();
}

function goNextSearch() {
	if(totalDBPage == currentDBPage + 1) {
		currentDBPage = currentDBPage;
	} else {
		currentDBPage = currentDBPage + 1;	
	}
	
	$('#currentPage').html((currentDBPage + 1) + "/" + totalDBPage);
	goMongoSearch();
}

function resetChart7() {
	chart7.destroy();
	chart7 = new CanvasJS.Chart("chartContainer7", {
		width:950,
		height:300,
		animationEnabled: true,
		theme: "light2",
		axisY:{
			includeZero: false
		},
		data: [{        
			type: "line",
			showInLegend: true,
			legendText: "온도",
			dataPoints: [
			]
		},
		{        
			type: "line", 
			showInLegend: true,
			legendText: "습도",
			dataPoints: [
			]
		}]
	});

	///////////// set canvasjs style ///////////////////
	$('.canvasjs-chart-canvas').css("box-shadow", "1px 1px 1px #000");
}

function getPZTermTotalCount(sdateVal, edateVal) {
	var totalCount = 0;
		
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/pz/term/" + sdateVal + "/" + edateVal + "/tt" ,
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
        	//console.log("SUCCESS : ", data);
        	totalCount = data;
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
	
	return totalCount;
}

function getPDTermTotalCount(sdateVal, edateVal) {
	var totalCount = 0;
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/pd/term/" + sdateVal + "/" + edateVal + "/tt" ,
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
        	//console.log("SUCCESS : ", data);
        	totalCount = data;
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
	
	return totalCount;
}

function getIDTermTotalCount(sdateVal, edateVal) {
	var totalCount = 0;
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/id/term/" + sdateVal + "/" + edateVal + "/tt" ,
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
        	//console.log("SUCCESS : ", data);
        	totalCount = data;
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
	
	return totalCount;
}

function setPageByTerm(dbName, sdateVal, edateVal) {
	var totalC = 0;
	switch(dbName) {
		case 'pz' :
			totalC = getPZTermTotalCount(sdateVal, edateVal);
			break;
		case 'pd' :
			totalC = getPDTermTotalCount(sdateVal, edateVal);
			break;
		case 'id' :
			totalC = getIDTermTotalCount(sdateVal, edateVal);
			break;
		default :
			break;
	}
	
	if(10 > totalC) {
		totalDBPage = 1;	
	} else {
		totalDBPage = Math.round(totalC / 10);	
	}
	
	$('#currentPage').html((currentDBPage + 1) + "/" + totalDBPage);
}

function searchByTerm() {
	var sDateTerm = $('#sDate').val();
	var eDateTerm = $('#eDate').val();
	
	var urlVal = "";
	
	if("" == sDateTerm || "" == eDateTerm) {
		alert("검색기간을 선택해 주세요.");
		return;
	}
	
	// 콜렉션에 따라서 검색조건으로 들어갈 key값을 바꿔준다
	switch(currentSelectedColl) {
		case 'parkingZone' :
			urlVal = "/pz/";
			break;
		case 'publicData' :
			urlVal = "/pd/";
			break;
		case 'iotData' :
			urlVal = "/id/";
			break;
		default :
			break;
	}
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: urlVal + "term/" + sDateTerm + "/" + eDateTerm + "/" + currentDBPage,
        dataType: 'json',
        cache: false,
        success: function (data) {
            //console.log("SUCCESS : ", data);
            
            switch(currentSelectedColl) {
	            case 'parkingZone' :
	            	var tmpHTML = "<tr class='w3-green'>" +
									"<th>관리번호</th>" +
					  				"<th>주차장명</th>" +
					  				"<th>도로명주소</th>" +
					  				"<th>구분</th>" +
					  				"<th>요금정보</th>" +
					  				"<th>운영요일</th>" +
					  				"<th>유형</th>" +
								   "</tr>";
				    for(var i = 0; i < data.length; i++) {
				    	tmpHTML += "<tr>" +
				    				 "<td>" + data[i].no + "</td>" +
				    				 "<td>" + data[i].name + "</td>" +
				    				 "<td>" + data[i].addr_road + "</td>" +
				    				 "<td>" + data[i].division + "</td>" +
				    				 "<td>" + data[i].fee_info + "</td>" +
				    				 "<td>" + data[i].op_date + "</td>" +
				    				 "<td>" + data[i].type + "</td>" +
				    			   "</tr>";
				    }
				    $('#docList').append(tmpHTML);
				    
				    setPageByTerm("pz", sDateTerm, eDateTerm);
	            	break;
	            case 'publicData' :
	            	resetChart7();
	            	
	            	var tmpHTML = "<tr class='w3-green'>" +
									"<th>데이터날짜</th>" +
					  				"<th>주소</th>" +
					  				"<th>날씨기준날짜</th>" +
					  				"<th>온도</th>" +
					  				"<th>습도</th>" +
					  				"<th>대기기준날짜</th>" +
					  				"<th>미세먼지</th>" +
					  				"<th>초미세먼지</th>" +
					  				"<th>오존지수</th>" +
								   "</tr>";
				    for(var i = 0; i < data.length; i++) {
				    	tmpHTML += "<tr>" +
				    	  			"<td>"+ data[i].indate + "</td>" +
				    	  			"<td>"+ data[i].address + "</td>" +
				    	  			"<td>"+ data[i].weather.basedate + " " + data[i].weather.basetime + "</td>" +
				    	  			"<td>"+ data[i].weather.t1h + "</td>" +
				    	  			"<td>"+ data[i].weather.reh + "</td>" +
				    	  			"<td>"+ data[i].airstatus.dataTime + "</td>" +
				    	  			"<td>"+ data[i].airstatus.pm10Value + "</td>" +
				    	  			"<td>"+ data[i].airstatus.pm25Value + "</td>" +
				    	  			"<td>"+ data[i].airstatus.o3Value + "</td>" +
				    			   "</tr>";
				    			   
				    	var tmpData = data[i].weather.t1h;
				    	tmpData *= 1;	// 문자열을 숫자로 형변환
				    	chart7.options.data[0].dataPoints.push({x: i, y: tmpData});
				    	tmpData = data[i].weather.reh;
				    	tmpData *= 1;
				    	chart7.options.data[1].dataPoints.push({x: i, y: tmpData});
				    }
				    $('#docList').append(tmpHTML);
	                
                	chart7.render();	
                	
                	setPageByTerm("pd", sDateTerm, eDateTerm);
                	
                	// get mongodb aggregation data
                	getAggData("pd", sDateTerm, eDateTerm);
                	
	            	break;
	            case 'iotData' :
	            	resetChart7();
	            	
	            	var tmpHTML = "<tr class='w3-green'>" +
									"<th>데이터날짜</th>" +
					  				"<th>dust</th>" +
					  				"<th>air</th>" +
					  				"<th>온도</th>" +
					  				"<th>습도</th>" +
								   "</tr>";
				    for(var i = 0; i < data.length; i++) {
				    	tmpHTML += "<tr>" +
				    	  			"<td>"+ data[i].date + "</td>" +
				    	  			"<td>"+ data[i].data.dust + "</td>" +
				    	  			"<td>"+ data[i].data.air + "</td>" +
				    	  			"<td>"+ data[i].data.temperature + "</td>" +
				    	  			"<td>"+ data[i].data.humidity + "</td>" +
				    			   "</tr>";
				    			   
				    	var tmpData = data[i].data.temperature;
				    	tmpData *= 1;	// 문자열을 숫자로 형변환
				    	chart7.options.data[0].dataPoints.push({x: i, y: tmpData});
				    	tmpData = data[i].data.humidity;
				    	tmpData *= 1;
				    	chart7.options.data[1].dataPoints.push({x: i, y: tmpData});
				    }
				    $('#docList').append(tmpHTML);
	                
                	chart7.render();	
                	
                	setPageByTerm("id", sDateTerm, eDateTerm);
                	
                	// get mongodb aggregation data
                	getAggData("id", sDateTerm, eDateTerm);
                	
	            	break;
            	default :
            		break;
            }
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
}

function getAggData(clName, sDateTerm, eDateTerm) {	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/" + clName + "/term/" + sDateTerm + "/" + eDateTerm + "/ag" ,
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
        	//console.log("getAggData : ", data);
        	parseAggData(clName, data);
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
}

function parseAggData(clName, data) {
	
	parsedAggData = [];
	
	for(var i = 0; i < data.length; i++) {
		var tempParsed = new Object();
		
		switch(clName) {
		case "pd" :
			tempParsed.indate = data[i].indate.substr(0, 10);
			tempParsed.intime = data[i].indate.substr(11, 19);
			tempParsed.t1h = data[i].weather.t1h;
			tempParsed.reh = data[i].weather.reh;
			break;
		case "id" :
			tempParsed.indate = data[i].date.substr(0, 10);
			tempParsed.intime = data[i].date.substr(11, 19);
			tempParsed.t1h = data[i].data.temperature;
			tempParsed.reh = data[i].data.humidity;
			break;
		default :
			break;
		}
		
		parsedAggData.push(tempParsed);
	}
	
	//console.log("parseAggData : ", parsedAggData);
	
	setSelectBox(parsedAggData);
}

function setSelectBox(parsedAggData) {
	$('#selGraphByDate').children().remove();
	var tmpSavedDate = ""
	
	for(var i = 0; i < parsedAggData.length; i++) {
		var indate = parsedAggData[i].indate;
		
		if("" == tmpSavedDate) {
			tmpHTML = "<option value='" + indate + "'>" + indate + "</option>"
			$('#selGraphByDate').append(tmpHTML);
			
			tmpSavedDate = indate;
		} else {
			if(tmpSavedDate == indate) {
				continue;
			} else {
				tmpHTML = "<option value='" + indate + "'>" + indate + "</option>"
				$('#selGraphByDate').append(tmpHTML);
				
				tmpSavedDate = indate;
			}
		}
	}
}

function getPZTotalCount(keyVal, valueVal) {
	var totalCount = 0;
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/pz/key/" + keyVal + "/" + valueVal + "/tt" ,
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
        	//console.log("SUCCESS : ", data);
        	totalCount = data;
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
	
	return totalCount;
}

function getPDTotalCount(keyVal, valueVal) {
	var totalCount = 0;
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/pd/key/" + keyVal + "/" + valueVal + "/tt" ,
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
        	//console.log("SUCCESS : ", data);
        	totalCount = data;
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
	
	return totalCount;
}

function getIDTotalCount(keyVal, valueVal) {
	var totalCount = 0;
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/id/key/" + keyVal + "/" + valueVal + "/tt" ,
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
        	//console.log("SUCCESS : ", data);
        	totalCount = data;
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
	
	return totalCount;
}

function setPageByKey(dbName, keyVal, valueVal) {
	var totalC = 0;
	switch(dbName) {
		case 'pz' :
			totalC = getPZTotalCount(keyVal, valueVal);
			break;
		case 'pd' :
			totalC = getPDTotalCount(keyVal, valueVal);
			break;
		case 'id' :
			totalC = getIDTotalCount(keyVal, valueVal);
			break;
		default :
			break;
	}
	
	if(10 > totalC) {
		totalDBPage = 1;	
	} else {
		totalDBPage = Math.round(totalC / 10);	
	}
	
	$('#currentPage').html((currentDBPage + 1) + "/" + totalDBPage);
}

function searchByKey() {
	var colKey = $('#collectionKeys').val();
	var searchStr = $.trim($('#searchText').val());
	var urlVal = "";
	
	if("" == searchStr) {
		alert("검색어를 입력해 주세요.");
		return;
	}
	
	switch(currentSelectedColl) {
		case 'parkingZone' :
			urlVal = "/pz/";
			break;
		case 'publicData' :
			urlVal = "/pd/";
			break;
		case 'iotData' :
			urlVal = "/id/";
			break;
		default :
			break;
	}
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: urlVal + "key/" + colKey + "/" + searchStr + "/" + currentDBPage,
        dataType: 'json',
        cache: false,
        success: function (data) {
            //console.log("SUCCESS : ", data);
            
            switch(currentSelectedColl) {
	            case 'parkingZone' :
	                var tmpHTML = "<tr class='w3-green'>" +
									"<th>관리번호</th>" +
					  				"<th>주차장명</th>" +
					  				"<th>도로명주소</th>" +
					  				"<th>구분</th>" +
					  				"<th>요금정보</th>" +
					  				"<th>운영요일</th>" +
					  				"<th>유형</th>" +
								   "</tr>";
	                for(var i = 0; i < data.length; i++) {
	                	tmpHTML += "<tr>" +
	                				 "<td>" + data[i].no + "</td>" +
	                				 "<td>" + data[i].name + "</td>" +
	                				 "<td>" + data[i].addr_road + "</td>" +
	                				 "<td>" + data[i].division + "</td>" +
	                				 "<td>" + data[i].fee_info + "</td>" +
	                				 "<td>" + data[i].op_date + "</td>" +
	                				 "<td>" + data[i].type + "</td>" +
	                			   "</tr>";
	                }
	                $('#docList').append(tmpHTML);
	                
	                setPageByKey("pz", colKey, searchStr);
	            	break;
	            case 'publicData' :
	            	resetChart7();
	            	
					var tmpHTML = "<tr class='w3-green'>" +
									"<th>데이터날짜</th>" +
					  				"<th>주소</th>" +
					  				"<th>날씨기준날짜</th>" +
					  				"<th>온도</th>" +
					  				"<th>습도</th>" +
					  				"<th>대기기준날짜</th>" +
					  				"<th>미세먼지</th>" +
					  				"<th>초미세먼지</th>" +
					  				"<th>오존지수</th>" +
								   "</tr>";
	                for(var i = 0; i < data.length; i++) {
	                	tmpHTML += "<tr>" +
	                	  			"<td>"+ data[i].indate + "</td>" +
	                	  			"<td>"+ data[i].address + "</td>" +
	                	  			"<td>"+ data[i].weather.basedate + " " + data[i].weather.basetime + "</td>" +
	                	  			"<td>"+ data[i].weather.t1h + "</td>" +
	                	  			"<td>"+ data[i].weather.reh + "</td>" +
	                	  			"<td>"+ data[i].airstatus.dataTime + "</td>" +
	                	  			"<td>"+ data[i].airstatus.pm10Value + "</td>" +
	                	  			"<td>"+ data[i].airstatus.pm25Value + "</td>" +
	                	  			"<td>"+ data[i].airstatus.o3Value + "</td>" +
	                			   "</tr>";
	                			   
	                	var tmpData = data[i].weather.t1h;
				    	tmpData *= 1;	// 문자열을 숫자로 형변환
				    	chart7.options.data[0].dataPoints.push({x: i, y: tmpData});
				    	tmpData = data[i].weather.reh;
				    	tmpData *= 1;
				    	chart7.options.data[1].dataPoints.push({x: i, y: tmpData});
	                }
	                $('#docList').append(tmpHTML);
	                
                	chart7.render();
	                
	                setPageByKey("pd", colKey, searchStr);
	                
	                getAggDataByKey("pd", colKey, searchStr)
	            	break;
	            case 'iotData' :
	            	resetChart7();
	            	
	            	var tmpHTML = "<tr class='w3-green'>" +
									"<th>데이터날짜</th>" +
					  				"<th>dust</th>" +
					  				"<th>air</th>" +
					  				"<th>온도</th>" +
					  				"<th>습도</th>" +
								   "</tr>";
					
	                for(var i = 0; i < data.length; i++) {
	                	tmpHTML += "<tr>" +
	                	  			"<td>"+ data[i].date + "</td>" +
	                	  			"<td>"+ data[i].data.dust + "</td>" +
	                	  			"<td>"+ data[i].data.air + "</td>" +
	                	  			"<td>"+ data[i].data.temperature + "</td>" +
	                	  			"<td>"+ data[i].data.humidity + "</td>" +
	                			   "</tr>";
	                			   
	                	var tmpData = data[i].data.temperature;
				    	tmpData *= 1;	// 문자열을 숫자로 형변환
				    	chart7.options.data[0].dataPoints.push({x: i, y: tmpData});
				    	tmpData = data[i].data.humidity;
				    	tmpData *= 1;
				    	chart7.options.data[1].dataPoints.push({x: i, y: tmpData});
	                }
	                $('#docList').append(tmpHTML);
	                
                	chart7.render();
	                
	                setPageByKey("id", colKey, searchStr);
	                
	                getAggDataByKey("id", colKey, searchStr)
	            	break;
            	default :
            		break;
            }
            
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
}

function getAggDataByKey(clName, keyVal, searchStrVal) {
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/" + clName + "/key/" + keyVal + "/" + searchStrVal + "/ag" ,
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
        	console.log("getAggDataByKey : ", data);
        	parseAggData(clName, data);
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
}

function goMongoSearch() {
	$('#docList').children().remove();
	
	switch(searchModeVal) {
		case 0 :
			searchByTerm();
			break;
		case 1 :
			searchByKey();
			break;
		default :
			break;
	}
}

// Get Collection keys
function getCollectionKeys(collectionName) {
	
	$('#collectionKeys').children().remove();
	 
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/pz/cl/" + collectionName + "/keys",
        dataType: 'json',
        cache: false,
        success: function (data) {
            //console.log("SUCCESS : ", data);
            
            var tmpHTML = "";
            for(var i = 0; i < data.length; i++) {
            	tmpHTML += "<option value='" + data[i] + "'>" + data[i] + "</option>";
            }
            $('#collectionKeys').append(tmpHTML);
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
}

// Get Collection info
function getCollectionInfo(collectionName) {
	
	$('#clInfoList').children().remove();
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/pz/cl/" + collectionName + "/stats",
        dataType: 'json',
        cache: false,
        success: function (data) {
            //console.log("SUCCESS : ", data);
            
            tmpHTML = "<p>Collections : " + data.ns + "</p>" + 
  		  				"<p>Count : " + data.count + "</p>" +
  		  				"<p>Size : " + numberWithCommas(data.size) + "Bytes</p>" +
  		  				"<p>Storage size : " + numberWithCommas(data.storageSize) + "Bytes</p>" +
  		  				"<p>Indexes : " + data.nindexes + "</p>" +
  		  				"<p>Total Index Size : " + numberWithCommas(data.totalIndexSize) + "Bytes</p>";
            $('#clInfoList').append(tmpHTML);
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
}

// Select Collection names
function selectCollect(myObj) {
	currentSelectedColl = $(myObj).html();
	getCollectionInfo(currentSelectedColl);
	getCollectionKeys(currentSelectedColl);
	
	$('#sDate').prop('disabled', false);
    $('#eDate').prop('disabled', false);
    
    $('#docList').children().remove();
    
    $('#currentPage').html((currentDBPage + 1) + "/" + totalDBPage);
}

// Get Collection names
function getCollectionNames() {
	
	$('#collectionNamesList').children().remove();
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/pz/cl",
        dataType: 'json',
        cache: false,
        success: function (data) {
            //console.log("SUCCESS : ", data);
            
            var tmpHTML = "";
            
            for(var i = 0; i < data.length; i++) {
            	tmpHTML += "<h6 onclick='selectCollect(this)'>" + data[i] + "</h6>";
            }
            
            $('#collectionNamesList').append(tmpHTML);
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
}

// Get Parking DB info
function getDBInfo() {
	
	$('#dbInfoName').children().remove();
	$('#dbInfoList').children().remove();
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/pz/db/stats",
        dataType: 'json',
        cache: false,
        success: function (data) {
            //console.log("SUCCESS : ", data);
            
            var tmpHTML = "<h6>" + data.db + "</h6>";
            
            $('#dbInfoName').append(tmpHTML);
            
            tmpHTML = "<p>Collections : " + data.collections + "</p>" + 
            		  "<p>Objects : " + data.objects + "</p>" +
            		  "<p>Data size : " + numberWithCommas(data.dataSize) + "Bytes</p>" +
            		  "<p>Storage size : " + numberWithCommas(data.storageSize) + "Bytes</p>" +
            		  "<p>indexes : " + data.indexes + "</p>" +
            		  "<p>index size : " + numberWithCommas(data.indexSize) + "Bytes</p>";
            
            $('#dbInfoList').append(tmpHTML);
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
}

// Get parkingZone json
function getParkingZoneJson(page) {
	$('#parkingList').children().remove();
	
	var tmpHTML = "<tr class='w3-blue'><th>주차장관리번호</th><th>주차장명</th><th>주차장도로명주소</th><th>주차장구분</th><th>요금정보</th></tr>";
	$('#parkingList').append(tmpHTML);
	
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/pz/page/" + page,
        //data: page,
        dataType: 'json',
        cache: false,
        success: function (data) {
            //console.log("SUCCESS : ", data);
            for(var i=0; i<data.length; i++) {
            	var tmpHTML = "<tr><td>" + data[i].no + "</td><td>" + data[i].name + "</td><td>" + data[i].addr_road + "</td><td>" +
            	              data[i].division + "</td><td>" + data[i].fee_info + "</td></tr>";
            	$('#parkingList').append(tmpHTML);
            }
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });
}

// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
    if (mySidebar.style.display === 'block') {
        mySidebar.style.display = 'none';
        overlayBg.style.display = "none";
    } else {
        mySidebar.style.display = 'block';
        overlayBg.style.display = "block";
    }
}

// Close the sidebar with the close button
function w3_close() {
    mySidebar.style.display = "none";
    overlayBg.style.display = "none";
}

function myFunc(id) {
    var x = document.getElementById(id);
    if (x.classList) { 
        x.classList.toggle("w3-show");
    } else {
    // Fallback for IE9 and earlier
    if (x.className.indexOf("w3-show") == -1) 
        x.className = x.className + " w3-show";
    else 
        x.className = x.className.replace("w3-show", "");
    }
}


</script>

</body>
</html>
