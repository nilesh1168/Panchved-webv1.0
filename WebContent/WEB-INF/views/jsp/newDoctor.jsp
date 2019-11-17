<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:if test="${sessionScope.user==null}">

<c:redirect url="/"></c:redirect>
</c:if>
 
<!DOCTYPE html>
<html style="position:relative; min-height:100%">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Panchaved - New Doctor</title>
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/googlefont.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome-all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/MUSA_panel-table-1.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/MUSA_panel-table.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/Navigation-Clean.css"/>">
</head>

<body id="page-top">
    <div id="wrapper">
        <nav class="navbar navbar-dark flex-shrink-0 align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
            <div class="container-fluid d-flex flex-column p-0">
                <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="/SpringDBMS/admin">
                    <div class="sidebar-brand-text mx-3" style="margin: 0px;padding: 0px;background-size: auto;background-position: left;"><span style="padding: 0px;margin-right: 0px;">Panchaved</span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="nav navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item" role="presentation"></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" href="/SpringDBMS/admin"><i class="fas fa-tachometer-alt"></i><span>Dashboard</span></a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#"><i class="fas fa-user"></i><span>New Doctor</span></a></li>
                    <li class="nav-item"
                        role="presentation"><a class="nav-link" href="/SpringDBMS/admin/doctor"><i class="fas fa-table"></i><span>Show Doctors</span></a></li>
                    <li class="nav-item" role="presentation"></li>
                    <li class="nav-item" role="presentation"></li>
                </ul>
                <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
            </div>
        </nav>
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                    <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                        <form class="form-inline d-none d-sm-inline-block mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            <h3 class="text-dark mb-0">${user}</h3>
                            <h3 class="text-dark mb-0">${success_msg}</h3>
                        </form>
                        <a href="/SpringDBMS/logout"><h4 class="text-dark mb-0" style="text-align:right">Logout</h4></a>
                    </div>
                </nav>
                <form:form method="post" modelAttribute="doc">
                    <div class="container-fluid">
                        <h3 class="text-dark mb-4">New Doctor Profile</h3><h6 style="color:red">(All Fields are Mandatory!)</h6>
                        <div class="row mb-3">
                            <div class="col-lg-4">
                                <div class="card shadow">
                                    <div class="card-header py-3">
                                        <p class="text-primary m-0 font-weight-bold">Basic Details</p>
                                    </div>
    <!-- Basic Details -->
                                    <div class="card-body" style="margin: 0px;">
                                            <div class="form-row">
                                                <div class="col">
                                                    <div class="form-group"><label ><strong>Doctor Id</strong><br></label><form:input id = "docID" path="doctorID" class="form-control" type="text" inputmode="numeric" minlength="1" maxlength="6" required="" autofocus="" placeholder="3-6 digits"/></div>
                                                </div>
                                                <div class="col">
                                                    
                                                </div>
                                            </div>
                                    </div>
                                </div>
                                <br><br><br><br>
                                
                            </div>
                            <div class="col-lg-8">
                                <div class="row mb-3 d-none">
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="card shadow mb-3">
                                            <div class="card-header py-3">
                                                <p class="text-primary m-0 font-weight-bold">Secondary Details</p>
                                                <p class="text-primary m-0 font-weight-bold"></p>
                                            </div>
    <!-- Secondary Details                                         -->
                                            <div class="card-body">
                                              <!--  <form id="f2"> -->
                                                    <div class="form-row">
                                                        <div class="col">
                                                            <div class="form-group"><label for="first_name"><strong> Name</strong></label><form:input id = "docName" path="doctorName" class="form-control" type="text" placeholder="Babu rao" style="margin: -7px;"/></div>
                                                        </div>
                                                        <div class="col">
                                                            <div class="form-group">
                                                                <div class="col"><label for="dob" style="height: 0px;"><strong>Date of Birth</strong></label><form:input id = "docDOB" path="doctorDOB"  class="form-control form-control-lg d-flex justify-content-center align-items-center align-content-center" type="date"
                                                                        name="dob" style="font-size: 16px;margin: 0px;height: 38px;" required=""/></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <div class="col">
                                                            <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable"><label for="qualification">&nbsp;<strong>Qualification</strong></label><form:input id = "docQual" path="doctorQualification" class="form-control" type="text" placeholder="MBBS"  style="margin: -7px;"/></div>
                                                        </div>
                                                        <div class="col">
                                                           
                                                        </div>
                                                    </div>
                                            </div>
                                        </div>
    
                                        <div class="card shadow">
                                            <div class="card-header py-3">
                                                <p class="text-primary m-0 font-weight-bold">Address</p>
                                            </div>
    <!-- Address -->
                                            <div class="card-body">
                                                    <div class="form-group"><label for="address"><strong>Address</strong></label><form:input id = "docAdd" path="doctorAddress"  class="form-control" type="text" /></div>
                                                    <div class="form-row">
                                                        <div class="col">
                                                            <div class="form-group"><label for="city"><strong>City</strong></label><form:input id = "docCity" path="doctorCity"  class="form-control" type="text" /></div>
                                                        </div>
                                                        <div class="col">
                                                        
                                                        </div>
                                                    </div>
                                                    <div class="form-group"><button class="btn btn-primary" type="submit">Save</button></div>
                                               
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>
                
            </div>
            <footer class="bg-white sticky-footer" >
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"><span>Copyright Panchved &copy; Clinic 2019</span></div>
                </div>
            </footer>
        </div>
</div>
        <script src="<c:url value="/assets/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/assets/bootstrap/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/assets/js/chart.min.js"/>"></script>
        <script src="<c:url value="/assets/js/bs-charts.js"/>"></script>
        <script src="<c:url value="/assets/js/theme.js"/>"></script>
        <script src="<c:url value="/assets/js/jquery.easing.js"/>"></script>
        <script src="<c:url value="/assets/js/search.js"/>"></script>
        <script type="text/javascript">
        $(document).ready(function(){
        	//global vars
        	var form = $("#newdocform");
        	
        	var id= $("#docID");
        	var name = $("#docName");
        	var dob= $("#docDOB");
        	var qual = $("#docQual");
        	var add= $("#docAdd");
        	var city = $("#docCity");
        	//On Submitting
        	form.submit(function(){
        	    if(id.val().length==0 ){
        	                    alert('Please enter the ID');
        	        return false;
        	                    }
        	    else
        	    {
        	                if(name.val().length==0)
        	                    {
        	                        alert('Please enter the Name');
        	                        return false;
        	                    }
        	                 else
        	                	 {
        	                	 if (dob.val().length()==0) 
        	                	 	{
        	                			alert('Please enter the Date Of Birth');
         	                        	return false;
									} 
        	                	 else 
        	                	 {
									if(qual.val().length()==0){
										alert('Please enter the Qualification');
	         	                        return false;
									}
									else {
										if (add.val().length()==0) {
											alert('Please enter the Address');
		         	                        return false;
										} else {
											if (city.val().length()==0) {
												alert('Please enter the City');
			         	                        return false;
											}
										}
									}	
         	                        
								}
        	                	 }
						return true;
        	    }
        	});
        	});
        </script>
</body>

</html>
