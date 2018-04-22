$(function () {
    // Tencent COS
    var root = 'https://o2o-1252833636.cos.ap-guangzhou.myqcloud.com/';
    var loading = false;
    // 分页允许返回的最大条数，超过则禁止访问
    var maxItems = 100;
    // 一页返回最大数目
    var pageSize = 5;
    // 获取店铺列表的URL
    var listUrl = '/o2o/frontend/listshops';
    // 获取店铺类别列表 区域列表URL
    var searchDivUrl = '/o2o/frontend/listshopspageinfo';
    // 页码
    var pageNum = 1;
    // 地址栏URL中获取parent shop category id
    var parentId = getQueryString('parentId');
    var areaId = '';
    var shopCategoryId = '';
    var shopName = '';
    // 渲染店铺类别列表以及区域列表 用于搜索
    getSearchDivData();
    // 预先加载10条店铺信息
    addItems(pageSize,pageNum);

    /**
     * 获取店铺列表 区域列表
     */
    function getSearchDivData() {
        // 如果传入parentId，则取出一级下的所有二级
        var url = searchDivUrl + '?' + 'parentId=' + parentId;
        $.getJSON(url,function (data) {
            if(data.success){
                // 获取后台返回的店铺类别列表
                var shopCategoryList = data.shopCategoryList;
                var html = '';
                html += '<a href="#" class="button">全部类别</a>';
                // 店铺类别列表 拼html
                shopCategoryList.map(function (item,index) {
                    html += '<a href="#" class="button" data-category-id=' + item.shopCategoryId + '>' + item.shopCategoryName + '</a>';
                });
                // 返回前端
                $('#shoplist-search-div').html(html);
                var selectOptions = '<option value="">全部区域</option>';
                // 获取后台返回的区域列表
                var areaList = data.areaList;
                // 区域列表 拼html
                areaList.map(function (item,index) {
                    selectOptions += '<option value="' + item.areaId + '">' + item.areaName + '</option>';
                });
                // 返回前端
                $('#area-search').html(selectOptions);
            }
        });
    }

    /**
     * 获取分页展示的店铺类别信息
     * @param pageSize
     * @param pageIndex
     */
    function addItems(pageSize,pageIndex) {
        // 拼接查询的URL 默认：null
        var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
            + pageSize + '&parentId=' + parentId + '&areaId=' + areaId
            + '&shopCategoryId=' + shopCategoryId + '&shopName=' + shopName;
        // 加载符 获取后台数据时 则无法访问 避免多次加载 加重服务器负担
        loading = true;
        // 访问后台查询相应条件的店铺
        $.getJSON(url,function (data) {
            // 获取当前条件下总数
            maxItems = data.count;
            var html = '';
            // 遍历店铺列表 拼html卡片
            data.shopList.map(function (item,index) {
                html += '' + '<div class="card" data-shop-id="'
                    + item.shopId + '">' + '<div class="card-header">'
                    + item.shopName + '</div>'
                    + '<div class="card-content">'
                    + '<div class="list-block media-list">' + '<ul>'
                    + '<li class="item-content">'
                    + '<div class="item-media">' + '<img src="'
                    + root + item.shopImg + '" width="44">' + '</div>'
                    + '<div class="item-inner">'
                    + '<div class="item-subtitle">' + item.shopDesc
                    + '</div>' + '</div>' + '</li>' + '</ul>'
                    + '</div>' + '</div>' + '<div class="card-footer">'
                    + '<p class="color-gray">'
                    + new Date(item.editTime).Format("yyyy-MM-dd")
                    + '更新</p>' + '<span>点击查看</span>' + '</div>'
                    + '</div>';
            });
            // 返回前端
            $('.list-div').append(html);
            // 获取已显示数目
            var total = $('.list-div .card').length;
            // 若总数达到跟按照此查询条件列出来的总数一致，则停止后台的加载
            if (total >= maxItems) {
                // 隐藏提示符
                $('.infinite-scroll-preloader').hide();
            } else {
                $('.infinite-scroll-preloader').show();
            }
            // 否则页码加1，继续load出新的店铺
            pageNum += 1;
            // 加载结束，可以再次加载了
            loading = false;
            // 刷新页面，显示新加载的店铺
            $.refreshScroller();
        });
    }

    // 下拉屏幕 自动分页搜索
    $(document).on('infinite', '.infinite-scroll-bottom', function() {
        if (loading)
            return;
        addItems(pageSize, pageNum);
    });

    // 点击店铺 进入详情页面
    $('.shop-list').on('click','.card',function (e) {
       var shopId = e.currentTarget.dataset.shopId;
       window.location.href = '/o2o/frontend/shopdetail?shopId=' + shopId;
    });

    // 选择新的店铺类别 重置页码 清空原店铺列表 按照新类别查询
    $('#shoplist-search-div').on('click','.button',function (e) {
        if(parentId){
            // 传入父类下的子类
            shopCategoryId = e.target.dataset.categoryId;
            // 若此前选定别的category 先移除选定 再选择新的
            if($(e.target).hasClass('button-fill')){
                $(e.target).removeClass('button-fill');
                shopCategoryId = '';
            }else{
                $(e.target).addClass('button-fill').siblings().removeClass('button-fill');
            }
            // 查询条件改变 清空店铺再查询
            $('.list-div').empty();
            // 重置页码
            pageNum = 1;
            addItems(pageSize,pageNum);
        }else{
            // 若parent为空 按照父类查询
            parentId = e.target.dataset.categoryId;
            if($(e.target).hasClass('button-fill')){
                $(e.target).removeClass('button-fill');
                parentId = '';
            }else{
                $(e.target).addClass('button-fill').siblings().removeClass('button-fill');
            }
            // 由于查询条件改变，清空店铺列表再进行查询
            $('.list-div').empty();
            // 重置页码
            pageNum = 1;
            addItems(pageSize, pageNum);
            parentId = '';
        }
    });

    // 需要查询的店铺名字发生变化后，重置页码，清空原先的店铺列表，按照新的名字去查询
    $('#search').on('change', function(e) {
        shopName = e.target.value;
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    // 区域信息发生变化后，重置页码，清空原先的店铺列表，按照新的区域去查询
    $('#area-search').on('change', function() {
        areaId = $('#area-search').val();
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    // 点击后打开右侧栏
    $('#me').click(function() {
        $.openPanel('#panel-right-demo');
    });

    // 初始化页面
    $.init();


    
});