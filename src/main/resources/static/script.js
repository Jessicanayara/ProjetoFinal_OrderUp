let form = document.getElementById('formservice');
let modal = document.getElementById('modal');

function limpar(){
    form.reset();
}
form.addEventListener('submit', function(event) {
    event.preventDefault();
    exibirModal();
});

function exibirModal() {
    console.log('oi')
   modal.style.display='block'
}

