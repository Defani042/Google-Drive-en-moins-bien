//attendre que la page soit prête
document.addEventListener("DOMContentLoaded", () => {
	
	//création de l'éditeur de texte
    const quill = new Quill('#editor', {
        theme: 'snow'
    });
	
	//récupération du contenu
    const content = document.getElementById("docContent").value || "";
    quill.root.innerHTML = content;
	
	//récupère le bon formulaire 
    const saveForm = document.querySelector("form[name='save']");
	
	//listener pour envoyer le contenu du document
	saveForm.addEventListener("submit", (e) => {
		
		//récupération du contenu
	    const content = quill.root.innerHTML;
		
		//met le contenu dans le champs hidden
	    document.getElementById("hiddenContent").value = content;
	});

});