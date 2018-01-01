package io.github.sambhav2612.coffee;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class order extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // variables for price

    final private double tax = 12.0;
    final private double discount = 6.0;
    private int coffee_price = 0;
    private int over_price = 0;
    private int net_price = 0;
    private double gross_total = 0.0;

    // item selection variables
    private boolean subItem1selected = false;
    private boolean subItem2selected = false;
    private boolean subItem3selected = false;

    // other variables

    private Spinner main_item_spinner = null;
    private ArrayAdapter<CharSequence> main_adapter = null;

    private Button second_item1 = null, second_item2 = null, second_item3 = null;

    private LinearLayout secondary_options_layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        main_item_spinner = findViewById(R.id.main_item_spinner);
        secondary_options_layout = findViewById(R.id.secondary_option_layout);

        second_item1 = findViewById(R.id.second_item1);
        second_item2 = findViewById(R.id.second_item2);
        second_item3 = findViewById(R.id.second_item3);

        second_item1.isClickable();
        second_item2.isClickable();
        second_item3.isClickable();

        main_item_spinner.setOnItemSelectedListener(this);

        main_adapter = ArrayAdapter.createFromResource(this,
                R.array.main_item_spinner,
                android.R.layout.simple_spinner_item);
        main_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        main_item_spinner.setAdapter(main_adapter);
    }

    // spinner handlers

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int index = 0;
        String[] item_in_array;

        String item_selected = adapterView.getItemAtPosition(i).toString();
        secondary_options_layout.setVisibility(View.VISIBLE);

        Resources res = getResources();
        item_in_array = res.getStringArray(R.array.main_item_spinner);

        if (item_selected.equals(item_in_array[0])) {
            coffee_price = 30;
        } else if (item_selected.equals(item_in_array[1])) {
            coffee_price = 40;
        } else {
            coffee_price = 35;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

    // onCLickListeners for checkboxes

    public void subItem1(View view) {
        if (!subItem1selected) {
            over_price += 30;
            subItem1selected = true;
        } else {
            over_price -= 30;
            subItem1selected = false;
        }
        net_price = coffee_price + over_price;
    }

    public void subItem2(View view) {
        if (!subItem1selected) {
            over_price += 50;
            subItem2selected = true;
        } else {
            over_price -= 50;
            subItem2selected = false;
        }
        net_price = coffee_price + over_price;
    }

    public void subItem3(View view) {
        if (!subItem1selected) {
            over_price += 40;
            subItem3selected = true;
        } else {
            over_price -= 40;
            subItem3selected = false;
        }
        net_price = coffee_price + over_price;
    }

    public void calculate_gross() {
        if (subItem1selected & subItem2selected & subItem3selected) {
            gross_total = ((((double) net_price * discount) / 100) + ((((((double) net_price * discount) / 100)) * tax) / 100));
        }
    }
}
