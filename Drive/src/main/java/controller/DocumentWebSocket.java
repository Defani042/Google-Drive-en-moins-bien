package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;


@ServerEndpoint("/DocumentWebSocket")
public class DocumentWebSocket {
	static List<Session> mesSessions = new ArrayList<>();
	
	@OnOpen
    public void onOpen(Session session) {
        System.out.println("Utilisateur connecté : " + session.getId());
        mesSessions.add(session);
        try {
			session.getBasicRemote().sendText(
					"Vous êtes connecté" // Texte à l'envoi
			);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Utilisateur déconnecté : " + session.getId());
		mesSessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Reçu : " + message);
        //sendUpdateToAll(session);
    }

    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
    }
}

