$(function () {
    // Tencent COS
    var root = 'https://o2o-1252833636.cos.ap-guangzhou.myqcloud.com/';
    var loading = false;
    // 分页允许返回的最大条数，超过则禁止访问
    var maxItems = 20;
    // 一页返回最大数目
    var pageSize = 3;
    // 获取商品列表的URL
    var listUrl = '/o2o/frontend/listproductsbyshop';
    // 页码
    var pageNum = 1;
    // 地址栏中获取shopid
    var shopId = getQueryString('shopId');
    var productCategoryId = '';
    var productName = '';
    // 获取店铺信息 以及商品信息类别列表的URL
    var searchDivUrl = '/o2o/frontend/listshopdetailpageinfo?shopId=' + shopId;
    // 渲染基本信息 以及商品类别列表
    getSearchDivData();
    // 加载X条商品信息
    addItems(pageSize, pageNum);

    // 兑换礼品 TODO

    /**
     * 获取店铺信息 以及商品信息类别列表
     */
    function getSearchDivData() {
        var url = searchDivUrl;
        $.getJSON(url, function (data) {
            if (data.success) {
                var shop = data.shop;
                $('#shop-cover-pic').attr('src', root + shop.shopImg);
                $('#shop-update-time').html(new Date(shop.editTime).Format("yyyy-MM-dd"));
                $('#shop-name').html(shop.shopName);
                $('#shop-desc').html(shop.shopDesc);
                $('#shop-addr').html(shop.shopAddr);
                $('#shop-phone').html(shop.phone);
                // 后端返回店铺商品类别列表
                var productCategoryList = data.productCategoryList;
                var html = '';
                html += '<a href="#" class="button">全部类别</a>';
                // 商品类别列表 拼html
                productCategoryList.map(function (item, index) {
                    html += '<a href="#" class="button" data-category-id='
                        + item.productCategoryId + '>'
                        + item.productCategoryName + '</a>';
                });
                // 商品类别返回前端
                $('#shopdetail-button-div').html(html);
            }
        });
    }

    /**
     * 获取分页展示的店铺类别信息
     * @param pageSize
     * @param pageIndex
     */
    function addItems(pageSize, pageIndex) {
        // 拼接出查询的URL，赋空值默认就去掉这个条件的限制，有值就代表按这个条件去查询
        var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
            + pageSize + '&productCategoryId=' + productCategoryId
            + '&productName=' + productName + '&shopId=' + shopId;
        // 设定加载符，若还在后台取数据则不能再次访问后台，避免多次重复加载
        loading = true;
        // 访问后台获取相应查询条件下的商品列表
        $.getJSON(url, function (data) {
            if (data.success) {
                // 获取当前查询条件下商品的总数
                maxItems = data.count;
                var html = '';
                // 遍历商品列表，拼接出卡片集合
                data.productList.map(function (item, index) {
                    html += '' + '<div class="card" data-product-id='
                        + item.productId + '>'
                        + '<div class="card-header">' + item.productName
                        + '</div>' + '<div class="card-content">'
                        + '<div class="list-block media-list">' + '<ul>'
                        + '<li class="item-content">'
                        + '<div class="item-media">' + '<img src="'
                        + root + item.imgAddr + '" width="44">' + '</div>'
                        + '<div class="item-inner">'
                        + '<div class="item-subtitle">' + item.productDesc
                        + '</div>' + '</div>' + '</li>' + '</ul>'
                        + '</div>' + '</div>' + '<div class="card-footer">'
                        + '<p class="color-gray">'
                        + new Date(item.editTime).Format("yyyy-MM-dd")
                        + '更新</p>' + '<span>点击查看</span>' + '</div>'
                        + '</div>';
                });
                // 将卡片集合添加到目标HTML组件里
                $('.list-div').append(html);
                // 获取目前为止已显示的卡片总数，包含之前已经加载的
                var total = $('.list-div .card').length;
                // 若总数达到跟按照此查询条件列出来的总数一致，则停止后台的加载
                if (total >= maxItems) {
                    // 加载完毕 注销加载
                    $.detachInfiniteScroll($('.infinite-scroll'));
                    $('.infinite-scroll-preloader').remove();
                }
                // 否则页码加1，继续load出新的店铺
                pageNum += 1;
                // 加载结束，可以再次加载了
                loading = false;
                // 刷新页面，显示新加载的店铺
                $.refreshScroller();
            }
        });
    }

    // 下拉屏幕 自动分页搜索
    $(document).on('infinite', '.infinite-scroll-bottom', function () {
        if (loading)
            return;
        addItems(pageSize, pageNum);
    });

    // 选择新的商品类别 重置页码 清空原店铺列表 按照新类别查询
    $('#shopdetail-button-div').on('click','.button',function (e) {
            // 获取商品类别Id
            productCategoryId = e.target.dataset.categoryId;
            if (productCategoryId) {
                // 若之前已选定了别的category,则移除其选定效果，改成选定新的
                if ($(e.target).hasClass('button-fill')) {
                    $(e.target).removeClass('button-fill');
                    productCategoryId = '';
                } else {
                    $(e.target).addClass('button-fill').siblings()
                        .removeClass('button-fill');
                }
                $('.list-div').empty();
                pageNum = 1;
                addItems(pageSize, pageNum);
            }else{
                $(e.target).addClass('button-fill').siblings().removeClass('button-fill');
                $('.list-div').empty();
                pageNum = 1;
                addItems(pageSize, pageNum);
            }
        });

    // 点击商品 进入详情页面
    $('.list-div').on('click', '.card', function (e) {
        var productId = e.currentTarget.dataset.productId;
        window.location.href = '/o2o/frontend/productdetail?productId=' + productId;
    });

    // 需要查询的商品名字发生变化后，重置页码，清空原先的店铺列表，按照新的名字去查询
    $('#search').on('input', function (e) {
        productName = e.target.value;
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    // 点击后打开右侧栏
    $('#me').click(function () {
        $.openPanel('#panel-right-demo');
    });

    // 初始化页面
    $.init();


    // END
});