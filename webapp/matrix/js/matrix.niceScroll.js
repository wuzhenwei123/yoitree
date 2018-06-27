/*$(document).ready(function(){
	$("#iframe-main").niceScroll({
		cursoropacitymin:0.1,
		cursoropacitymax:0.9,
		cursorcolor:"#adafb5",
		cursorwidth:"8px",
		cursorborder:"",
		cursorminheight:100,
		cursorborderradius:"8px",
		usetransition:600,
		background:"",
		railoffset:{top:10,left:-3},
		bouncescroll: true	
	}); 
	$("#sidebar").niceScroll({
		cursoropacitymin:0.1,
		cursoropacitymax:0.9,
		cursorcolor:"#524747",
		cursorwidth:"8px",
		cursorborder:"",
		cursorminheight:100,
		cursorborderradius:"8px",
		usetransition:600,
		background:"",
		railoffset:{top:10,left:-3},
		bouncescroll: true	
	}); 
});*/

$(document).ready(function(){
	$("#iframe-main").slimscroll({
		height:'100%',
		width : '100%'
	}); 
	$("#sidebar").slimscroll({
		height:'100%',
		width : '220px'
	}); 
});