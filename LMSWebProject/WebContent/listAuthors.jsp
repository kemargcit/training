<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<%
	List<Author> authors = new AdministratorService().readAllAuthors();
%>
<%@include file="include.html"%>
    <script src="resources/jquery.confirm.min.js"></script>

<script type="text/javascript">
<!--

//-->
$( document ).ready(function() {
$("#deleteBtn").confirm();

});


</script>

${result}
<table class="table">
	<tr>
		<th>Author Id</th>
		<th>Author Name</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>

	<%
		for (Author a : authors) {
	%>
	<tr>
		<td><%=a.getAuthorId()%></td>
		<td><%=a.getAuthorName()%></td>
		
		
		<td>
		<div >
    <div > 
    <a href="#" class="btn btn-info" rel="popover" data-togle='modal' data-content='
<form id="mainForm" name="mainForm" method="get" action="editAuthor">
<p>
        <label>id :</label>
        <input type="text" readonly="readonly" value="<%=a.getAuthorId()%>" id="txtName" name="authorId" />
    </p>
    <p>
        <label>Name :</label>
        <input type="text" value="<%=a.getAuthorName()%>" id="txtName" name="authorName" />
    </p>
   
    <p>
        <input type="submit" name="Submit" value="Submit" />
    </p>
</form>' data-placement="top" data-original-title="Fill in form">Edit</a>

    </div>
</div> 
</td>
		<td><button id="deleteBtn" class="btn btn-danger"
				onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>

