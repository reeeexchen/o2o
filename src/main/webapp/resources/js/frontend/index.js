$(function () {
    // 定义访问后台,获取头条列表以及一级类别列表的URL
    var url = '/o2o/frontend/listmainpageinfo';
    var root = 'https://o2o-1252833636.cos.ap-guangzhou.myqcloud.com/';
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
                   + '"external><img class="banner-img" src="'+ root + item.lineImg
                   + '"alt="' + item.lineName + '"></a>'+ '</div>';
            });
            // 轮播图赋值给前端HTML控件
            $('.swiper-wrapper').html(swiperHtml);
            // 设定轮播时间为3秒
            $('.swiper-container').swiper({
                autoplay : 3000,
                autoplayDisableOnInteraction:false
            });
            // 获取后台传递过来的大类列表
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = "";
            // 遍历大类列表，拼接出俩个一行的类别
            shopCategoryList.map(function (item,index) {
                categoryHtml += '<div class = "col-50 shop-classify" data-category='
                    + item.shopCategoryId + '>' + '<div class="word">'
                    + '<p class="shop-title">' + item.shopCategoryName
                    + '</p>' + '<p class="shop-desc"> '
                    + item.shopCategoryDesc + '</p>' + '</div>'
                    + '<div class="shop-classify-img-warp"> '
                    + '<img class="shop-img" src=" ' + root + item.shopCategoryImg
                    + '"> </div>' + '</div>';
            });

            // 拼接好的类别渎职给前端HTML控件展示
            $(".row").html(categoryHtml);
        }
    });

    // 点击我的，则显示侧边栏
    $("#me").click(function () {
       $.openPanel('#panel-right-demo');
    });

    $('.row').on('click','.shop-classify',function (e) {
       var shopCategoryId = e.currentTarget.dataset.category;
       var newUrl = '/o2o/frontend/shoplist?parentId=' + shopCategoryId;
       window.location.href = newUrl;
    });

    // END
});