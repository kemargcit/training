<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@page import="com.gcit.lms.domain.Genre"%>

<%@page import="java.util.List"%>
<%@include file="include.html" %>

<script type="text/javascript">
<!--

//-->
$( document ).ready(function() {

});


</script>
<%
	List<Author> authors = new AdministratorService().readAllAuthors();
    List<Publisher> publishers =new AdministratorService().readAllPublihers();
    List<Genre> genres =new AdministratorService().readAllGenres();
%>

<h4>Add New Author</h4>
${result}

<form action="addBook" method="post">
	<table class="table">
	<tr>
	<td>Enter Book Name:</td>
	<td><input type="text" name="title"/></td>
	</tr>
	<tr>
	<td>Select Author:</td>
	<td>
	<select multiple name="authorId">
	
	<%
		for (Author a : authors) {
	%>
	<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
	<%
		}
	%>
	</select>
	</td>

	<td>
	</tr>
	<tr>
	<td>Select Publisher:</td>
	<td><select name="pubId" >
	
	<%
		for (Publisher p : publishers) {
	%>
	<option value="<%=p.getId()%>"><%=p.getName()%></option>
	<%
		}
	%>
	</select></td>
	</tr>
	<tr>
	<td>Select Genre:</td>
	 <td>
	<select multiple name="genreId">
	<%
		for (Genre g : genres) {
	%>
	
	<option value="<%=g.getGenreId()%>"><%=g.getName()%></option>
	<%
		}
	%>
	</select>
	</td>
	</tr>
	
	
	</table>		
		
		<input type="submit"  />
	</form>
