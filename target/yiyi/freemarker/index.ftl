<!DOCTYPE html>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link rel="stylesheet" href="/css/style.css"/>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
  $("button").click(function(){
    $.get("getStudent",function(data,status){
    	
      $("#test1").text(data);
    });
  });
});
</script>
</head>
<body>
<p id="test1"></p>
</br>
<button>请开始你的表演</button>

</body>
</html>