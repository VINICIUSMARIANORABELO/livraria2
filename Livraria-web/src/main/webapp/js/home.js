document.querySelector('#q').onfocus = function(){
	document.querySelector('#form-busca').style.background='#dcdcdc';
}

document.querySelector('#form-busca').onsubmit = function(){
	if(document.querySelector('#q').value == ''){
		document.querySelector('#form-busca').style.background='red';
		return false;
	}
}
