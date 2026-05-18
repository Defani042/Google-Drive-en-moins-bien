//attendre que la page soit prête
document.addEventListener("DOMContentLoaded", () => {
	
	const titre = document.getElementById("titre");
	const adresse = "ws://" + window.location.host + "/Drive/DocumentWebSocket";
	let socket = new WebSocket(adresse);
	
	//Fonction pour se connecter au Websocket
	
	function connectToWebsocket(){
		socket.addEventListener('message', event => {
			let data = event.data;
			let obj = JSON.parse(data);
			
			//On récupère l'id du doc
			const params = new URLSearchParams(window.location.search);
			const id_doc = params.get("id");
			
			if (obj.id == id_doc){
				if (obj.type == "editor"){
					quill.setContents(obj.content);
				}
				else if (obj.type == "chat"){
					let chatbox = document.getElementById("chat-box");
					chatbox.innerHTML += `
					                <div class="notification is-dark">
					                    <strong>${obj.username}</strong><br>
					                    ${obj.content}
					                </div>
					            `;
					
				} else if (obj.type == "join"){
					//On récupère le document actuel pour l'envoyer
					const deltaContent = quill.getContents();
					
					socket.send(JSON.stringify({
						type: "editor",
					    content: deltaContent,
						id: id_doc 
					}));
								
					socket.send(JSON.stringify({
						type: "titre",
						id: id_doc,
						content: titre.value
					}));
				}
				else if (obj.type == "titre"){
					const titre = document.getElementById("titre");
					titre.value = obj.content;
				}
			}
		});
		
		socket.addEventListener('open', event => {
			console.log("Connecté");
			//On récupère l'id du doc
			const params = new URLSearchParams(window.location.search);
			const id_doc = params.get("id");
			
			//On averti toutes les sessions qu'on vient de se connecter afin d'avoir la version la + à jour possbile du doc
			socket.send(JSON.stringify({
							type: "join",
							id: id_doc 
						}));
						
			});
		
		socket.addEventListener('close', event => {
			console.log("Déconnecté");
			});
		
		socket.addEventListener('error', event => {
			console.log("Erreur");
			});
	}
	
	//création de l'éditeur de texte
    const quill = new Quill('#editor', {
        theme: 'snow'
    });
	
	//Si on est en mode lecture, quill est désactivé
	if (lecture) {
	    quill.enable(false);
	}
	
	//récupération du contenu
    const content = document.getElementById("docContent").value || "";
    quill.root.innerHTML = content;
	
	//Connexion au websocket
	connectToWebsocket();
	
	//récupère le bon formulaire 
    const saveForm = document.querySelector("form[name='save']");
	
	//listener pour envoyer le contenu du document
	saveForm.addEventListener("submit", (e) => {
		
		//récupération du contenu
	    const content = quill.root.innerHTML;
		
		//met le contenu dans le champs hidden
	    document.getElementById("hiddenContent").value = content;
	});
	
	//A chaque changement, on y envoit au serveur :
	quill.on('text-change', function(delta, oldDelta, source) {
		//On récupère les changements
	    const deltaContent = quill.getContents();
		
		//On récupère l'id du doc
		const params = new URLSearchParams(window.location.search);
		const id_doc = params.get("id");
		
		//Pour éviter une boucle infini, on rajoute cette condition (L'update automatique du contenu est de source silent)
		if (source == "user"){
			socket.send(JSON.stringify({
						type: "editor",
				        content: deltaContent,
						id: id_doc 
			}));
		}
	});
	
	//Event qui gère l'envoi dynamique du texte
	titre.addEventListener("input", (event) => {
		//On récupère l'id du doc
		const params = new URLSearchParams(window.location.search);
		const id_doc = params.get("id");
		   
		socket.send(JSON.stringify({
			type: "titre",
			id: id_doc,
			content: event.target.value
		}));
	});
	
	window.diffuserMessage = function(){
		const contentMessage = document.getElementById("content").value;
		const user = document.getElementById("username").value;
		const params = new URLSearchParams(window.location.search);
		const id_doc = params.get("id");

		socket.send(JSON.stringify({
			type: "chat",
			content: contentMessage,
			id: id_doc,
			username: user 
		}));
	}
});