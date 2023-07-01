
document.addEventListener("DOMContentLoaded", function () {

    console.log("dentro")

    const toolbarLinks = document.querySelectorAll(".toolbar-link");


    toolbarLinks.forEach(function (link) {
        link.setAttribute("disabled", "disabled");
        link.style.pointerEvents = "none";
        link.style.cursor = "default";
    });


    function habilitarLinksToolbar() {
        toolbarLinks.forEach(function (link) {
            link.removeAttribute("disabled");
            link.style.pointerEvents = "auto";
            link.style.cursor = "pointer";
        });
    }


    const formulario = document.querySelector("form");
    formulario.addEventListener("submit", function () {
        habilitarLinksToolbar();
    });


    const botaoLimpar = document.getElementById("del");
    botaoLimpar.addEventListener("click", function () {
        habilitarLinksToolbar();
    });
});