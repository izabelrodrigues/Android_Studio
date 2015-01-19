package com.izabel.mapsexample; //seu pacote

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    public static final int TYPE_MAP_BASIC = 1;
    public static final int TYPE_MAP_CUSTOM_MARKER = 2;

    private Button btnBasicMap;
    private Button btnCustomMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * Recupera as views do layout activity_main e
     * configura o listener de click dos botões
     */
    private void initViews() {
        btnBasicMap = (Button) findViewById(R.id.home_basic_map);
        btnCustomMap = (Button) findViewById(R.id.home_custom_marker);

        btnBasicMap.setOnClickListener(this);
        btnCustomMap.setOnClickListener(this);
    }

    /**
     * Implementação do Método de onClick da interface OnClickListener e que é acionado
     * quando um dos botões é clicado.
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_basic_map:
                showMap(TYPE_MAP_BASIC);
                break;
            case R.id.home_custom_marker:
                showMap(TYPE_MAP_CUSTOM_MARKER);
                break;
        }
    }

    /**
     * Cria uma nova instância do fragment que possui o mapa de acordo com a opção escolhida
     * (botão clicado) e exibe na tela.
     * @param typeMap
     */
    private void showMap(int typeMap) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_map, CustomMapFragment.newInstance(typeMap)).commitAllowingStateLoss();
    }
}
