function excluirOrdem(event) {
    event.preventDefault();

    if (confirm("Tem certeza que deseja excluir a ordem de serviço?")) {
        document.getElementById("form-excluir").submit();
    }
}

