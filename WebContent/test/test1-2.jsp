<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<form name="box" method="get">

	<input type="file" name="menuImg" readonly="readonly" >
	<input type="button" name="button" value="Insert" onclick="change();">
	<img name="img" src="">

</form>

<script>
function change() {
	filename = document.box.menuImg.value;
	var filename=filename.substr(filename.lastIndexOf("damdak"));	//�����̸� �ڸ���
	alert(filename);
	document.box.img.src = "../img/" + filename;	//�����̸� �̹��� src�� �ֱ�
}
</script>

</body>
</html>