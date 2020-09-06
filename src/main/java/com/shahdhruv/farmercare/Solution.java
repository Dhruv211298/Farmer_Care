package com.shahdhruv.farmercare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Solution extends AppCompatActivity {

    Button btn_bs,btn_ls,btn_hispa,btn_lb;
    EditText edt_solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);
        btn_bs=findViewById(R.id.bs_btn);
        btn_hispa=findViewById(R.id.hispa_btn);
        btn_lb=findViewById(R.id.lb_btn);
        btn_ls=findViewById(R.id.ls_btn);
        edt_solution=findViewById(R.id.solution_edt);

        btn_lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_solution.setText("Leaf Blight:" +
                        "\n->Use balanced amounts of plant nutrients, especially nitrogen." +
                        "\n->Ensure good drainage of fields (in conventionally flooded crops) and nurseries." +
                        "\n->Keep fields clean. Remove weed hosts and plow under rice stubble, straw, rice ratoons and volunteer seedlings, which can serve as hosts of bacteria." +
                        "\n->Allow fallow fields to dry in order to suppress disease agents in the soil and plant residues.");

            }
        });
        btn_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_solution.setText("Brown Spot:" +
                        "\n->Improving soil fertility is the first step in managing brown spot. To do this:" +
                        "\n  ->Monitor soil nutrients regularly." +
                        "\n  ->Apply required fertilizers." +
                        "\n  ->For soils that are low in silicon, apply calcium silicate slag before planting.");
            }
        });
        btn_hispa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_solution.setText("Rice Hispa:" +
                        "\n->A cultural control method that is recommended for the rice hispa is to avoid over fertilizing the field.Close plant spacing results in greater leaf densities that can tolerate higher hispa numbers.To prevent egg laying of the pests, the shoot tips can be cut.Clipping and burying shoots in the mud can reduce grub populations by 75−92%.");
            }
        });
        btn_ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_solution.setText("Leaf Smut:" +
                        "\n->Keep the field clean." +
                        "\n->Remove infected seeds, panicles, and plant debris after harvest." +
                        "\n->Reduce humidity levels through alternate wetting and drying (AWD) rather than permanently flooding the fields." +
                        "\n->Where possible, perform conservation tillage and continuous rice cropping." +
                        "\n->Use moderate rates of Nitrogen." +
                        "\n->Use certified seeds." +
                        "\n->Resistant varieties have been reported. Contact your local agriculture office for an up-to-date list of available varieties." +
                        "\n->Treat seeds at 52°C for 10 min.");
            }
        });
    }
}
