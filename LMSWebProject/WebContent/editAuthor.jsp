<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<% 
String authorId = request.getParameter("authorIdToEdit");
Author a = new AdministratorService().readOneAuthor(Integer.parseInt(authorId));

%>

<div class="modal-body">

	<form action="editAuthor" method="get">
		Enter Author Name: <input type="text" name="authorName" value="<%=a.getAuthorName()%>"/>
		<input type="hidden" value="<%=a.getAuthorId()%>" name="authorId" />
		<input type="submit"/>
	</form>
</div>