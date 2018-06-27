(function() {
  function init() {
    initRootPx();
    bindEvent();
  }
// 字体rem
  function initRootPx() {
    var deviceWidth = document.documentElement.clientWidth;
    deviceWidth = deviceWidth > 750 ? 750 : deviceWidth;
    document.documentElement.style.fontSize = deviceWidth / 7.5 + 'px';
  }

  function bindEvent() {
    window.onresize = initRootPx;
    bottomClick();
  }

  function bottomClick() {
    var str = $('.active-b i').css('backgroundImage');
    if( str == undefined){
      return false;
    }else{
      str = str.replace('.png','_1.png');
      $('.active-b').find('i').css({
          background: str,
          backgroundSize:'100%'
      })
    }
  }

  init();

})();