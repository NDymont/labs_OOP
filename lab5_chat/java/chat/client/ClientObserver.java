package chat.client;

import chat.Message;

public interface ClientObserver {
    void onLoginSuccess(Client client);

    void onLoginFailure(Client client);

    void onMessageReceive(Client client, Message message);

    void onStop(Client client);
}
