const toolbarLinks = document.querySelectorAll('.toolbar a.nav-link');
const formcliente = document.getElementById('formc');
const formordem = document.getElementById('formo');

var modal = document.getElementById('modal');

toolbarLinks.forEach(link => {
    link.addEventListener('click', event => {
        event.preventDefault();
        alert('Você não pode sair desta página. Salve suas alterações antes de sair.');
    });
});



function voltarordem(){
    let url = window.location.href;
    let userId = url.split('/').slice(-3)[0];
    window.location.href ="/"+userId+"/ordemlist";
}

function  voltarcliente(){
    let url = window.location.href;
    let userId = url.split('/').slice(-3)[0];
    window.location.href = "/"+userId+"/clientelist";}

document.addEventListener('DOMContentLoaded', function() {
    var mensagemSalvo = "[[${mensagemSalvo}]]";
    if (modal !== null && mensagemSalvo && mensagemSalvo.trim() !== '') {

        modal.style.display = 'block';

    }
});

function closeModalordem(){
    let url = window.location.href;
    let userId = url.split('/').slice(-3)[0];
    modal.style.display = 'none';


}
function closeModalcliente(){
    let url = window.location.href;
    let userId = url.split('/').slice(-3)[0];
    modal.style.display = 'none';
    window.location.href ="/"+userId+"/clientelist";

}
function home(){
    let url = window.location.href;
    let userId = url.split('/').slice(-2)[0];
    let redirectUrlperfil = "/" + userId + "/perfil";
    window.location.href = redirectUrlperfil;

}
