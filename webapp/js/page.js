//pageNo 当前页
//totalRecords 总条数
//returnNum	每页数
function genPageTag1(pageNo,totalRecords,returnNum,divId){
	
//	alert("pageNo="+pageNo+",totalRecords="+totalRecords+",returnNum="+returnNum);
	var yu = totalRecords%returnNum;
	var zs = parseInt(totalRecords/returnNum);
	if(yu > 0){
		zs +=1;
	}
	if(zs == 0)
		zs = 1;
	pageBar1(pageNo,zs,totalRecords,divId,returnNum); 
}


//	pageNo 当前页
//	totalPage 总页数
//	totalRecords 总条数
function pageBar1(pageNo,totalPage,totalRecords,divId,returnNum){
	if(!pageNo){
		pageNo = 1;
	}
	var allPages = 0;//总页数
	var count = totalRecords;
	if( count != 0 ) {
		if( count % 10 == 0 ) {
			allPages = count/returnNum;
		} else {
			allPages = Math.floor(count/returnNum)+1;
		}
	}
	
	var html = "<ul>";
	html +="<li class='disabled'><a href='javascript:void(0)'>"+pageNo+"/"+allPages+"</a></li>";
	if(pageNo == 1){
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='icon-step-backward'></i></a></li>";
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='icon-backward'></i></a></li>";
	}else{
		html +="<li><a onclick=\"searchData1(1)\" href='javascript:void(0)'><i class='icon-step-backward'></i></a></li>";
		html +="<li><a onclick=\"previousPage1()\" href='javascript:void(0)'><i class='icon-backward'></i></a></li>";
	}
	if(pageNo  == 0) {pageNo++;}
	for(var i = 0; i < allPages; i ++) {
		var flowNumber = (i + 1);	// 当前正在循环的页数
		if(flowNumber == pageNo) {		// 如果是点击的页数，则将class设置为active，用以标识选中
			html += "<li class='active'><a herf=\"javascript:void(0)\"";
		}else{
			html += "<li><a herf=\"javascript:void(0)\"  onclick=\"goPage1("+flowNumber+")\"";	// IE8没有this.text属性
			
		}
		if(allPages > 8) { // 只显示8页，两边的隐藏
			if(pageNo <= 5) {
				if(flowNumber > 8) {
					html += " style=\"display:none\"";
				}
			} else if(pageNo > (allPages - 5)) {
				if(flowNumber < (allPages - 8)) {
					html += " style=\"display:none\"";
				}
			} else {
				if((pageNo - flowNumber) > 4 || (flowNumber - pageNo) > 5) {
					html += " style=\"display:none\"";
				}
			}
		}
		html +=	">" + flowNumber + "</a></li>";
	}
	if(pageNo == allPages){
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='icon-forward'></i></a></li>";
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='icon-step-forward'></i></a></li>";
	}else{
		if(allPages > 8) { 
			html += "<li><a herf=\"javascript:void(0)\">" + "..." + "</a></li>";
		}
		html +="<li><a onclick=\"nextPage1("+allPages+")\" href='javascript:void(0)'><i class='icon-forward'></i></a></li>";
		html +="<li><a onclick=\"searchData1("+allPages+")\" href='javascript:void(0)'><i class='icon-step-forward'></i></a></li>";
	}
	
	html +="<li>";
	html +="<select style='height:26px; width: 63px;' onchange='selectPage1(this.value,"+pageNo+")'>";
	if(returnNum == 10){
		html +="    <option selected='selected' value=\"10\">10</option>";
	}else{
		html +="    <option value=\"10\">10</option>";
	}
	if(returnNum == 15){
		html +="    <option selected='selected' value=\"15\">15</option>";
	}else{
		html +="    <option value=\"15\">15</option>";
	}
	if(returnNum == 50){
		html +="    <option selected='selected' value=\"50\">50</option>";
	}else{
		html +="    <option value=\"50\">50</option>";
	}
	if(returnNum == 100){
		html +="    <option selected='selected' value=\"100\">100</option>";
	}else{
		html +="    <option value=\"100\">100</option>";
	}
	if(returnNum == 200){
		html +="    <option selected='selected' value=\"200\">200</option>";
	}else{
		html +="    <option value=\"200\">200</option>";
	}
	html +="</select>";
	html += "</li>";
	html += "</ul>";
	
	$("#"+divId).html(html);
//	$("html").getNiceScroll().resize();
}
function selectPage1(pageNumber,pageNo) {
	$("#returnNum1").val(pageNumber);
	goPage1(pageNo);
}
/** 前往指定页面 */
function goPage1(pageNumber) {
	$("#currPage1").val(pageNumber);
	searchData1(pageNumber);
}
/** 上一页 */
function previousPage1(){ 
	var pageNumber = parseInt($("#currPage1").val());
	if(pageNumber == 1) {
		return;
	}
	$("#currPage1").val(pageNumber-1);
	searchData1(pageNumber - 1);
}


/** 下一页 */
function nextPage1(allPages){
	var pageNumber = parseInt($("#currPage1").val());
	if(pageNumber == allPages) {
		return;
	}
	$("#currPage1").val(pageNumber+1);
	searchData1(pageNumber + 1);
}
//=======================================================================
//pageNo 当前页
//totalRecords 总条数
//returnNum	每页数
function genPageTag2(pageNo,totalRecords,returnNum,divId){
	
//	alert("pageNo="+pageNo+",totalRecords="+totalRecords+",returnNum="+returnNum);
	var yu = totalRecords%returnNum;
	var zs = parseInt(totalRecords/returnNum);
	if(yu > 0){
		zs +=1;
	}
	if(zs == 0)
		zs = 1;
	pageBar2(pageNo,zs,totalRecords,divId,returnNum); 
}


//	pageNo 当前页
//	totalPage 总页数
//	totalRecords 总条数
function pageBar2(pageNo,totalPage,totalRecords,divId,returnNum){
	if(!pageNo){
		pageNo = 1;
	}
	var allPages = 0;//总页数
	var count = totalRecords;
	if( count != 0 ) {
		if( count % 10 == 0 ) {
			allPages = count/returnNum;
		} else {
			allPages = Math.floor(count/returnNum)+1;
		}
	}
	
	var html = "<ul>";
	html +="<li class='disabled'><a href='javascript:void(0)'>"+pageNo+"/"+allPages+"</a></li>";
	if(pageNo == 1){
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='icon-step-backward'></i></a></li>";
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='icon-backward'></i></a></li>";
	}else{
		html +="<li><a onclick=\"searchData2(1)\" href='javascript:void(0)'><i class='icon-step-backward'></i></a></li>";
		html +="<li><a onclick=\"previousPage2()\" href='javascript:void(0)'><i class='icon-backward'></i></a></li>";
	}
	if(pageNo  == 0) {pageNo++;}
	for(var i = 0; i < allPages; i ++) {
		var flowNumber = (i + 1);	// 当前正在循环的页数
		if(flowNumber == pageNo) {		// 如果是点击的页数，则将class设置为active，用以标识选中
			html += "<li class='active'><a herf=\"javascript:void(0)\"";
		}else{
			html += "<li><a herf=\"javascript:void(0)\"  onclick=\"goPage2("+flowNumber+")\"";	// IE8没有this.text属性
			
		}
		if(allPages > 8) { // 只显示8页，两边的隐藏
			if(pageNo <= 5) {
				if(flowNumber > 8) {
					html += " style=\"display:none\"";
				}
			} else if(pageNo > (allPages - 5)) {
				if(flowNumber < (allPages - 8)) {
					html += " style=\"display:none\"";
				}
			} else {
				if((pageNo - flowNumber) > 4 || (flowNumber - pageNo) > 5) {
					html += " style=\"display:none\"";
				}
			}
		}
		html +=	">" + flowNumber + "</a></li>";
	}
	if(pageNo == allPages){
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='icon-forward'></i></a></li>";
		html +="<li class='disabled'><a href='javascript:void(0)'><i class='icon-step-forward'></i></a></li>";
	}else{
		if(allPages > 8) { 
			html += "<li><a herf=\"javascript:void(0)\">" + "..." + "</a></li>";
		}
		html +="<li><a onclick=\"nextPage2("+allPages+")\" href='javascript:void(0)'><i class='icon-forward'></i></a></li>";
		html +="<li><a onclick=\"searchData2("+allPages+")\" href='javascript:void(0)'><i class='icon-step-forward'></i></a></li>";
	}
	
	html +="<li>";
	html +="<select style='height:26px; width: 63px;' onchange='selectPage2(this.value,"+pageNo+")'>";
	if(returnNum == 10){
		html +="    <option selected='selected' value=\"10\">10</option>";
	}else{
		html +="    <option value=\"10\">10</option>";
	}
	if(returnNum == 15){
		html +="    <option selected='selected' value=\"15\">15</option>";
	}else{
		html +="    <option value=\"15\">15</option>";
	}
	if(returnNum == 50){
		html +="    <option selected='selected' value=\"50\">50</option>";
	}else{
		html +="    <option value=\"50\">50</option>";
	}
	if(returnNum == 100){
		html +="    <option selected='selected' value=\"100\">100</option>";
	}else{
		html +="    <option value=\"100\">100</option>";
	}
	if(returnNum == 200){
		html +="    <option selected='selected' value=\"200\">200</option>";
	}else{
		html +="    <option value=\"200\">200</option>";
	}
	html +="</select>";
	html += "</li>";
	html += "</ul>";
	
	$("#"+divId).html(html);
//	$("html").getNiceScroll().resize();
}
function selectPage2(pageNumber,pageNo) {
	$("#returnNum2").val(pageNumber);
	goPage2(pageNo);
}
/** 前往指定页面 */
function goPage2(pageNumber) {
	$("#currPage2").val(pageNumber);
	searchData2(pageNumber);
}
/** 上一页 */
function previousPage2(){ 
	var pageNumber = parseInt($("#currPage2").val());
	if(pageNumber == 1) {
		return;
	}
	$("#currPage2").val(pageNumber-1);
	searchData2(pageNumber - 1);
}


/** 下一页 */
function nextPage2(allPages){
	var pageNumber = parseInt($("#currPage2").val());
	if(pageNumber == allPages) {
		return;
	}
	$("#currPage2").val(pageNumber+1);
	searchData2(pageNumber + 1);
}