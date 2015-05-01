<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@page import="com.gcit.lms.domain.Genre"%>

<%@page import="java.util.List"%>
<%@include file="include.html" %>
<style>
td{
width:400px;
}



</style>

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

${result}
<form class="form-inline" role="form" action="addAuthor" method="post">
  <div class="form-group">

<table>

<tr>

<td>
	<label class="aria-describedby">Add Book</label><br>
		<input placeholder="Enter Book Name" class="form-control input-md" type="text" id="title" name="title"/><br>
		</td>
		
		<td >
			<label class="aria-describedby">Select Author(s)</label><br>
		
		<select multiple name="authorId" class="form-control">
	
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
			<label class="aria-describedby">Select publisher(s)</label><br>
		<select name="pubId" class="form-control">
	
	<%
		for (Publisher p : publishers) {
	%>
	<option value="<%=p.getId()%>"><%=p.getName()%></option>
	<%
		}
	%>
	</select>
	</td>
	<td>
			<label class="aria-describedby">Select genre(s)</label><br>
	<select multiple name="genreId" class="form-control">
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
		<br>
		<tr >
		<td></td>
		<td>
		<button id="sub" type="submit" class="btn btn-primary">
  <i class="glyphicon glyphicon-floppy-disk"></i> Enter
</button>
		</td>
		</tr>
		</table>
				</div>
		
		<br>
	</form>
<%-- <form action="addBook" method="post">
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
 --%>