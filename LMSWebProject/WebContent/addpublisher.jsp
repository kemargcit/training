<%@include file="include.html" %>
	<h4>Add New Publisher</h4>
	<form action="addPublisher" method="post">
		<table class="table">
		<tr>
			<td>Enter Publisher Name: </td>
			<td><input type="text" name="pubName"/></td>
		</tr>
		<tr>
			<td>Enter Publisher Address: </td>
			<td><input type="text" name="pubAddress"/></td>
		</tr>	
		<tr>
			<td>Enter Publisher Phone: </td>
			<td><input type="text" name="pubPhone"/></td>
		</tr>
		</table>
		<input type="submit"/>
	</form>