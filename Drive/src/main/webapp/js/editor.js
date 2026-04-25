document.addEventListener("DOMContentLoaded", () => {

    const quill = new Quill('#editor', {
        theme: 'snow'
    });

    // charger contenu existant
    const content = document.getElementById("docContent").value;
    quill.root.innerHTML = content;

    // sauvegarde
    document.querySelector("form").addEventListener("submit", () => {
        document.getElementById("hiddenContent").value = quill.root.innerHTML;
    });

});