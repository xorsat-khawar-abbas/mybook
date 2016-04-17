package net.xorsat.mybook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import net.xorsat.datasource.BranchDatasource;
import net.xorsat.model.Branch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BranchActivity extends Activity {
    private GoogleMap mMap;
    private ArrayList<Branch> array_list;
    private Map<Marker, Branch> mMarkerHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        setupMap();
    }


    private void setupMap() {
        mMarkerHashMap = new HashMap<Marker, Branch>();
        BranchDatasource mBranchDatasource = new BranchDatasource();
        array_list = mBranchDatasource.getList();
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.
                id.map)).getMap();

        for (Branch item : array_list) {
            String _title = item.getBranch_name();
            String _snippet = item.getBranch_address();
            LatLng _latLng = new LatLng(item.getLatitude(), item.getLongitude());
            MarkerOptions mMarkerOptions = new MarkerOptions();
            mMarkerOptions.position(_latLng).title(_title).snippet(_snippet).flat(true);
            Marker marker = mMap.addMarker(mMarkerOptions);
            mMarkerHashMap.put(marker, item);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(marker.getPosition())
                    .zoom(11.0f)
                    .build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent mIntent = new Intent(BranchActivity.this,
                        BranchDetailActivity.class);
                BranchDetailActivity.BRANCH = mMarkerHashMap.get
                        (marker);
                startActivity(mIntent);
            }
        });
    }


}
