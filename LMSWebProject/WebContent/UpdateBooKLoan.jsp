<%@page import="com.gcit.lms.domain.Borrower"%>
<%@page import="com.gcit.lms.domain.BookLoan"%>

<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<%
List<BookLoan> bookLoans = new AdministratorService().readAllBookLoan();

%>
<%@include file="include.html"%>
  

${result}
<table class="table">
	<tr>
		<th>Book Title</th>
		<th>Branch Name</th>
		<th>Borrower Name</th>
		<th>Date Out</th>
		<th>Due Date</th>		
		<th>Edit</th>
	</tr>

	<%
		for (BookLoan b : bookLoans) {
	%>
	<tr>
		<td><%=b.getBook().getTitle()%></td>
		<td><%=b.getLibraryBranch().getBranchName()%></td>
	    <td><%=b.getBorrower().getBorrowerName()%></td>
	    <td><%=b.getDateOut()%></td>
	    <td><%=b.getDateDue()%></td>
		
		
		<td>
		<div >
    <div > 
    <a href="#" class="btn btn-info" rel="popover"  data-content='
<form id="mainForm" name="mainForm" method="post" action="editDueDate">
<p>
        <label>book id :</label>
        <input type="text" readonly="readonly" value="<%=b.getBook().getBookId()%>" id="txtName" name="bookId" />
    </p>
    <p>
        <label>branch id :</label>
        <input type="text" readonly="readonly"  value="<%=b.getLibraryBranch().getBranchId()%>" id="txtName" name="branchId" />
    </p>
   <p>
        <label>card No :</label>
        <input type="text" readonly="readonly"  value="<%=b.getBorrower().getCardNo()%>" id="txtName" name="cardNo" />
    </p>
    <p>
        <label>Date Out :</label>
        <input type="text" readonly="readonly"  value="<%=b.getDateOut()%>" id="txtName" name="dateOut" />
    </p>
    <p>
        <label>Due Date :</label>
        <input type="text" value="<%=b.getDateDue()%>" id="txtName" name="dueDate" />
    </p>
    <p>
        <input type="submit" name="Submit" value="Submit" />
    </p>
</form>' data-placement="top" data-original-title="Fill in form">Edit</a>

    </div>
</div> 
</td>
	</tr>
	<%
		}
	%>
</table>