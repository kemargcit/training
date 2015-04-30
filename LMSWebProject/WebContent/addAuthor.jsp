<%@include file="include.html" %>

<style>
#bor{
 height: 100px;
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

<div id="bor">
<script type="text/javascript">
$( document ).ready(function() {
    $("#result").fadeOut(2500);

 $("#sub").click(function(){

	  // Get the Name value and trim it
	    var name = $.trim($('#authorName').val());

	    // Check if empty of not
	    if (name  === '') {
	        alert('Add an author Name .');
	        return false;
	    }
 });

});

</script>
<div id="result" style="background-color: blue;
">
<h4>${result}</h4>
</div>
<br><br>
	<form class="form-inline" role="form" action="addAuthor" method="post">
	<label class="aria-describedby">Add Author</label><br>
  <div class="form-group">
		<input placeholder="Enter Author Name" class="form-control input-md" type="text" id="authorName" name="authorName"/>
		</div>
<button id="sub" type="submit" class="btn btn-primary">
  <i class="glyphicon glyphicon-floppy-disk"></i> Enter
</button>	</form>
</div>