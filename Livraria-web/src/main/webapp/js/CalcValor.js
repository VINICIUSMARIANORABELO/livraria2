function CalcValor(pform, item) {
	var memo = "";
	var valor, cod, qtd;
	var frm = document.getElementById(pform);
	var x = frm.elements.length;
	for(var i=0; i < x; i++){
		var id= frm.elements[i].id;
		if(id){
			console.log('found id = '+id);
			var val= frm.elements[i].value;
			memo += "<br>id = "+id +" valor = "+val;
			if(id == "ValorLivro"){
				valor = val;
			}
			if(id == "codigo"){
				cod = val;
			}
			if(id == "QTD"){
				qtd = val;
			}
		}
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById(item).innerHTML = xhttp.responseText;	
		}
	};
	xhttp.open("GET", "EditarCarrinho?codigo=" + cod +"&QTD="+qtd+"&ValorLivro="+valor+"&acao=remanejar", true);
	xhttp.send();

	
}