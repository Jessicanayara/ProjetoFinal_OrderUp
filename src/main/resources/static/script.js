let form = document.getElementById('formservice');
let formcliente = document.getElementById('formc');
var modal = document.getElementById('modal');

function limpar(){
    form.reset();
}

document.addEventListener('DOMContentLoaded', function() {
    var mensagemSalvo = "[[${mensagemSalvo}]]";
    if (modal !== null && mensagemSalvo && mensagemSalvo.trim() !== '') {

        modal.style.display = 'block';
        form.reset();
    }
});

function closeModal(){
    modal.style.display = 'none';
    form.reset();

}