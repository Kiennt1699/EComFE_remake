package API;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import android.util.Log;

public class ChatSocketManager {
    private OkHttpClient client;
    private WebSocket webSocket;

    public void connectWebSocket(String url, WebSocketListener listener) {
        client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        webSocket = client.newWebSocket(request, listener);

        // client.dispatcher().executorService().shutdown();
    }
    public void sendMessage(String message) {
        if (webSocket != null) {
            webSocket.send(message);
        }
    }

    public void closeWebSocket() {
        if (webSocket != null) {
            webSocket.close(1000, "Closing connection");
        }
        if (client != null) {
            client.dispatcher().executorService().shutdown(); // Cleanup OkHttp resources
        }
    }
}
