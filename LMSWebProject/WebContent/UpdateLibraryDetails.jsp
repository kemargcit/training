<%@include file="include.html" %>
<%@page import="com.gcit.lms.domain.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.BookCopy"%>

<%
int branchId =Integer.parseInt(request.getParameter("branchId"));
LibraryBranch l = new LibrarianService().getBranchById(branchId);
List<Book> books = new AdministratorService().readAllBook();
for(Book book :books){
	
}

%>
<script>
$( document ).ready(function() {
	$("#bookTable").hide();
	$("#addCopy").click(function(){
		$("#bookTable").toggle().slideDown(slow);
		
		});
	
});

</script>
${result}
<table class="table">
	<tr>
		<th>Branch Id</th>
		<th>Branch Name</th>
		<th>Branch Name</th>
		<th>Edit</th>
	</tr>

	
	<tr>
		<td><%=l.getBranchId()%></td>
		<td><%=l.getBranchName()%></td>
		<td><%=l.getBranchAddress()%></td>
		
		<td>
		<div >
    <div > 
    <a href="#" class="btn btn-info" rel="popover" data-togle='modal' data-content='
<form id="mainForm" name="mainForm" method="post" action="editBranch">
<p>
        <label>id :</label>
        <input type="text" readonly="readonly" value="<%=l.getBranchId()%>" id="txtName" name="branchId" />
    </p>
    <p>
        <label>Name :</label>
        <input type="text" value="<%=l.getBranchName()%>" id="txtName" name="branchName" />
    </p>
   <p>
        <label>Address :</label>
        <input type="text" value="<%=l.getBranchAddress()%>" id="txtName" name="branchAddress" />
    </p>
    <p>
        <input type="submit" name="Submit" value="Submit" />
    </p>
</form>' data-placement="top" data-original-title="Fill in form">Edit</a>

    </div>
</div> 
</td>
		
	</tr>
	
</table>
<button id="addCopy" class="btn btn-danger">Add Book Copies</button>
<div id="bookTable">

<table class="table">
<thead>
Pick the Book you want to add copies of, to your branch:
</thead>
	<tr>
		<th>Book Id</th>
		<th>Title</th>
		<th>No. of Copies</th>
		<th>Update number of copies</th>
	</tr>

	<%
		for (Book book : books) {
	int  numbookCopy = new LibrarianService().getNumOfBookCopies(book, l);

	%>
	<tr>
		<td><%=book.getBookId()%></td>
		<td><%=book.getTitle()%></td>		
		<td><%=numbookCopy%></td>		
		
		
		<td>
			<div>
				<div>
					<a href="#" class="btn btn-info" rel="popover" data-togle='modal'
						data-content='
<form id="mainForm" name="mainForm" method="post" action="updateNumOfCopies">
<p>
        <label>id :</label>
        <input type="text" readonly="readonly" value="<%=book.getBookId()%>" id="txtName" name="bookId" />
    </p>
    <p>
        <label>Name :</label>
        <input type="text" readonly="readonly"  value="<%=book.getTitle()%>" id="txtName" name="title" />
        
    <p>
    <p>
        <label>BranchId :</label>
        <input type="text" readonly="readonly"  value="<%=l.getBranchId()%>" id="txtName" name="branchId" />
        
    <p>
       <p>
        <label>Number Of Copies :</label>
        <input type="text" value="<%=numbookCopy%>" id="txtName" name="numOfCopy" />
        
    <p>
        <input type="submit" name="Submit" value="Submit" />
    </p>
</form>'
						data-placement="top" data-original-title="Fill in form">Edit</a>

				</div>
			</div>
		</td>
		
	</tr>
	<%
		}
	%>
</table>


</div>