/**
 * Created by 英宇 on 2016/12/19.
 */
function Common() {
    this.baseurl = "/"; //请求根路径
    this.sildeboxes = $(".sel_box");
};
//下拉
Common.prototype.slideUpAndDown = function() {
    var _this = this.sildeboxes;
    _this.each(function() {
        //下拉超过5行显示滚动条
        if ($(this).find('li').length > 5) {
            $(this).find('ul').slimscroll({
                height: "200px"
            })
        }
    });
    _this.on("click", function(e) {
        common.stopPropagation(e);
        $(this).toggleClass('active');
        if ($(this).hasClass('active')) {
            $(this).find('.sel_list').show()
        } else {
            $(this).find('.sel_list').hide()
        }
    })
    _this.find('li').on("click", function(e) {
        common.stopPropagation(e);
        $(this).parents(".sel_box").removeClass('active');
        $(this).parents(".sel_box").find('.sel_text').val($(this).text());
        $(this).parents(".sel_box").find('.sel_list').hide();
    })
};
//回车事件
Common.prototype.keyenter = function(ele, call) {
    ele.on("keydown", function(e) {
        if (e.keyCode == 13) {
            call(); //处理事件
        }
    });
};
//全局click
Common.prototype.documentClick = function(){
    var _this = this.sildeboxes;
    $(document).on("click",function(){
        _this.removeClass('active');
        _this.find('.sel_list').hide();
    })
};
//阻止冒泡
Common.prototype.stopPropagation = function(e) {
    e = e || window.event;
    if (e.stopPropagation)
        e.stopPropagation();
    else {
        e.cancelBubble = true;
    }
};

var common = new Common();
$(function(){
    common.documentClick();
})
    
