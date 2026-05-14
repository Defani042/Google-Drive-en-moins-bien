package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Utilisateur déconnecté : " + session.getId());
		mesSessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Reçu : " + message);
        for (Session s: mesSessions) {
        	if (!s.equals(session)) {
        		try {
    				s.getBasicRemote().sendText(message);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
        }
    }

    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
    }
}

