<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@page import="com.gcit.lms.domain.Genre"%>

<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<%
	List<Book> books = new AdministratorService().readAllBook();
	List<Author> authorsList = new AdministratorService()
			.readAllAuthors();
	List<Publisher> publishers = new AdministratorService()
			.readAllPublihers();
	List<Genre> genres = new AdministratorService().readAllGenres();
%>

<%@include file="include.html"%>




${result}
<table class="table">
	<tr>
		<th>Book Id</th>
		<th>Title</th>
		<th>Publisher</th>
		<th>Authors</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>

	<%
		for (Book book : books) {
	%>
	<tr>
		<td><%=book.getBookId()%></td>
		<td><%=book.getTitle()%></td>
		<td><%=book.getPublisher().getName()%></td>
		<%
			List<Author> authors = book.getAuthors();
		%>
		<td>
			<%
				for (Author author : authors) {
			%> <%=author.getAuthorName()%><br> <%
 	}
 %>
		</td>
		<%-- <%List<Genre> genres=book.getGenres();%>
		<td><%
		for (Genre genre: genres) {
	%>  
			<%=genre.getName()%><br>
			<%
		}
	%> --%>
		</td>
		<td>
			<div>
				<div>
					<a href="#" class="btn btn-info" rel="popover" data-togle='modal'
						data-content='
<form id="mainForm" name="mainForm" method="post" action="editBook">
<p>
        <label>id :</label>
        <input type="text" readonly="readonly" value="<%=book.getBookId()%>" id="txtName" name="bookId" />
    </p>
    <p>
        <label>Name :</label>
        <input type="text" value="<%=book.getTitle()%>" id="txtName" name="title" />
    </p>
   <p>
        <label>publisher :</label>
<select name="pubId" >
	
	<%for (Publisher p : publishers) {%>
	<option value="<%=p.getId()%>"><%=p.getName()%></option>
	<%}%>
	</select></p>
    <p>
            <label>author :</label>
    <select multiple name="authorId">
	<%
		for (Author a : authorsList) {
	%>
	<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
	<%
		}
	%>
	</select>
    </p
    <p>
                <label>genre :</label>
    
    <select multiple name="genreId">
	<%
		for (Genre g : genres) {
	%>
	
	<option value="<%=g.getGenreId()%>"><%=g.getName()%></option>
	<%
		}
	%>
	</select>
    </p>
    
    
    <p>
        <input type="submit" name="Submit" value="Submit" />
    </p>
</form>'
						data-placement="top" data-original-title="Fill in form">Edit</a>

				</div>
			</div>
		</td>
		<td><button id="deleteBtn" class="btn btn-danger"
				onclick="javascript:location.href='deleteBook?bookId=<%=book.getBookId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>