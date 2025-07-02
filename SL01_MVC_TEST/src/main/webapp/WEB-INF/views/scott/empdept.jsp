<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="http://localhost/jspPro/images/SiSt.ico">
<title>2025. 6. 30. 오후 2:45:38</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }   
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">kEnik HOme</a></h1>
  <ul>
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<div>
  <xmp class="code"> 
        /scott/empdept.jsp
  </xmp>
  
  <div>
  <form action="/scott/empdept" method="get">
	  <select id="deptno" name="deptno">
	    <c:forEach items="${ dlist }" var="dto">
	      <option value="${ dto.deptno }">${ empty dto.dname ? '부서없음': dto.dname }</option>
	    </c:forEach>
	  </select>  
	  </form>
  </div>
  <br>
  <div> 	 
    <table id="tbl-emp">
      <caption></caption>
      <thead>
      <tr>
        <th></th>
        <th>Empno</th>
        <th>Ename</th>
        <th>Job</th>
        <th>Mgr</th>
        <th>Hiredate</th>
        <th>Sal</th>
        <th>Comm</th>
        <th>Deptno</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${ elist }" var="dto">
         <tr>
	        <td><input type="checkbox" value="${ dto.empno }" name="empno"></td>
	        <td>${ dto.empno }</td>
	        <td>${ dto.ename }</td>
	        <td>${ dto.job }</td>
	        <td>${ dto.mgr }</td>
	        <td>${ dto.hiredate }</td>
	        <td>${ dto.sal }</td>
	        <td>${ dto.comm }</td>
	        <td>${ dto.deptno }</td> 
	      </tr>
      </c:forEach>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="9">
          <button id="home" class="home">HOme</button>
        </td>
      </tr>
    </tfoot>
  </table>
  
  </div>
  
  
</div>

<script>
 $(function (){
	 
	 // 부서원 검색 
	 $("#deptno").on("change", function (){		  
		  // GET 방식 - ?deptno=10&deptno=20
		  $("form").submit();
	 });

	 
	// 상태관리
	 $("#deptno").val('${empty param.deptno ? 10 : param.deptno}');
	 
 });  // document.ready() 닫기
</script>

</body>
</html> 








