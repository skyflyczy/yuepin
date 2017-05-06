(function(){var d=750,c=parseInt(window.screen.width),e=c/d,b=navigator.userAgent;if(/Android (\d+\.\d+)/.test(b)){var a=parseFloat(RegExp.$1);if(a>2.3){document.write('<meta name="viewport" content="width='+d+",minimum-scale="+e+",maximum-scale="+e+',target-densitydpi=device-dpi">')}else{document.write('<meta name="viewport" content="width='+d+',target-densitydpi=device-dpi">')}}else{document.write('<meta name="viewport" content="width='+d+',user-scalable=no,target-densitydpi=device-dpi">')}})();
// 我的名片 添加删除	
	function add_tar(){             
        $(".rele_devic li.target .tar_select").append("<div class='tar_box'><select class='mgt10'><option value='1'>最大波长</option><option value='2'>最大图像面积</option></select><span class='del_tar btn_3'>-</span></div>");
    };
    function add_com(){             
        $(".rele_devic li.charge .cha_inp_box").append("<div class='input' data='123'><input class='name'  type='text' placeholder='名称'><input class='price' type='text' placeholder=价格><input class='company' type='text' placeholder='单位'><span class='del_com btn_3' >-</span></div>");
    };
    $("body").on("click",".del_com",function(){
        $(this).parent().remove();
    });
    $("body").on("click",".del_tar",function(){
        $(this).parent().remove();
    });
    function add_shoufei_inp(){             
        $(".cover_layer .price_popup .add_box").append("<ul class='add_form mgl20 mgt15'><li class='fw'><label class='text' >服务项目:</label><input  type='text' name='gov_name' ></li><li class='sf'><label class='text' >收费类型:</label><input type='radio' name='tiaomu' id='tiaomu1' checked='checked' value='按时数'><label for='tiaomu1' class='fs-22 mgr20'>按时数</label><input type='radio' name='tiaomu' id='tiaomu2' value='按样品数'><label for='tiaomu2' class='fs-22'>按样品数</label></li><li class='dj'><label class='text' >单价:</label><input  type='text' ></li><li class='sl'><label class='text' >数量:</label><input  type='text' ></li></ul><span class='btn_4 btn-w250 qrtj' onclick='add_shoufei()'>确认添加</span>");
    };
    function add_shoufei(){
        var fw = $(".cover_layer .price_popup .add_form li.fw input").val();
        var sf = $('.cover_layer .price_popup .add_form li.sf input[name="tiaomu"]:checked ').val();
        var dj = $(".cover_layer .price_popup .add_form li.dj input").val();
        var sl = $(".cover_layer .price_popup .add_form li.sl input").val();
        if (fw && dj && sl) {
            $(".cover_layer .price_popup .jg_list").append("<li class='clearfix'><div class='del_shoufei'>-</div><div class='name'><h4>"+fw+"</h4><p>"+sf+"</p></div><div class='num'><p class='price'>￥<span>"+dj+"</span></p><span>*"+sl+"</span></div></li>");
            $(".cover_layer .price_popup .add_box ul").remove();
            $(".cover_layer .price_popup .add_box span").remove();
        } else {
            return;
        }
    };
    $("body").on("click",".del_shoufei",function(){
        $(this).parent().remove();
    });
    function close1(){
        $(".cover_layer").hide(1);
        $(".cover_layer .box_4").hide(1);
    };
    $(".chatbox div.push-button span.cancel").click(function(){
        $(".cover_layer").show(1);
        $(".cover_layer .cancel_popup").show(1);
    });
    $(".chatbox div.push-button span.docking").click(function(){
        $(".cover_layer").show(1);
        $(".cover_layer .docking_popup").show(1);
    });
    $(".chatbox div.push-button span.complete").click(function(){
        $(".cover_layer").show(1);
        $(".cover_layer .complete_popup").show(1);
    });
    $(".chatbox div.push-button span.price").click(function(){
        $(".cover_layer").show(1);
        $(".cover_layer .price_popup").show(1);
    });
$(function(){
// 列表页面的筛选
	$(".screen_switch span").toggle(
		function(){
			$(".screen_switch span i").css("backgroundPosition","0 0");
			$(".screen .screen_form").animate({height:'405px',padding:'20px'},300);
		},
		function(){
			$(".screen_switch span i").css("backgroundPosition","0 -30px");
			$(".screen .screen_form").animate({height:'0',padding:'0'},300);
		}
	);
    $(".order_details .screen_switch span").toggle(
        function(){
            $(".screen_switch span").html("收缩详情<i></i>");
            $(".screen_switch span i").css("backgroundPosition","0 0");
            $(".order_details .order_details_top").removeClass("ord150").addClass("ordauto");
        },
        function(){
            $(".screen_switch span").html("展开详情<i></i>");
            $(".screen_switch span i").css("backgroundPosition","0 -30px");
            $(".order_details .order_details_top").removeClass("ordauto").addClass("ord150")
        }
    );
// 详情页面点击收藏
	$(".details .show_img .keep").toggle(
		function(){
			$(".details .show_img .keep span").text("已收藏");
		},
		function(){
			$(".details .show_img .keep span").text("收藏");
		}
	);
// 星星评分点击选择
	// var li = $(".stars li");
	// for(var i=0;i<li.length;i++){
	// 	li[i].addEventListener('click',function(){	
	// 		for(var i=0;i<li.length;i++){
	// 			li[i].className='';
	// 		}
	// 		this.className='active';
	// 		// alert(this.innerHTML+'分');
	// 	},false)
	// };
// 点击上传图片
    function upload(name) {
        $(name).change(function() {
            var $file = $(this);
            var fileObj = $file[0];
            var windowURL = window.URL || window.webkitURL;
            var dataURL;
            var $img = $(this).parent();
             
            if(fileObj && fileObj.files && fileObj.files[0]){
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
                $img.css("background","url("+dataURL+")").html("");
            }else{
                dataURL = $file.val();
                var imgObj = document.getElementById("preview");
                imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").attr("background","url("+dataURL+")"); 
            }
        });
    };
    upload(".upload_1");
});