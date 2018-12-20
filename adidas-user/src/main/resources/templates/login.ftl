<html>
<title>登陆</title>

<body>
    <form  method="post" id="search_form">
         <span>账号<input type="text" name="name" id="name"/></span><br/>
        <span>密码<input type="password" name="password" id="password"/></span><br/>
          <img src="/user/verification" id="ss"><br/>
        <input type="text" name="image" id="image"/><br/>
        <a href="javaScript:;"  onclick="zx();">看不清楚，换一张</a><br/>
       <button type="button" name="button" onclick="zl();">登陆</button>
        <span id="jg"></span>
    </form>
</body>
<script>
    function ajax(opt) {
        opt = opt || {};
        opt.method = opt.method.toUpperCase() || 'POST';
        opt.url = opt.url || '';
        opt.async = opt.async || true;
        opt.data = opt.data || null;
        opt.success = opt.success || function () {
        };
        var xmlHttp = null;
        if (XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        }
        else {
            xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
        }
        var params = [];
        for (var key in opt.data) {
            params.push(key + '=' + opt.data[key]);
        }
        var postData = params.join('&');
        if (opt.method.toUpperCase() === 'POST') {
            xmlHttp.open(opt.method, opt.url, opt.async);
            xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
            xmlHttp.send(postData);
        }
        else if (opt.method.toUpperCase() === 'GET') {
            xmlHttp.open(opt.method, opt.url + '?' + postData, opt.async);
            xmlHttp.send(null);
        }
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                opt.success(xmlHttp.responseText);
            }
        };
    };
    function zx(){
        var timenow = new Date().getTime();
        document.getElementById("ss").setAttribute("src","/user/verification?date="+ timenow);
    };
    function zl(){
        var name=document.getElementById("name");
        var password=document.getElementById("password");
        var image=document.getElementById("image");
        var jg=document.getElementById("jg");
        var search_form=document.getElementById("search_form");
        if(name.value=="" || password.value=="" || image.value=="" || jg.value==""){
            return alert("不能为空");
        }
        ajax({
            url: '/user/userQuery',
            method: 'POST',
            data: {
                'name': name.value,
                'password': password.value,
                'image': image.value,
            },
            success: function(response){
                var jsonData = JSON.parse(response);//1.转json
                if(jsonData.rtnCode==500){
                   return jg.innerHTML=jsonData.msg;
                }
                window.location.href="http://localhost:8763/index.html";
            }
        });
    };
</script>
</html>