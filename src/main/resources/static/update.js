const toolbarLinks = document.querySelectorAll('.toolbar a.nav-link');
const formcliente = document.getElementById('formclienteupdate');
const formordem = document.getElementById('formordemupdate');
const voltarordem = document.getElementById('voltarordem');
const voltarcliente = document.getElementById('voltarcliente');


toolbarLinks.forEach(link => {
    link.addEventListener('click', event => {
        event.preventDefault();
        alert('Você não pode sair desta página. Salve suas alterações antes de sair.');
    });
});

function redirecionarcliente() {
    let url = window.location.href;
    let userId = url.split('/').slice(-2)[0];
    alert("Campos editados com sucesso!");
    window.location.href = "/"+userId+"/clientelist";
}

formcliente.addEventListener("submit", redirecionarcliente);

function redirecionarordem() {
    let url = window.location.href;
    let userId = url.split('/').slice(-2)[0];
    alert("Campos editados com sucesso!");
    window.location.href = "/"+userId+"/ordemlist";
}

formordem.addEventListener("submit", redirecionarordem);

voltarordem.addEventListener("click", () => {
    let url = window.location.href;
    let userId = url.split('/').slice(-2)[0];
    window.location.href ="/"+userId+"/ordemlist";
});

voltarcliente.addEventListener("click", () => {
    let url = window.location.href;
    let userId = url.split('/').slice(-2)[0];
    window.location.href = "/"+userId+"/clientelist";
});
