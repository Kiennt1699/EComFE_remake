package API;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import android.util.Log;

import androidx.annotation.NonNull;

public class ChatSocket extends WebSocketListener {
    private static final String TAG = "ChatSocket";

    @Override
    public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
        Log.d(TAG, "Connection opened");
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
        Log.d(TAG, "Received message: " + text);
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, ByteString bytes) {
        Log.d(TAG, "Received bytes: " + bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, @NonNull String reason) {
        Log.d(TAG, "Closing connection: Code " + code + ", Reason: " + reason);
        webSocket.close(code, reason);
    }

    @Override
    public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
        Log.d(TAG, "Connection closed: Code " + code + ", Reason: " + reason);
    }

    @Override
    public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, Response response) {
        Log.e(TAG, "Connection failed", t);
    }
}