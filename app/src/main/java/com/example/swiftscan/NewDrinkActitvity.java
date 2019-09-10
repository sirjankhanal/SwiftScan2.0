package com.example.swiftscan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewDrinkActitvity extends AppCompatActivity {
    private static final String TAG = NewDrinkActitvity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drink);
    }

    public void addThisDrink(View view) {

        final EditText editText_1 = findViewById(R.id.editText_1);
        final EditText editText_10 = findViewById(R.id.editText_10);
        final EditText editText_2 = findViewById(R.id.editText_2);
        final EditText editText_3 = findViewById(R.id.editText_3);
        final EditText editText_4 = findViewById(R.id.editText_4);
        final EditText editText_5 = findViewById(R.id.editText_5);
        final EditText editText_6 = findViewById(R.id.editText_6);
        final EditText editText_7 = findViewById(R.id.editText_7);
        final EditText editText_8 = findViewById(R.id.editText_8);
        final EditText editText_9 = findViewById(R.id.editText_9);
        final EditText editText_12 = findViewById(R.id.editText_12);
        final EditText editText_11 = findViewById(R.id.editText_11);
        boolean check = (editText_1.getText().toString().isEmpty()
                | editText_2.getText().toString().isEmpty()
                | editText_3.getText().toString().isEmpty()
                | editText_4.getText().toString().isEmpty()
                | editText_5.getText().toString().isEmpty()
                | editText_6.getText().toString().isEmpty()
                | editText_7.getText().toString().isEmpty()
                | editText_8.getText().toString().isEmpty()
                | editText_9.getText().toString().isEmpty()
                | editText_11.getText().toString().isEmpty()
                | editText_12.getText().toString().isEmpty()
                | editText_10.getText().toString().isEmpty());
        if (!check) {
            Log.d(TAG, "createUserWithEmail:success");
            DBHelper db = DBHelper.getInstance(getApplicationContext());
            db.addDrink(getApplicationContext(), editText_1.getText().toString(), editText_2.getText().toString(),
                    editText_3.getText().toString(), editText_4.getText().toString(),
                    editText_5.getText().toString(), editText_6.getText().toString(),
                    editText_7.getText().toString(), editText_8.getText().toString(),
                    editText_9.getText().toString(), editText_10.getText().toString(),
                    editText_11.getText().toString(), editText_12.getText().toString());

            Intent intent = new Intent(NewDrinkActitvity.this, ProfileActivity.class);
            startActivity(intent);
        } else {
            Log.w(TAG, "createUserWithEmail:failure");
            Toast.makeText(NewDrinkActitvity.this, "Please fill all fields",
                    Toast.LENGTH_SHORT).show();

        }
    }
}
