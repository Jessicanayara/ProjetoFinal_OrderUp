var modalRetorn = document.getElementById('modalretorn');
var spinner = document.querySelector('.spinner-border');


document.addEventListener('DOMContentLoaded', function() {
    var mensagemSalvo = "[[${mensagemSalvo}]]";
    if (modalRetorn !== null && mensagemSalvo && mensagemSalvo.trim() !== '') {
        modalRetorn.style.display = 'block';
        spinner.classList.remove('d-none');
    }
});

function closeModal() {
    spinner.classList.add('d-none');
    modalRetorn.style.display = 'none';
   let url = window.location.href;


    let hasDeleteCliente = url.includes("deletecliente");
    let hasDeleteOrdem = url.includes("deleteorder");

    if (hasDeleteCliente) {

        let userId = url.split('/').slice(-3)[0];
        let redirectUrlcliente = "/" + userId + "/clientelist";
        window.location.href = redirectUrlcliente;
    } if (hasDeleteOrdem){
        let userId = url.split('/').slice(-3)[0];
        let redirectUrlordem = "/" + userId + "/ordemlist";
        window.location.href = redirectUrlordem;
    }

}
function confirmarExclusao() {
    if (confirm("Tem certeza que deseja excluir o cliente?")) {
        document.getElementById("form-excluir").submit();
    }
}
function home(){
    let url = window.location.href;
    let userId = url.split('/').slice(-2)[0];
    let redirectUrlperfil = "/" + userId + "/perfil";
    window.location.href = redirectUrlperfil;

}
