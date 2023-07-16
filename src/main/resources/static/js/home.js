const loginSection = document.getElementById('login');
const registerSection = document.getElementById('cadastro');
const toggleRegister = document.getElementById('toggleRegister');
const toggleLogin = document.getElementById('toggleLogin');
var modal = document.getElementById('modal');

toggleRegister.addEventListener('click', function(event) {
    event.preventDefault();
    loginSection.style.display = 'none';
    registerSection.style.display = 'block';
});

toggleLogin.addEventListener('click', function(event) {
    event.preventDefault();
    registerSection.style.display = 'none';
    loginSection.style.display = 'block';
});

document.addEventListener('DOMContentLoaded', function() {
    var mensagemSalvo = "[[${mensagemSalvo}]]";
    if (modal !== null && mensagemSalvo && mensagemSalvo.trim() !== '') {

        modal.style.display = 'block';

    }
});

function closeModal(){

    modal.style.display = 'none';

}