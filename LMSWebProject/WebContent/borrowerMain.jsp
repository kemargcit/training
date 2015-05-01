<%@page import="com.gcit.lms.service.AdministratorService"%>
<%@page import="com.gcit.lms.domain.Borrower"%>
<%@page import="com.gcit.lms.domain.BookLoan"%>
<%@page import="java.util.List"%>

<%@page import="com.gcit.lms.service.BorrowerService"%>

<%@include file="include.html" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
             <link href="resources/styles.css" rel="stylesheet">
             <%
//allow access only if session exists
String user = null;
if(session.getAttribute("user") == null){
    response.sendRedirect("Borrower.jsp");
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}

Borrower borrower= null;
if(user != null){
borrower = new BorrowerService().getBorrowerByCardNo(Integer.parseInt(user));
 List<BookLoan> bookLoans = new BorrowerService().readAllByBorrower(Integer.parseInt(user));
		 
request.setAttribute("borrower", borrower);
request.setAttribute("bookloans", bookLoans);

}
    %>
<div style="text-align: right;">
Hi  ${borrower.borrowerName}!
<form action="borrowerLogout" method="post">
<input class="btn" type="submit" value="Logout" >
</form>

</div>
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










