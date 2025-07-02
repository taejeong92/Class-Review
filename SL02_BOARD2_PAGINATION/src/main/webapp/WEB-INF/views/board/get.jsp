<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="http://localhost/jspPro/images/SiSt.ico">
<title>2025. 7. 2. 오후 12:35:43</title>
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
        /board/get.jsp
  </xmp>
  
  <form action="/board/register" method="post">
	  <table>  
	    <tbody>
	      <tr>
	        <th>글번호</th>
	        <td><input type="text" name="bno" class="full"  readonly="readonly"  value="${ boardVO.bno }"></td>        
	      </tr> 
	      <tr>
	        <th>제목</th>
	        <td><input type="text" name="title" class="full"  readonly="readonly"  value="${ boardVO.title }"></td>        
	      </tr> 
	      <tr>
	        <th>내용</th>
	        <td><textarea  name="content" class="full" readonly="readonly"><c:out value="${ boardVO.content }"></c:out></textarea></td>        
	      </tr> 
	      <tr>
	        <th>작성자</th>
	        <td><input type="text" name="writer" class="short" readonly="readonly" value="${ boardVO.writer }"></td>        
	      </tr>  
	    </tbody> 
	    <tfoot>
	      <tr>
	        <td colspan="2">
	          <button type="button"  data-oper="modify" class="edit">Modify</button>
	          <button type="button"  data-oper="remove" class="delete">Delete</button>
	          <button type="button" data-oper="list"  class="list">List</button>
	        </td>
	      </tr>
	    </tfoot>
	  </table>
	  
	  <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	    
  </form>
  
</div>

<script>
  $(function (){
	  
	  const formObj = $("form");
	  
	  $("tfoot button").on("click", function (){		  
		  // data-oper="modify"
		  let operation = $(this).data("oper");
		  if (operation == "modify") {
			//     /board/modify/2
			//   location.href = '/board/modify?bno=2'  ***
			// action="/board/register" method="post"
			formObj
			   .attr({
				   "action": "/board/modify", 
				   "method": "get"
			   })
			   .submit();		
		  } else if (operation == "remove") {
			  // 작성자 확인 X
			  if ( confirm("정말 삭제할거냐?") ) {
				  formObj
				   .attr({
					   "action": "/board/remove", 
					   "method": "get"
				   })
				   .submit();
			  } // if
		  } else if (operation == "list") {
			  formObj
			   .attr({
				   "action": "/board/list", 
				   "method": "get"
			   })
			   .empty()
			   .submit();
		  } // if
		  
	  }); // $("tfoot button").on("click"
			  
	  // list.jsp 코딩을 복사해서 get.jsp 붙여넣기
	  var result = '<c:out value="${result}" />'; 
	  checkModal(result); 
	  history.replaceState({}, null, null);  
	  function checkModal(result) {
		if ( result === "" || history.state  )		return ;
		if ( result === "SUCCESS" ) {
			alert( `${ boardVO.bno } 번이 수정되었습니다.` );
			return ;
		} // if  
	  } // function checkModal		  
	  
  }); // $(function (){
</script>

</body>
</html>



 