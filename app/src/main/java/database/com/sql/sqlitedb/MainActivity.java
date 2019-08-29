package database.com.sql.sqlitedb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText name, idName, age;
    Button push, view, update, delete;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        idName = findViewById(R.id.textid);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        push = findViewById(R.id.btnpush);
        view = findViewById(R.id.btnview);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btndelete);

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDB.insertDate("fghf", "rrrrrr");
                if (isInserted = true) {
                    Toast.makeText(MainActivity.this, "Successfully" + isInserted, Toast.LENGTH_SHORT).show();
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getData();
                if (res.getCount() == 0) {
                    showMassage("Errror", "Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id:" + res.getString(0) + "\n");
                    buffer.append("Name:" + res.getString(1) + "\n");
                    buffer.append("Age:" + res.getString(2) + "\n");
                }
                showMassage("Data", buffer.toString());
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdateData = myDB.updateData(idName.getText().toString(), name.getText().toString(), age.getText().toString());
                if (isUpdateData == true) {
                    Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRaw = myDB.deleteData(idName.getText().toString());
                if (deleteRaw > 0) {

                    Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    public void showMassage(String title, String massage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(massage);
        builder.show();

    }

}
