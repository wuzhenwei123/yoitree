/**
 * @param country_div 国家select id
 * @param province_div 省select id
 * @param city_div 市 select id
 * @param area_div 区 select id
 * @param coid 国家ID
 * @param pid 省ID
 * @param cid 市ID
 * @param aid 区ID
 * 
 */
var Location = {
	config:{
		call_back:""
	},
	init:function(ctx,province_div,city_div,area_div,school_div,pid,cid,aid,schoolval){
		Location.init_province(ctx,province_div,city_div,area_div,school_div,pid,cid,aid,schoolval);
	},
	init_province : function(ctx,province_div,city_div,area_div,school_div,pid,cid,aid,schoolval){
		$.getJSON(ctx + "/msProvince/list",
        	{
				ranNum:Math.random()
			},
        	function(data){
	            var html = '';
	            html += '<option value="">--全部--</option>';	            			            		
	            for(var i =0 ;i<data.items.length;i++){
	            	var province = data.items[i];
	            	if(province.provinceCode == pid){
	            		html += '<option selected="selected" value="'+province.provinceCode+'">'+province.provinceName+'</option>';	            		
	            	}else{
	            		html += '<option value="'+province.provinceCode+'">'+province.provinceName+'</option>';	            			            		
	            	}
	            }
	            $("#"+province_div).html(html);
	            if(city_div && city_div!=''){
	            	var selected_province = $("#"+province_div).val();
	            	if(selected_province!= ''){
	            		Location.init_city(ctx,selected_province,city_div,area_div,school_div,cid,aid,schoolval);
	            	}
	            }
	            $("#"+province_div).change(function(){
	            	Location.call_back("province",province_div);
	            	
	            	if(city_div && city_div!=''){
	            		var selected_province = $(this).children('option:selected').val();
	            		if(selected_province!=''){
	            			Location.init_city(ctx,selected_province,city_div,area_div,school_div,cid,aid,schoolval);
	            		}else{
	            			Location.clean_select(city_div);
	            			Location.clean_select(area_div);
	            		}
	            	}
	            })
	        	Location.call_back("province",province_div);
	        });
	},
	init_city:function(ctx,province_id,city_div,area_div,school_div,cid,aid,schoolval){
		if(province_id == '' || province_id == null || province_id == 'null')
			return;
		$.getJSON(ctx + "/msCity/listByProvinceId",
    	{
			provinceCode 	:	province_id,
			ranNum:Math.random()
		},
    	function(data){
            var html = '';
            html += '<option value="">--全部--</option>';	   
            for(var i =0 ;i<data.items.length;i++){
            	var city = data.items[i];
            	if(city.cityCode == cid){
            		html += '<option selected="selected" value="'+city.cityCode+'">'+city.cityName+'</option>';		            		
            	}else{		            		
            		html += '<option value="'+city.cityCode+'">'+city.cityName+'</option>';		            		
            	}
            }
            $("#"+city_div).html(html);
            
            if(area_div && area_div!=''){
            	var selected_city = $("#"+city_div).val();
            	if(selected_city!=''){
            		Location.init_area(ctx,selected_city,area_div,school_div,aid,schoolval);
            	}
            }
            $("#"+city_div).change(function(){
            	Location.call_back("city",city_div);
            	if(area_div && area_div!=''){
            		var selected_city = $(this).children('option:selected').val();
            		if(selected_city!= '' ){
            			Location.init_area(ctx,selected_city,area_div,school_div,aid,schoolval);
            		}else{
            			Location.clean_select(area_div);
            		}
            	}
            })
        	Location.call_back("city",city_div);
        });
	},
	init_area	:function(ctx,city_id,area_div,school_div,aid,schoolval){
		if(city_id == '' || city_id == null || city_id == 'null')
			return;
		$.getJSON(ctx + "/msArea/listByCityId",
    	{
			cityCode 	:	city_id,
			ranNum:Math.random()
		},
    	function(data){
            var html = '';
            html += '<option value="">--全部--</option>';	   
            for(var i =0 ;i<data.items.length;i++){
            	var obj = data.items[i];
            	if(obj.areaCode == aid){
            		html += '<option selected="selected" value="'+obj.areaCode+'">'+obj.areaName+'</option>';		            		
            	}else{		            		
            		html += '<option value="'+obj.areaCode+'">'+obj.areaName+'</option>';		            		
            	}
            }
            $("#"+area_div).html(html);
            
            
            if(school_div && school_div!=''){
            	var selected_area = $("#"+area_div).val();
            	if(selected_area!=''){
            		Location.init_school(ctx,selected_area,school_div,schoolval);
            	}
            }
            $("#"+area_div).change(function(){
            	 Location.call_back("area",area_div);
	        	 if(school_div && school_div!=''){
	        		 var selected_area = $(this).children('option:selected').val();
	        		 if(selected_area!= '' ){
	        			 Location.init_school(ctx,selected_area,school_div,schoolval);
	        		 }else{
	        			 Location.clean_select(school_div);
	        		 }
	        	 }
            })
            
        	Location.call_back("area",area_div);
        });
	},
	init_school : function(ctx,area_id,school_div,schoolval){
		if(area_id == '' || area_id == null || area_id == 'null')
			return;
		$.getJSON(ctx + "/msSchool/getMsSchoolBaseList",
    	{
			areaCode 	:	area_id,
			ranNum:Math.random()
		},
    	function(data){
            var html = '';
            html += '<option value="">--全部--</option>';	   
            for(var i =0 ;i<data.items.length;i++){
            	var obj = data.items[i];
            	if(obj.id == schoolval){
            		html += '<option selected="selected" value="'+obj.id+'">'+obj.name+'</option>';		            		
            	}else{		            		
            		html += '<option value="'+obj.id+'">'+obj.name+'</option>';		            		
            	}
            }
            $("#"+school_div).html(html);
            
            $("#"+school_div).change(function(){
            	Location.call_back("school",school_div);
            })
            
        	Location.call_back("school",school_div);
        });
	},
	call_back:function(type,div_id){
		var selected = $("#"+div_id).children('option:selected');
    	if(Location.config.call_back != ''){
    		Location.config.call_back(type,selected);
    	}
	},
	clean_select : function (select_id){
		if(select_id != ''){
			var html = '<option value="">--全部--</option>';	   
			$("#"+select_id).html(html);
		}
	}
}