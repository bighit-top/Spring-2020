<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   
<html>
<head>
<title>유저게시판 삭제 PRO</title>


<c:if test="${check == 0}">
	<script language="javaScript">
		alert("비밀번호가 맞지 않습니다");
		history.go(-1);
	</script>
</c:if>



<c:if test="${check == 1}">
	<script language="javaScript">
		alert("게시글 삭제가 완료되었습니다");
	    window.location="/moneyWatch/faqList.mw"; 
	</script>	 
</c:if>

</head>
</html>