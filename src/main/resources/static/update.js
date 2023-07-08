const toolbarLinks = document.querySelectorAll('.toolbar a.nav-link');



toolbarLinks.forEach(link => {
    link.addEventListener('click', event => {
        event.preventDefault();
        alert('Você não pode sair desta página. Salve suas alterações antes de sair.');
    });
});

function redirecionarcliente() {
    let url = window.location.href;
    let userId = url.split('/').slice(-3)[0];
    alert("Campos editados com sucesso!");
    window.location.href = "/"+userId+"/clientelist";
}



function redirecionarordem() {
    let url = window.location.href;
    let userId = url.split('/').slice(-3)[0];
    alert("Campos editados com sucesso!");
    window.location.href = "/"+userId+"/ordemlist";
}



function voltarordem(){
    let url = window.location.href;
    let userId = url.split('/').slice(-3)[0];
    window.location.href ="/"+userId+"/ordemlist";
}

function voltarcliente(){
    let url = window.location.href;
    let userId = url.split('/').slice(-3)[0];

    console.log(userId)
    window.location.href = "/"+userId+"/clientelist";
}
