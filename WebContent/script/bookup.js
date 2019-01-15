function setCookie(c_name,value,exdays)
{
   var exdate=new Date();
   exdate.setDate(exdate.getDate() + exdays);
   var c_value=escape(value) + ((exdays==null) ? "" : ("; expires="+exdate.toUTCString()));
   document.cookie=c_name + "=" + c_value;
}

function getCookie(c_name)
{
   var i,x,y,ARRcookies=document.cookie.split(";");
   for (i=0; i<ARRcookies.length; i++)
   {
      x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
      y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
      x=x.replace(/^\s+|\s+$/g,"");
      if (x==c_name)
      {
        return unescape(y);
      }
   }
}

/* table numbers for bookup */
	$('.t1').click(function(){
		var a = $('.t1').val();
		$('.t_num').val("Table No."+a);
		$('.ptn').text(a);
		alert('Selected No.'+a+'table')
		$('.t1').hide();
		
	});
	
	$('.t2').click(function(){
		var a = $('.t2').val();
		$('.t_num').val("Table No."+a);
		$('.ptn').text(a);
		alert('Selected No.'+a+'table')
		$('.t2').hide();

	});
	
	$('.t3').click(function(){
		var a = $('.t3').val();
		$('.t_num').val("Table No."+a);
		$('.ptn').text(a);
		alert('Selected No.'+a+'table')
		$('.t3').hide();

	});
	
	$('.t4').click(function(){
		var a = $('.t4').val();
		$('.t_num').val("Table No."+a);
		$('.ptn').text(a);
		alert('Selected No.'+a+'table')
		$('.t4').hide();

	});
	
	$('.t5').click(function(){
		var a = $('.t5').val();
		$('.t_num').val("Table No."+a);
		$('.ptn').text(a);
		alert('Selected No.'+a+'table')
		$('.t5').hide();

	});
	
	$('.t6').click(function(){
		var a = $('.t6').val();
		$('.t_num').val("Table No."+a);
		$('.ptn').text(a);
		alert('Selected No.'+a+'table')
		$('.t6').hide();

	});
	
	$('.t7').click(function(){
		var a = $('.t7').val();
		$('.t_num').val("Table No."+a);
		$('.ptn').text(a);
		alert('Selected No.'+a+'table')
		$('.t7').hide();

	});
	
	$('.t8').click(function(){
		var a = $('.t8').val();
		$('.t_num').val("Table No."+a);
		$('.ptn').text(a);
		alert('Selected No.'+a+'table')
		$('.t8').hide();

	});
	
	$('.t9').click(function(){
		var a = $('.t9').val();
		$('.t_num').val("Table No."+a);
		$('.ptn').text(a);
		alert('Selected No.'+a+'table')
		$('.t9').hide();

	});
	
	
	/*
	 * table show when clicked reserve box 
	 * 
	 * */
	$('#reserve').click(function() {
		$('.table').show();		
	});

	/*	input time	*/
//	$('.time').click(function(){
//		var a = $('.time').val();
//		$('#reserve_time').val(a);
//		$('.reserve_time').val(a);		
//		$('.time').hide();
//	});
	
	//onclick TimePicker
	function time(t) {
		var a = t
		$('.reserve_label').val('book at');
		$('.re_time').val(a);
		$('.reserve_time').val(a);
		$('.time').hide();
		$('.date').hide();
	}
	/*	input day	*/
	$('#today').click(function(){
		var a = 'today';
		$('.re_day').val(a);
		$('.reserve_day').val(a);
		$('.day').val(a);
	});
	$('#manana').click(function(){
		var a = 'manana';
		$('.re_day').val(a);
		$('.reserve_day').val(a);
	});
	$('#third').click(function(){
		var a = 'third';
		$('.re_day').val(a);
		$('.reserve_day').val(a);
	});
	$('#fourth').click(function(){
		var a = 'fourth';
		$('.re_day').val(a);
		$('.reserve_day').val(a);
	});
	$('#fifth').click(function(){
		var a = 'fifth';
		$('.re_day').val(a);
		$('.reserve_day').val(a);
	});
	$('#sixth').click(function(){
		var a = 'sixth';
		$('.re_day').val(a);
		$('.reserve_day').val(a);
	});
