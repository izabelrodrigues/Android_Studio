package com.izabel.mapsexample; //seu pacote

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CustomMapFragment extends Fragment implements OnMapReadyCallback {
    private SupportMapFragment supportMapFragment;
    private int typeMap;
    private View rootView;

    public static CustomMapFragment newInstance(int typeMap) {
        CustomMapFragment fragment = new CustomMapFragment();
        Bundle args = new Bundle();
        args.putInt("typeMap", typeMap);
        fragment.setArguments(args);
        return fragment;
    }

    public CustomMapFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            typeMap = getArguments().getInt("typeMap"); //Recupera o tipo do mapa passado por parâmetro
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        supportMapFragment = SupportMapFragment.newInstance();
        rootView = inflater.inflate(R.layout.fragment_custom_map, null);
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().replace(R.id.map, supportMapFragment).commitAllowingStateLoss();
        }
        supportMapFragment.getMapAsync(this);
        return rootView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        switch (typeMap) {
            case MainActivity.TYPE_MAP_BASIC:
                configBasicMap(googleMap);
                break;
            case MainActivity.TYPE_MAP_CUSTOM_MARKER:
                configCustomMarkerMap(googleMap);
                break;
        }
    }

    private void configBasicMap(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.867, 151.206); //Cria uma coordenada de ponto para o mapa
        //Configura o zoom da camera do mapa
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        /**
         * Adiciona um pino na posição da coordenada.
         * Ao clicar em cima do pino exibirá o título Sidney e a descrição que está no snippet
         */
        googleMap.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("Cidade mais populosa da Austrália.")
                .position(sydney));
    }

    private void configCustomMarkerMap(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.867, 151.206);
        googleMap.setMyLocationEnabled(true); //Exibi o botão de localizar a localização do usuário
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
        MarkerOptions markerOptions = new MarkerOptions();
        /**
         * O arquivo de imagem pin48.png deve estar presente dentro da pasta mipmap-mdpi
         * Caso esteja usando uma versão mais antiga do Android Studio e do SDK,
         * você pode ter somente as pastas drawable. Nesse caso, cole dentro de drawable-mdpi
         * e altere a linha markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin48))
         * para markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin48))
         */
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin48))
                .anchor(0.0f, 1.0f) //Posiciona o pino um pouco mais pra cima
                .position(sydney)
                .title("Sydney");
        googleMap.addMarker(markerOptions);
    }
}
