<%@include file="include.html" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
             <link href="resources/styles.css" rel="stylesheet">
         
<div class="panel panel-default">
   <div class="panel-heading">
      <h3 class="panel-title">
         Borrower Info
      </h3>
   </div>
   <div class="panel-body">
   <table style="width:100%">
   <tr>
    <td style="width:25%">
    CardNo: ${borrower.cardNo}<br>
Name: ${borrower.borrowerName}<br>
Address: ${borrower.borrowerAddress}<br>
PhoneNumber: ${borrower.borrowerPhone}<br>
    
    </td>
    
    <td style="width:75% ;text-align: right;">
        <h5>Books Out </h5>
    <h5>title|due date</h5><br>
<c:forEach items="${bookloans}" var="b">
            
                 <c:out value="${b.book.title}"/> |<c:out value="${b.dateDue}"/><br>
            
        </c:forEach>
</td> 
  </tr>
 
</table> 
   </div>
</div>

<div style=" width: 50%;
    margin: 0 auto;">
<a onclick="javascript:location.href='CheckOutBook.jsp?cardNo=${borrower.cardNo}';" class="checkoutbtn">CHECK OUT BOOK</a>
<a onclick="javascript:location.href='Returns?cardNo=${borrower.cardNo}';" class="returnbtn">  RETURN BOOKS</a>

</div>










