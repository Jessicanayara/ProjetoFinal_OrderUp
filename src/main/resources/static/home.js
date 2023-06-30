const loginSection = document.getElementById('login');
const registerSection = document.getElementById('cadastro');
const toggleRegister = document.getElementById('toggleRegister');
const toggleLogin = document.getElementById('toggleLogin');

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