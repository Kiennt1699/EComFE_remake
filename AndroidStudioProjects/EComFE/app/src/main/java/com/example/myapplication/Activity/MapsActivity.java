package com.example.myapplication.Activity;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Nhận địa chỉ từ Intent
        Intent intent = getIntent();
        String address = intent.getStringExtra("location");

        // Lấy SupportMapFragment và đăng ký callback
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Nhận tọa độ từ Intent
        String coordinate = getIntent().getStringExtra("location");

        if (coordinate != null && !coordinate.isEmpty()) {
            // Chuyển đổi tọa độ từ định dạng chuỗi sang vĩ độ và kinh độ
            LatLng location = convertToLatLng(coordinate);
            if (location != null) {
                mMap.addMarker(new MarkerOptions().position(location).title("Địa điểm: " + coordinate));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            } else {
                Toast.makeText(this, "Không tìm thấy vị trí", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Phương thức để chuyển đổi tọa độ từ chuỗi
    private LatLng convertToLatLng(String coordinate) {
        try {
            String[] parts = coordinate.split(" ");

            // Phân tích vĩ độ
            String latPart = parts[0];
            double latitude = parseCoordinate(latPart);

            // Phân tích kinh độ
            String lngPart = parts[1];
            double longitude = parseCoordinate(lngPart);

            return new LatLng(latitude, longitude);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Phương thức để phân tích một phần tọa độ
    private double parseCoordinate(String coordinate) {
        String[] dms = coordinate.split("[°'\"NSEW]");
        double degrees = Double.parseDouble(dms[0]);
        double minutes = Double.parseDouble(dms[1]) / 60;
        double seconds = Double.parseDouble(dms[2]) / 3600;

        double result = degrees + minutes + seconds;

        // Nếu tọa độ là Tây hoặc Nam, hãy đổi dấu
        if (coordinate.endsWith("S") || coordinate.endsWith("W")) {
            result = -result;
        }

        return result;
    }

}
