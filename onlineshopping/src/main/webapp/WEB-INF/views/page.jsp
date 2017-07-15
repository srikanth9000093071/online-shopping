
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<spring:url var="css" value="/resources/css"/>
<spring:url var="js" value="/resources/js"/>
<spring:url var="images" value="/resources/images"/>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>OnlineShopping-${title}</title>
    
    <script>
    	window.menu='${title}';
    	window.contextRoot="${contextRoot}";
    </script>

    <!-- Bootstrap Core CSS -->
    <link href="${css}/bootstrap.min.css" rel="stylesheet">
    
    <!-- Bootstrap Readable Theme-->
    <link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">
    
     <!-- Bootstrap DataTables-->
    <link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${css}/myapp.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="wrapper">
			    <!-- Navigation -->
			   <%@ include file="./shared/navbar.jsp" %>
			
			    <!-- Page Content -->
			    
			<div class="content">
			    
			    <!-- Loading the Home content -->
			    <c:if test="${userClickHome == true}">
			   	 <%@ include file="home.jsp" %>
			    </c:if>
			    
			    <!-- Loads Only when user clicks about -->
			    <c:if test="${userClickAbout == true}">
			   	 <%@ include file="about.jsp" %>
			    </c:if>
			    
			     <!-- Loads Only when user clicks contact -->
			    <c:if test="${userClickContact == true}">
			   	 <%@ include file="contact.jsp" %>
			    </c:if>
			    
			    <!-- Loads Only when user clicks userClickAllProducts or userClickCategoryProducts  -->
			    <c:if test="${userClickAllProducts == true || userClickCategoryProducts == true }">
			   	 <%@ include file="listProducts.jsp" %>
			    </c:if>
			    
			     <!-- Loads Only when user clicks userClickShowProduct  -->
			    <c:if test="${userClickShowProduct == true}">
			   	 <%@ include file="singleProduct.jsp" %>
			    </c:if>
			    
			     <!-- Loads Only when user clicks userClickManageProducts  -->
			    <c:if test="${userClickManageProducts == true}">
			   	 <%@ include file="manageProducts.jsp" %>
			    </c:if>
			    
			    <!-- /.container -->
			</div>
			    
			    <!-- FOOTER COMES HERE -->
			    <%@ include file="./shared/footer.jsp" %>
			    
			    <!-- /.container -->
			
			    <!-- jQuery -->
			    <script src="${js}/jquery.js"></script>
			    
			    <!-- jQuery Validator-->
			    <script src="${js}/jquery.validate.js"></script>
			
			    <!-- Bootstrap Core JavaScript -->
			    <script src="${js}/bootstrap.min.js"></script>
			    
			    <!-- DATA TABLES Plug-in -->
			     <script src="${js}/jquery.dataTables.js"></script>
			     
			     <!-- DATA TABLES Boostrap script -->
			     <script src="${js}/dataTables.bootstrap.js"></script>
			     
			      <!--Boobox.js -->
			     <script src="${js}/bootbox.min.js"></script>
			    
			    <!-- Self Coded JavaScript -->
			     <script src="${js}/myapp.js"></script>
			     
			    
			    
</div>
</body>

</html>
