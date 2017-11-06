package empolyesecurity.recyclerviewwithloadjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class
MainActivity extends AppCompatActivity {

    Button volleyBtn,retrofitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volleyBtn = (Button)findViewById(R.id.volley);
        retrofitBtn = (Button)findViewById(R.id.retrofit);
        volleyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),VolleyActivity.class);
                startActivity(i);
            }
        });

        retrofitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),RetrofitActivity.class);
                startActivity(i);
            }
        });
    }
}
