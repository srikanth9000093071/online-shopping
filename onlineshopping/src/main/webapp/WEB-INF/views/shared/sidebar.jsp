<p class="lead">Shop Name</p>
<div class="list-group">
	<c:forEach var="category" items="${categories}">
		<a href="${contextRoot}/show/category/${category.id}/products" class="list-group-item" id="a_${category.name}">${category.name}</a>
	</c:forEach>
</div>