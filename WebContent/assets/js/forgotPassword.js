$(function(){
	var id = $("#userID");
	var secAns= $("#securityAnswer");
	
	$('#getpassword').hide();
	$('#forgotPassword').click(
			function(){
				if(id.val().length==0 ){
                    alert('Please enter the User ID');
                    	return false;
                    }
				else
				{
					if(secAns.val().length==0)
                    	{
                        	alert('Please enter the Security Answer');
                        	return false;
                    	}

					else   
                    {
						$.ajax({
							url: "/SpringDBMS/forgotPassword",
							type : "post",
							data : {'userID':$('#userID').val(),'secAnswer': $('#securityAnswer').val()}, 
							success : function(data){
								console.log(data);
								$('#getpassword').show();
								$('#getpassword').val(data);
							}
//						
						});
                    }
				}
				
				
			});
}
);