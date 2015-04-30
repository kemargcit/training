<%@include file="include.html" %>
             <link href="resources/styles.css" rel="stylesheet">
             
   <script src="resources/script2.js"></script>

${result}


<div id='cssmenu'>
<ul>
   <li class='active'><a href='#'><span>Welcome to Administrator section
Pick the action from below:</span></a></li>
   <li class='has-sub'><a href='#'><span>Authors</span></a>
      <ul>
         <li><a href="addAuthor.jsp">Add Author</a></li>
         <li class='last'><a href="listAuthors.jsp">List Authors</a></li>
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span>Books</span></a>
      <ul>
         <li><a href="addBook.jsp">Add Book</a></li>
         <li class='last'><a href="listbooks.jsp">List Book</a></li>
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span>Publishers</span></a>
      <ul>
         <li><a href="addpublisher.jsp">Add Publisher</a></li>
         <li class='last'><a href="ListPublishers.jsp">List Publisher</a></li>
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span>Borrowers</span></a>
      <ul>
         <li><a href="addborrower.jsp">Add Borrower</a></li>
         <li class='last'><a href="ListBorrowers.jsp">List Borrower</a></li>
      </ul>
   </li>
   
   
    <li class='has-sub'><a href='#'><span>Book Loan</span></a>
      <ul>
         <li class='last'><a href="UpdateBooKLoan.jsp">Over-ride Due Date for a Book Loan</a></li>
      </ul>
   </li> 
</ul>
</div>


