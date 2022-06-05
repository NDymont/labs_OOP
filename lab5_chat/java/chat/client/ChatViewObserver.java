package chat.client;

public interface ChatViewObserver {
    void onUserInput(ChatView view, String input);
}
