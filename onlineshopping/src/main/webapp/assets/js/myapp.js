$(function(){
	
	//Solving the active menu problems
	switch(menu){
	
	
		case  'About Us' : 
			       $('#about').addClass('active');
			       break;
		case  'Contact Us' : 
		       $('#contact').addClass('active');
		       break;
		case  'All Products' : 
			
		       $('#listProducts').addClass('active');
		       break;
		default : 
			   if(menu == "Home") break;
		       $('#listProducts').addClass('active');
			   $('#a_'+menu).addClass('active');
		       break;
	}
	
	
	//Code for JQuery Data-Tables
	/*// Create a dataset
	
	var products=[
		
		['1','ABC'],
		['2','EDF'],
		['3','SDF'],
		['4','HFG'],
		['5','GFD'],
		['6','ERE'],
		['7','FDF'],
		['8','DFD']
		
	];
	*/
	var $table =$('#productListTable');
	
	var jsonUrl = '';
	if(window.categoryId == ''){
		jsonUrl = window.contextRoot+ '/json/data/all/products';
		
	}
	else{
		jsonUrl = window.contextRoot+ '/json/data/category/'+window.categoryId+'/products';
	}
	
	//execute the below code only where we have table
	if($table.length){
		//console.log('Iam inside the table');
		$table.DataTable({
			lengthMenu :[[3,5,10,-1],['3 Records','5 Records','10 Records','All']],
			pageLength :5,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns :[
				{
					data : 'code',
					mRender: function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
				},
				{
					data : 'name'
				},
				{
					data : 'brand'
				},
				{
					data : 'unitPrice',
					mRender : function(data,type,row){
						return '&#8377 '+data;
					}
				},
				{
					data : 'quantity'
				},
				{
					data : 'id',
					bSortable : false,
					mRender : function(data,type,row){
						var str = '';
						str+= '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;'
						str+= '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						return str;
						
					}
				}
			]
		});
	}
	
});