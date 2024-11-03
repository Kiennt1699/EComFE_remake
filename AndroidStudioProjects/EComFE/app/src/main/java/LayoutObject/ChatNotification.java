package LayoutObject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.myapplication.Activity.ChatActivity;
import com.example.myapplication.R;

public class ChatNotification {
    public static final String CHANNEL_NAME = "chat_notification";
    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Chat notification";
            String description = "Channel description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_NAME, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void showNotification(Context context,
                                        String title,
                                        String Content) {
        createNotificationChannel(context); // Ensure the channel is created

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_NAME)
                .setSmallIcon(R.drawable.ecom) // Set the icon for the notification
                .setContentTitle(title) // Set the title of the notification
                .setContentText(Content) // Set the content of the notification
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Set the priority
                .setAutoCancel(true) // Automatically remove the notification when tapped
                .setVibrate(new long[]{0, 1000, 500, 1000}); // Vibration pattern

        // Create an intent for the notification tap action
        Intent intent = new Intent(context, ChatActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent); // Set the intent to the notification

        // Show the notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build()); // Use a unique ID for each notification
    }
}
