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
let cepInput = document.getElementById('cep');
let cepErro = document.getElementById('cep-erro');
let enderecoInput = document.getElementById('endereco');
let cidadeInput = document.getElementById('cidade');
let estadoInput = document.getElementById('estado');

cepInput.addEventListener('input', function() {
    validarcep();});

function validarcep(){

    let cep = cepInput.value.replace(/\D/g, '');


    fetch(`https://viacep.com.br/ws/${cep}/json/`)
        .then(response => response.json())
        .then(data => {
            if (data.erro) {
                cepErro.style.display = 'block';
            } else {
                cepErro.style.display = 'none';
                enderecoInput.value = data.logradouro;
                cidadeInput.value = data.localidade;
                estadoInput.value = data.uf;

            }
        })
}