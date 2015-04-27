<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Publisher"%>

<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<%
List<Publisher> publishers = new AdministratorService().readAllPublihers();

%>
<%@include file="include.html"%>
  

${result}
<table class="table">
	<tr>
		<th>Publisher Id</th>
		<th>Publisher Name</th>
		<th>Publisher Address</th>
		<th>Publisher Phone</th>		
		<th>Edit</th>
		<th>Delete</th>
	</tr>

	<%
		for (Publisher p : publishers) {
	%>
	<tr>
		<td><%=p.getId()%></td>
		<td><%=p.getName()%></td>
	    <td><%=p.getAddress()%></td>
	    <td><%=p.getPhoneNumber()%></td>
		
		
		<td>
		<div >
    <div > 
    <a href="#" class="btn btn-info" rel="popover" data-togle='modal' data-content='
<form id="mainForm" name="mainForm" method="post" action="editPublisher">
<p>
        <label>id :</label>
        <input type="text" readonly="readonly" value="<%=p.getId()%>" id="txtName" name="pubId" />
    </p>
    <p>
        <label>Name :</label>
        <input type="text" value="<%=p.getName()%>" id="txtName" name="pubName" />
    </p>
   <p>
        <label>Address :</label>
        <input type="text" value="<%=p.getAddress()%>" id="txtName" name="pubAddress" />
    </p>
    <p>
        <label>Phone :</label>
        <input type="text" value="<%=p.getPhoneNumber()%>" id="txtName" name="pubPhone" />
    </p>
    <p>
        <input type="submit" name="Submit" value="Submit" />
    </p>
</form>' data-placement="top" data-original-title="Fill in form">Edit</a>

    </div>
</div> 
</td>
		<td><button id="deleteBtn" class="btn btn-danger"
				onclick="javascript:location.href='deletePublisher?pubId=<%=p.getId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>

