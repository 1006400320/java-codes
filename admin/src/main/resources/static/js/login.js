/**
 * 登录
 */
// $(function(){
//      layui.use(['form' ,'layer'], function() {
//          var form = layui.form;
//          var layer = layui.layer;
//          form.on("submit(login)",function () {
//              login();
//              return false;
//          });
//          var path=window.location.href;
//          if(path.indexOf("kickout")>0){
//              layer.alert("您的账号已在别处登录；若不是您本人操作，请立即修改密码！",function(){
//                  window.location.href="/login";
//              });
//          }
//      })
//  })

function login(){
    var keyword = $("#keyword").val();
    if (keyword == null || keyword.trim() == '') {
        layer.alert("请输入用户名或邮箱~", {icon: 5, title: '喵呜'});
        return;
    }

    var password = $("#password").val();
    if (password == null || password.trim() == '') {
        layer.alert("请输入密码~", {icon: 5, title: '喵呜'});
        return;
    }

    $.post("/user/login",
        {
            keyword: keyword,
            password: password
        },
        function(data){
            console.log(data);
            if(data != null && data.code == 200){
                // layer.alert("注册成功,即将跳往首页~", {icon: 6, title: '喵呜'});
                layer.msg('登录成功！<span name="count" style="color: red;">3</span>秒后跳转到首页~', {
                    icon: 1,
                    title: '喵呜',
                    success: function (layero, index) {
                        var countElem = layero.find('span[name="count"]');
                        var timer = setInterval(function () {
                            var countTemp = parseInt(countElem.text()) - 1;
                            countTemp === 0 ? clearInterval(timer):countElem.text(countTemp);
                        }, 1000)
                    }
                }, function () {
                    location.href="/home";
                });
            } else {
                layer.alert(data.message, {icon: 5, title: '喵呜'});
            }
    });
}
