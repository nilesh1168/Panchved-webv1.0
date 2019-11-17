function getPPage() {
	if(currPageNo>1){
	currPageNo--;

	$.ajax({
		url : "http://localhost:8080/SpringDBMS/admin/ajaxPatient",
		data : "page="+currPageNo,
		success : function(data) {
			loadData(data);
		}

	});
}
}

function getNPage() {
	currPageNo++;
	$.ajax({
		url : "http://localhost:8080/SpringDBMS/admin/ajaxPatient",
		data : "page="+currPageNo,
		success : function(data) {
			loadData(data);
		}
	});
}
function loadData(data) {
	var i,row;
	
	
		$("td").remove();
	for(i=0 ; i< data.length; i++){

		let dyData = "<td><a href=/SpringDBMS/admin/patient/update?patientId="+data[i].patientId+">"+data[i].patientId+"</a></td>"+
		"<td>"+data[i].patientName+"</td>"+
		"<td>"+data[i].gender+"</td>"+
		"<td>"+data[i].bloodGroup+"</td>"+
		"<td>"+data[i].phoneNo+"</td>"+
		"<td>"+data[i].dob+"</td>"+
		"<td>"+data[i].address+"</td>"+
		"<td>"+data[i].district+"</td>"+
		"<td>"+data[i].state+"</td>";
		$('tbody').append("<tr>"+dyData+"</tr>");
	}
	$("#dataTable_info").text("Showing page "+currPageNo);
}