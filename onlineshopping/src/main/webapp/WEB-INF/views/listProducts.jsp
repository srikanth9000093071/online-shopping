<div class="container">
	<div class="row">

		<!-- would be display sidebar -->
		<div class="col-md-3">
			<%@ include file="./shared/sidebar.jsp"%>

		</div>

		<!-- to display actual content -->
		<div class="col-md-9">
			<!-- added BreadCrumb component -->
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts eq true}">
					<script>
					window.categoryId='';
					</script>
					
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>

						</ol>
					</c:if>
					<c:if test="${userClickCategoryProducts eq true}">
					<script>
					window.categoryId='${category.id}';
					</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>

						</ol>
					</c:if>
				</div>




			</div>
		
		<div class="row">
	
	<div class="col-xs-12">
	
	<div class="container-fluid">
					
		<div class="table-responsive">
				<table id="productListTable" class="table table-striped table-borderd">
	<thead>
	<tr>
			<th></th>
			<th>name</th>
			<th>Brand</th>
			<th>Price</th>
			<th>Qty. Availabile</th>
			<th></th>
	</tr>
	</thead>
	
	<tfoot>
	<tr>
			<th></th>
			<th>name</th>
			<th>Brand</th>
			<th>Price</th>
			<th>Qty. Availabile</th>
			<th></th>
	</tr>
	</tfoot>
	
	</table>		
						
		</div>
	</div>
	
	
	
	</div>
	
	</div>
		
		
		</div>

	


	</div>


</div>