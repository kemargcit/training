<%@page import="com.gcit.lms.domain.Borrower"%>

<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<%
List<Borrower> borrowers = new AdministratorService().readAllBorrowers();

%>
<%@include file="include.html"%>
  

${result}
<table class="table">
	<tr>
		<th>Card No</th>
		<th>Borrower Name</th>
		<th>Borrower Address</th>
		<th>Borrower Phone</th>		
		<th>Edit</th>
		<th>Delete</th>
	</tr>

	<%
		for (Borrower b : borrowers) {
	%>
	<tr>
		<td><%=b.getCardNo()%></td>
		<td><%=b.getBorrowerName()%></td>
	    <td><%=b.getBorrowerAddress()%></td>
	    <td><%=b.getBorrowerPhone()%></td>
		
		
		<td>
		<div >
    <div > 
    <a href="#" class="btn btn-info" rel="popover" data-togle='modal' data-content='
<form id="mainForm" name="mainForm" method="post" action="editBorrower">
<p>
        <label>id :</label>
        <input type="text" readonly="readonly" value="<%=b.getCardNo()%>" id="txtName" name="cardNo" />
    </p>
    <p>
        <label>Name :</label>
        <input type="text" value="<%=b.getBorrowerName()%>" id="txtName" name="borName" />
    </p>
   <p>
        <label>Address :</label>
        <input type="text" value="<%=b.getBorrowerAddress()%>" id="txtName" name="borAddress" />
    </p>
    <p>
        <label>Phone :</label>
        <input type="text" value="<%=b.getBorrowerPhone()%>" id="txtName" name="borPhone" />
    </p>
    <p>
        <input type="submit" name="Submit" value="Submit" />
    </p>
</form>' data-placement="top" data-original-title="Fill in form">Edit</a>

    </div>
</div> 
</td>
		<td><button id="deleteBtn" class="btn btn-danger"
				onclick="javascript:location.href='deleteBorrower?cardNo=<%=b.getCardNo()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>

