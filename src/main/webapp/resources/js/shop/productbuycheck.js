$(function () {
    var productName = "";
    getList();

    function getList() {
        var listUrl = '/o2o/shopadmin/listuserproductmapsbyshop?pageIndex=0&pageSize=100&productName=' + productName;
        // 访问后台 获取购买信息列表
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var userProductMapList = data.userProductMapList;
                var tempHtml = '';
                userProductMapList.map(function (item, index) {
                    tempHtml += '<div class="row row-productbuycheck">' +
                        '<div class="col-20">' + item.product.productName + '</div>' +
                        '<div class="col-40 productbuycheck-time">' + new Date(item.createTime).Format("yyyy-MM-dd") + '</div>' +
                        '<div class="col-25">' + item.user.name + '</div>' +
                        '<div class="col-15">' + item.point + '</div>' +
                        '</div>';
                });
                $('.productbuycheck-wrap').html(tempHtml);
            }
        });
    }

    // 搜索时，依据商品名模糊搜索购买记录，清空后，再次加载
    $('#search').on('change', function (e) {
        productName = e.target.value;
        $('.productbuycheck-wrap').empty();
        getList();
    });
    
    // 七天的销量
    function getProductSellDailyList() {
        var listProductSellDailyUrl = '/o2o/shopadmin/listproductselldailyinfobyshop';
        $.getJSON(listProductSellDailyUrl,function (date) {
           if(date.success){
               var myChart = echarts.init(document.getElementById('chart'));
               var option = generateStaticEchartPart();
               option.legend.data = date.legendData;
               option.xAxis = data.xAxis;
               option.series = data.series;
               myChart.setOption(option);
           }
        });
    }

    function generateStaticEchartPart() {
        var option = {
            // 提示框,鼠标悬浮交互时的信息提示
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器,坐标轴触发有效
                    type: 'shadow'// 默认为直线
                }
            },
            // 图例,每个图表仅有一个图例
            legend: {
                // 图例内容数组 数组为{String},每一项代表一个系列的name
            },
            // 直角坐标系内汇图网格
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            // 直角坐标系中横轴数组
            xAxis: [
                {

                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ]
        };
    }

});