		//declare array for 'Basket' list
		var basketArr = new Array();
		var mnArr = new Array();
		var prArr = new Array()
		var total = 0;
		
		function bookmenu($mn, $menuImg, $pr) {		
			var pr = Number($pr);
			var filename = $menuImg.substring($menuImg.lastIndexOf("/"), $menuImg.length);
			// menu insert into the Array
			basketArr.push(filename);
			mnArr.push($mn);
			prArr.push(pr);

			// output
			for ( var i = 0; i < 8; i++ ) {
				//'i' == status index by JSTL
				$(".thumbnail" + i).attr("src", "img" + basketArr[i]);
				$('.menuname' + i).val(mnArr[i]);
				$('.product' + i).text(mnArr[i]); //payment
				$('.price' + i).text(prArr[i]); //payment
				
			}
			function add(acc, value) {
				return acc + value;
			}
			//jQuery('.menuname').val(mnArr[*]);		
			var mSum = mnArr.reduce(add, 0);
			var mTot = mnArr.reduce(function(a, b) {return a +' '+ b;});
			$('.menuname').val(mTot);

			// total price by reduce() methods
			var sum = prArr.reduce(add, 0);
			var sum = prArr.reduce(function(a, b) {return a + b;});
			
			var sum = sum.toFixed(2);	//소수점2자리
			//sum = +sum.toFixed(2);	// 뒤에 0 제거
			$('.withdraw').val(sum);
			$('.paysum').text("$"+sum+"USD");
			$('.paysum').val(sum);
			
		}