let form = document.getElementById('formservice');
var modal = document.getElementById('modal');

function limpar(){
    form.reset();
}

document.addEventListener('DOMContentLoaded', function() {
    var mensagemSalvo = "[[${mensagemSalvo}]]";
    if (modal !== null && mensagemSalvo && mensagemSalvo.trim() !== '') {

        modal.style.display = 'block';
    }
});

function closeModal(){
    modal.style.display = 'none';
    form.reset();

}
function home(){
    let url = window.location.href;
    let userId = url.split('/').slice(-2)[0];
    let redirectUrlperfil = "/" + userId + "/perfil";
    window.location.href = redirectUrlperfil;

}