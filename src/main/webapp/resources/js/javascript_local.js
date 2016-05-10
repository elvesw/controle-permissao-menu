PrimeFaces.locales['pt'] = {
    closeText: 'Fechar',
    prevText: 'Anterior',
    nextText: 'Próximo',
    currentText: 'Começo',
    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun', 'Jul','Ago','Set','Out','Nov','Dez'],
    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb'],
    dayNamesMin: ['D','S','T','Q','Q','S','S'],
    weekHeader: 'Semana',
    firstDay: 0,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Só Horas',
    timeText: 'Tempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    ampm: false,
    month: 'Mês',
    week: 'Semana',
    day: 'Dia',
    allDayText : 'Todo o Dia'
};


function alteraMaiusculo(){
	var valor = document.getElementById("campo").texto;
	var novoTexto = valor.value.toUpperCase();
	valor.value = novoTexto;
	}

function toUpperCaseInput() {
	var inputs = document.getElementsByClassName('ui-column-filter');
	var i;
	for (i=0; i<inputs.length; i++){
	    input[i].onkeyup = function(){
	    	var novoTexto = input[i].value.toUpperCase();
	    	input[i].value = novoTexto;
	    }
	}
}

function configurarMoeda() {
	$(".moeda").maskMoney({
		showSymbol : true,
		symbol : "R$ ",
		decimal : ",",
		thousands : ".",
		allowZero : true
	});
	/*desligando cache dos formularios*/
	$('input').attr('autocomplete','off');  
}


function sair(){
	judas = window.open(window.location,"_self")
	judas.close();
}

function fecharAba(){
	setTimeout('sair();',5000);
}

$(document).ready(function() {
	configurarMoeda();
});



