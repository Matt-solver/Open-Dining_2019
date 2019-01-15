	// previous process
		function init() {
			$('#insite').hide();
			$('.tp').hide(); // hide reserve time
			getDistance();
			$('.table').hide();
			$('#inputpay').hide();
			//delay excute
			setTimeout(function() {
				printClock();
				}, 3000);
			
		};
		
		