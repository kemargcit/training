<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdministratorService"%>
<%
	List<Author> authors = null;
int pageNo = 1;
int pageSize = 10;
int count = 0;



 if (request.getAttribute("authors") != null) {
	count = (int) request.getAttribute("count");
 authors = (List<Author>) request.getAttribute("authors");//search
}else{
		authors = new AdministratorService().getAuthors();
		count = new AdministratorService().getAuthorCount();

	}
	/* String searchString = (String)request.getAttribute("searchString");
	authors = new AdministratorService().getAuthors();
	count = new AdministratorService().getAuthorCount(); */
%>

<%@include file="include.html"%>

<script type="text/javascript">
<!--
	//-->
	function pageAuthors(pageNo) {
		var pageCount=0;
		 $.ajax({
		url : "getCountForSearch",
		data : {
			searchString : $("#searchString").val(),

		}
	}).done(function(data) {
         
          var pageSize = $("#pageSize").val();
          
          pageCount = data/pageSize;
			//alert(pageCount);

		
	});
		if(pageNo==-1){
			var curpage = $("#pageNo").val();
			$("#pageNo").val(curpage-1)
			$.ajax({
				url : "pageAuthors",
				data : {
					searchString : $("#searchString").val(),
					pageNo : $("#pageNo").val(),
					pageSize : $("#pageSize").val()
				}
			}).done(function(data) {
				$("#resultSection").html(data);
			});
		}
		else if(pageNo==-2){
			    alert(pageCount);
			var curpage = $("#pageNo").val();
			if((parseInt(curpage)+1)>pageCount ){
				$("#pageNo").val(parseInt(curpage));
				
			}else{
 			$("#pageNo").val(parseInt(curpage)+1);
			}
 			$.ajax({
				url : "pageAuthors",
				data : {
					searchString : $("#searchString").val(),
					pageNo : $("#pageNo").val(),
					pageSize : $("#pageSize").val()
				}
			}).done(function(data) {
				$("#resultSection").html(data);
			});
		}
		
		else{
		$("#pageNo").val(pageNo);
		$.ajax({
			url : "pageAuthors",
			data : {
				searchString : $("#searchString").val(),
				pageNo : $("#pageNo").val(),
				pageSize : $("#pageSize").val()
			}
		}).done(function(data) {
			$("#resultSection").html(data);
		});
		}
	}

	function searchAuthors() {
		$.ajax({
			url : "pageAuthors",
			data : {
				searchString : $("#searchString").val(),
				pageNo : 1,
				pageSize : $("#pageSize").val()
			}
		}).done(function(data) {
			$("#resultSection").html(data);
		});
		
	}

	/* function deleteAuthor(id) {
		location.href = 'deleteAuthor?authorId=' + id;
		$("#myModald").modal('show');

	}
	function editAuthor(id) {
		href = 'editAuthor.jsp?authorIdToEdit' + id;

	}

	function editAuthor() {
		$('a[rel=popover]').popover({
			html : 'true',
			placement : 'left'
		})

	}
 */
	$(document).ready(function() {
		$("#result").fadeOut(2500);

	});
</script>

<div id="result" style="background-color: blue">
	<h4>${result}</h4>
</div>

<nav>
	<ul class="pagination">
	
           		<li><a href="javascript:pageAuthors(-1);"><i class="glyphicon glyphicon-chevron-left"></i></a></li>
           
		<%
			int end = (count / pageSize);
			if (count % pageSize != 0)
				end++;
			for (int i = 1; i <= end; i++) {
		%>
		<li><a href="javascript:pageAuthors(<%=i%>);"><%=i%></a></li>
		<%
			}
		%>
           		<li><a href="javascript:pageAuthors(-2);"><i class="glyphicon glyphicon-chevron-right"></i></a></li>

	</ul>
</nav>
	<input type="text" id="searchString" name="searchString"
		value="${searchString}" class="col-md-8"
		placeholder="Enter string to search Author Name" /><input
		type="button" onclick="javascript:searchAuthors();" value="Search!" />
	<input type="hidden" id="pageNo" name="pageNo" value="<%=pageNo%>" />
	<input type="hidden" id="pageSize" name="pageSize"
		value="<%=pageSize%>" />

<table class="table" id="resultSection">
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
			<div>
				<div>
					<a href="#" class="btn btn-xs btn-info" rel="popover" data-togle='modal'
						data-content='
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
</form>'
						data-placement="top" data-original-title="Fill in form"> <i
						class="glyphicon glyphicon-edit"></i>Edit
					</a>

				</div>
			</div>

		</td>
		<td>
			<form method="get" action="deleteAuthor" accept-charset="UTF-8"
				style="display: inline">
                  
				<input type="hidden" name="authorId" value="<%=a.getAuthorId()%>">

				<button class="btn btn-xs btn-danger" type="button"
					data-toggle="modal" data-target="#confirmDelete"
					data-title="Delete Author"
					data-message="Are you sure you want to delete this Author ?">
					<i class="glyphicon glyphicon-trash"></i> Delete
				</button>
				
			</form> 
		</td>
		
	</tr>
					                  <%@include file="delete_confirm.html"%>
	
	<%
		}
	%>
</table>
<div id="myModal1" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>


