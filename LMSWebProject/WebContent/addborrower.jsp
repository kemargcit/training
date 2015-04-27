<%@include file="include.html" %>
<h4>Add New Borrower</h4>
	<form action="addBorrower" method="post">
	<table class="table">
		<tr>
			<td>Enter Borrower Name: </td>
			<td><input type="text" name="name" /> </td>
		</tr>
		<tr>
			<td>Enter Borrower Address: </td>
			<td><input type="text" name="address" /> </td>
		</tr>	
		<tr>
			<td>Enter Borrower Phone: </td>
			<td><input type="text" name="phone" /></td>
		</tr>
		</table>
		<input type="submit" />
	</form>
