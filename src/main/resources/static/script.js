let form = document.getElementById('formservice');
let modal = document.getElementById('modal');

function limpar(){
    form.reset();
}


function exibirModal() {
    console.log('oi')
   modal.style.display='block'
}

document.addEventListener('DOMContentLoaded', function() {
    var mensagemSalvo = "[[${mensagemSalvo}]]";
    if (mensagemSalvo && mensagemSalvo.trim() !== '') {
       var modal = document.getElementById('modal');
        modal.style.display = 'block';
    }
});