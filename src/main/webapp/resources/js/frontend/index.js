$(function () {
    // 定义访问后台,获取头条列表以及一级类别列表的URL
    var url = '/o2o/frontend/listmainpageinfo';
    // 访问后台，获取头条列表以及一级类别列表
    $.getJSON(url,function (data) {
        if(data.success){
            // 获取后台传递过来的头条列表
            var headLineList = data.headLineList;
            var swiperHtml = '';
            // 遍历头条列表，拼接轮播图
            headLineList.map(function (item,index) {
               swiperHtml += '<div class="swiper-slide img-wrap">'
                   + '<a href="' + item.lineLink
                   + '"external><img class="banner-img" src="'+ item.lineImg
                   + '"alt="' + item.lineName + '"></a>'+ '</div>';
            });
            // 轮播图赋值给前端HTML控件
            // 设定轮播时间为3秒
        }
    });
});