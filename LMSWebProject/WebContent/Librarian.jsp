<%@include file="include.html" %>
<%@page import="com.gcit.lms.domain.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>

<%
List<LibraryBranch> branches = new LibrarianService().readAll();
%>
${result}
<table class="table">
	<tr>
		<th>Author Id</th>
		<th>Author Name</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>

	<%
		for (LibraryBranch l : branches) {
			
	%>
	<tr>
		<td><%=l.getBranchId()%></td>
		<td><%=l.getBranchName()%></td>
		<td><%=l.getBranchAddress()%></td>
		
		
		<td><button id="deleteBtn" class="btn btn-danger"
				onclick="javascript:location.href='UpdateLibraryDetails.jsp?branchId=<%=l.getBranchId()%>';">Select</button></td>
	</tr>
	<%
		}
	%>
</table>

