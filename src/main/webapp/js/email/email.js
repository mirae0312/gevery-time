
var contents =$('#email03')
$('#selectEmail').change(function(){
	 $("#selectEmail option:selected").each(function () { 
		if($(this).attr("name")== "email03"){ 
			$("#email02").val(contents.val());  $("#email02").attr("disabled",false); 
			 }else{ $("#email02").val($(this).text()); 
		 $("#email02").attr("disabled",true); 
	 } 
		}); 
	
	});
	
