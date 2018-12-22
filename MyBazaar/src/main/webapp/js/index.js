/**
 * http://usejsdoc.org/
 */

$(document).ready(function() {
    console.log( "ready!" );
    $.ajax({
		  url: "http://localhost:2018/MyBazaar/webapi/shops",
		  type: "GET",
		  dataType: "json",
		  cache: false,
		  success: function(data){
			  console.log(data);
			  var trHTML;
		   for (var i = 0; i < data.length; i++) {
			   console.log(data[i].shopName);
			   trHTML += '<tr><td>' + data[i].shopName + '</td><td>' + data[i].pincode + '</td><td>' + data[i].shopId + '</td></tr>';
		   }
		   $('#records_table').append(trHTML);
		  }
	});
});