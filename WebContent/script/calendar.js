				
	var reArray = new Array();

		$('#basket').click(function() {
			$('#bkTitle').hide();
			$('#insite').show();			
		});

		$('#book').click(function() {
			

		});
		$('#today').click(function() {
			// reset color
			$('.date_f').css('background-color', 'white');
			$('.date_f').css('color', '#aaaaaa');
			// convert color
			$('#today').css('background-color', '#66ccff');
			$('#today').css('color', 'white');
			
			$('.tp').hide();
			$('.timepicker0').show();
		});
		$('#manana').click(function() {
			$('.date_f').css('background-color', 'white');
			$('.date_f').css('color', '#aaaaaa');
			
			$('#manana').css('background-color', '#66ccff');
			$('#manana').css('color', 'white');
			
			$('.tp').hide();
			$('.timepicker1').show();
		});
		$('#third').click(function() {
			$('.date_f').css('background-color', 'white');
			$('.date_f').css('color', '#aaaaaa');
			
			$('#third').css('background-color', '#66ccff');
			$('#third').css('color', 'white');
			
			$('.tp').hide();
			$('.timepicker2').show();

		});
		$('#fourth').click(function() {
			$('.date_f').css('background-color', 'white');
			$('.date_f').css('color', '#aaaaaa');
			
			$('#fourth').css('background-color', '#66ccff');
			$('#fourth').css('color', 'white');
			
			$('.tp').hide();
			$('.timepicker3').show();

		});
		$('#fifth').click(function() {
			$('.date_f').css('background-color', 'white');
			$('.date_f').css('color', '#aaaaaa');
			
			$('#fifth').css('background-color', '#66ccff');
			$('#fifth').css('color', 'white');
		
			$('.tp').hide();
			$('.timepicker4').show();

		});
		$('#sixth').click(function() {	
			$('.date_f').css('background-color', 'white');
			$('.date_f').css('color', '#aaaaaa');
			
			$('#sixth').css('background-color', '#66ccff');
			$('#sixth').css('color', 'white');
			
			$('.tp').hide();
			$('.timepicker5').show();

		});	