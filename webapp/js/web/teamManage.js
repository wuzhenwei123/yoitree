function showStudyDetail(id){
	layer.open({
		type: 2,
		title: '学习详情',
		shadeClose: false,
		shade: 0.8,
		area: ['70%', '75%'],
		content: $("#path").val()+'/web/toTeamManageDetail?userId='+id//iframe的url
	});
}