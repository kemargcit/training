<%@include file="include.html" %>
<%@page import="com.gcit.lms.domain.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%
List<LibraryBranch> branches = new LibrarianService().readAll();

request.setAttribute("branches", branches);
%>

<div>
${result}
<table class="table">
	<tr>
		<th>Branch Name</th>
		<th>Branch Address</th>
		<th>Select</th>
	</tr>
	<c:forEach items="${branches}" var="branch">
            <tr>
              <td>   <c:out value="${branch.branchName}"/> </td>
              <td><c:out value="${branch.branchAddress}"/></td>
<td><button id="deleteBtn" class="btn btn-danger"
				onclick="javascript:location.href='getBooksByBranchId?branchId=${branch.branchId}&cardNo=<%=request.getParameter("cardNo")%>';">Select</button></td>              
            </tr>
        </c:forEach>
	
	
	</table>


</div>

<table class="table">
<tr>
		<th>Book Id</th>
		<th>Title</th>
		<th>Publisher</th>
		<th>BORROW</th>
	</tr>




	<c:forEach items="${bookcopies}" var="bookcopy">
	  <tr>
	          <td><c:out value="${bookcopy.book.bookId}"/></td>
              <td><c:out value="${bookcopy.book.title}"/></td>
              <td><c:out value="${bookcopy.book.publisher.name}"/></td>
              
              <td><c:out value="${branch.branchAddress}"/></td>
<td><button id="deleteBtn" class="btn btn-danger"
				onclick="javascript:location.href='borrowBook?branchId=${branchId}&cardNo=<%=request.getParameter("cardNo")%>&bookId=${bookcopy.book.bookId}';">BORROW</button></td>              
            </tr>
	
	
        </c:forEach>

</table>


	


