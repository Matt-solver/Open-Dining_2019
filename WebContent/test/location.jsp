<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
  window.onload=function(){
    //��ġ������ Ȯ���� �� �ִ� ���������� Ȯ��
    if(navigator.geolocation == undefined){
        alert("��ġ ���� ����� �������� �ʽ��ϴ�!")
        return;
    }
 }
 
 //���� ��ġ ���� �˾ƺ��� �޼ҵ�
 function showData(){
    navigator.geolocation.getCurrentPosition(success, fail); //���� ��ġ ������ �����ϰ� ���� ���� ������ ȣ��Ǵ� �Լ� ����
 }
 
 function success(position) { //������
    log("��ġ���� Ȯ�� ����!");
    for(var property in position.coords) { //�ݺ��� ���鼭 ���
        log("Key ��:"+property+" ����:"+position.coords[property]);
    }
    var lat=position.coords["latitude"];
    var lon=position.coords["longitude"];
    var url="http://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&sensor=false";
     //location.href = url;//������ �̵��ϱ�
 }
 
 //���н�
 function fail(err){
    switch (err.code){
        case err.PERMISSION_DENIED:
            msg = "����� �ź�";
        break;
 
        case err.PERMISSION_UNAVAILABLE:
            msg = "���������� ���� �� ����";
        break;
 
        case err.TIMEOUT:
            msg = "�ð��ʰ�";
        break;
 
        case err.UNKNOWN_ERROR:
            msg = "�� �� ���� ���� �߻�";
        break;
    }
        log(msg);
 }
  
 function log(msg){
    var console = document.getElementById("console");
    console.innerHTML += msg+"<br/>";
 }
</script>
 
 <button onclick="showData()">���� ��ġ Ȯ��</button>
 
<div id="console" style="width:500px; border:5px; font-size:20px"></div>

</body>
</html>