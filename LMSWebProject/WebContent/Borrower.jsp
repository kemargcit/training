<%@include file="include.html" %>
  <style>
  #cardNo{
 height: 50px;
    width: 400px;  
     position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;

    margin: auto;
    }
#bor{
 height: 200px;
    width: 700px;  
     position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;
text-align: center;

    margin: auto;
    }
  
  </style>
  <%
  %>
  
  <div id="bor">
      ${loginMsg}
 
<form class="form-inline" role="form" method="post"  action="borrowerLogin">
  <label class="aria-describedby">Welcome To the Borrower Section</label>
  <div class="form-group">
    <input placeholder="Enter cardNo" name="cardNo" id="cardNo"class="form-control input-lg" id="inputlg" type="text" ><br>
  </div>
    <br><br><br><br><br><br><br>  <input class="btn-lg btn-info" type="submit" value="Enter">
    
  
</form>
  </div>