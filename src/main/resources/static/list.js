function excluirOrdem() {
    if (confirm("Tem certeza que deseja excluir a ordem de serviço?")) {
        // Se o usuário confirmar, enviar o formulário de exclusão
        document.getElementById("form-excluir").submit();
    }
}

const botaoLimpar = document.getElementById("del");

let form = document.getElementById('formulario');

// Adicione um evento de clique ao botão de limpar
botaoLimpar.addEventListener("click", function() {
    form.reset();
});

