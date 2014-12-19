package sample.javaee7.chat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatWebSocketEndpoint")
public class ChatWebSocketEndpoint {

  private static final Set<Session> sessions = new HashSet<>();

  @OnOpen
  public void onOpen(Session session) throws IOException {
    //接続イベント処理
    sessions.add(session);
  }

  @OnClose
  public void onClose(Session session) throws IOException {
    //クローズイベント処理
    sessions.remove(session);
  }

  @OnMessage
  public void onMessage(String message) {
    //メッセージ受信イベント処理
    for (Session session : sessions) {
        session.getAsyncRemote().sendText(message);
    }
  }
}
