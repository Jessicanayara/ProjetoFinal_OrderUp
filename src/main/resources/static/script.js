var clienteInput = document.getElementById('cliente');
var clienteIdInput = document.getElementById('cliente-id');
var clienteList = document.getElementById('cliente-list');

clienteInput.addEventListener('input', function() {



    var termo = clienteInput.value;
    if (termo.trim().length >= 3) {
        buscarClientes(termo);
    } else {
        clienteList.innerHTML = '';
    }
});

function buscarClientes(termo) {
    var url = window.location.href;
    var userId = url.split('/').slice(-2)[0];
    console.log(url,userId)
    var url = '/'+userId+'/buscar-cliente?termo=' + encodeURIComponent(termo);

    fetch(url)
        .then(response => response.json())
        .then(data => exibirClientes(data))
        .catch(error => console.error(error));
}

function exibirClientes(clientes) {
    clienteList.innerHTML = '';

    if (clientes.length > 0) {
        clientes.forEach(function (cliente) {
            var li = document.createElement('li');
            li.textContent = cliente.nome;
            li.classList.add('list-group-item');

            li.addEventListener('click', function () {
                selecionarCliente(cliente.id, cliente.nome);
            });

            clienteList.appendChild(li);
        });
    } else {
        var li = document.createElement('li');
        li.textContent = 'Nenhum cliente encontrado';
        li.classList.add('list-group-item', 'disabled');

        clienteList.appendChild(li);
    }
}
function selecionarCliente(clienteId, clienteNome) {
    clienteInput.value = clienteNome;
    clienteIdInput.value = clienteId;
    clienteList.innerHTML = '';
}



const botaoLimpar = document.getElementById("del");

let form = document.getElementById('formulario');

// Adicione um evento de clique ao botão de limpar
botaoLimpar.addEventListener("click", function() {
    form.reset();
});
