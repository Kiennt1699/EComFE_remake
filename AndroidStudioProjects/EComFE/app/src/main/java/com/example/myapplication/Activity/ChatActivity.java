package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import API.ChatSocket;
import API.ChatSocketManager;
import Adapter.ChatAdapter;
import Domain.ChatMessage;
import Domain.User;
import LayoutObject.ChatNotification;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


public class ChatActivity extends AppCompatActivity {

    private ImageView sendButton;
    private ChatSocketManager chatSocketManager;
    private EditText input;
    private List<ChatMessage> messages = new ArrayList<>();
    private ChatAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        findViewById(R.id.backBtn).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
        RecyclerView messagesView = (RecyclerView)findViewById(R.id.recyclerViewMessages);
        messagesView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new ChatAdapter(messages,User.getCurrentUser().getUserId());
        messagesView.setAdapter(adapter);
        Log.d("DEBUG",User.getCurrentUser().getUserId());
        sendButton = findViewById(R.id.sendButton);
        input = findViewById(R.id.editTextMessage);
        ((TextView)findViewById(R.id.backbar_title)).setText("Chat room");
        AppCompatActivity context = this;
        chatSocketManager = new ChatSocketManager();
        chatSocketManager.connectWebSocket(
                String.format("ws://reinir.mooo.com:5000/chat/%s", User.getCurrentUser().getUserId()),
                new WebSocketListener() {
                    @Override
                    public void onMessage(WebSocket webSocket, String text) {
                        messages.add(new ChatMessage(text, true));
                        runOnUiThread(() -> {
                            ChatNotification.showNotification(context,"Staff", text);
                            adapter.notifyItemInserted(messages.size() -1);
                        });
                    }
                    @Override
                    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                        super.onFailure(webSocket, t, response);
                        Looper.prepare();
                        Toast.makeText(context, "Connection failed. Please retry later", Toast.LENGTH_SHORT)
                                .show();
                        finish();
                    }
                }
        );

        sendButton.setOnClickListener(v -> {sendMessage();});
    }

    private void sendMessage()
    {
        String text = String.valueOf(input.getText());
        if(text.isEmpty())
            return;
        input.setText("");
        chatSocketManager.sendMessage(text);
        messages.add(new ChatMessage(text,false));
        adapter.notifyItemInserted(messages.size() -1);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        adapter.notifyDataSetChanged();
    }
}
