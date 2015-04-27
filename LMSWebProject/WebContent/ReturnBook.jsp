<%@include file="include.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.gcit.lms.domain.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>

<%
	List<LibraryBranch> branches = new LibrarianService().readAll();

request.setAttribute("branches", branches);
%>
<script type="text/javascript">
<!--
	//-->

	$(document).ready(
			function() {
				$("#branchDiv").hide();
				
				
				$('#selectbranch').bind('click', function(){
				      var $div1 = $('#loanDiv'), $div2 = $('#branchDiv')
				      if ( $div2.is(':visible') ) {
				            $div2.hide();
				            $div1.show();
				            $(this).html("<h2>Select The Branch You Want To return Book To</h2>");

				      } else if ( $div1.is(':visible') && $div2.is(':hidden') )
				    	  
				      {
				    	  
				    	
					            $(this).html("<h2>Select Book To Return</h2>");
    
				            $div1.hide();
				            $div2.show();
				    	 
				      } 				  
				});
			});
	
	
</script>
${result}
<button style="margin:0 auto;
    display:block;" id="selectbranch" class="btn-lg btn-danger">
		<h2>Select The Branch You Want To return Book To</h2>
	</button>
<form id="bookSelect" method="post" action="submitReturn">
<input type="hidden" name="cardNo" value="<%=request.getParameter("cardNo")%>"/>
	<div id="loanDiv">

		<table class="table">
			<tr>
				<th>Book Id</th>
				<th>Title</th>
				<th>Branch</th>
				<th>Return</th>
			</tr>



			<c:forEach items="${bookloans}" var="bookloan">
				<tr>
					<td><c:out value="${bookloan.book.bookId}" /></td>
					<td><c:out value="${bookloan.book.title}" /></td>
					<td><c:out value="${bookloan.libraryBranch.branchName}" /></td>

					<td><c:out value="${branch.branchAddress}" /></td>
					<td><div class="checkbox">
							<label> <input name="bookId" id="bookId" type="checkbox"
								value="${bookloan.book.bookId}"> return
							</label>
						</div></td>

				</tr>


			</c:forEach>

		</table>
	</div>
	

	<div id="branchDiv">

		<table class="table">
			<tr>
				<th>Branch Name</th>
				<th>Branch Address</th>
				<th>Select</th>
			</tr>
			<c:forEach items="${branches}" var="branch">
				<tr>
					<td><c:out value="${branch.branchName}" /></td>
					<td><c:out value="${branch.branchAddress}" /></td>
					<td>
					
					<div class="radio">
  <label>
    <input type="radio" name="branchId" id="raturnRadios1" value="${branch.branchId}" checked>
select
  </label>
</div>
					
					</td>
				</tr>
			</c:forEach>


		</table>
      <input id="submitReturn" type="submit" class="btn-lg btn-danger">
	</div>
	
	
</form>
