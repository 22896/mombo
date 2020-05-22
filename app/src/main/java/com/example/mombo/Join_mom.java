package com.example.mombo;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

        import androidx.appcompat.app.AppCompatActivity;

public class Join_mom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button Button1 = (Button) findViewById(R.id.ok);
        Button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View w) {
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
            }
        });


    }
}
