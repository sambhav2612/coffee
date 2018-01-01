package io.github.sambhav2612.coffee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class order extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner main_item_spinner = null;
    private ArrayAdapter<CharSequence> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        main_item_spinner = findViewById(R.id.main_item_spinner);
        main_item_spinner.setOnItemSelectedListener(this);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.main_item_spinner,
                R.layout.activity_order);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        main_item_spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item_selected = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
