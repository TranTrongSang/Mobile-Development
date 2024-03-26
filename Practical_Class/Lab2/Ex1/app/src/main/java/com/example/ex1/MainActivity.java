package com.example.ex1;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menuNew) {
            showToast("New select");
            return true;
        } else if (itemId == R.id.menuSave) {
            showToast("Saved");
            return true;
        } else if (itemId == R.id.menuLoad) {
            showToast("Load selected");
            return true;
        }
        else if (itemId == R.id.menuDelete) {
            showToast("Deleted");
            return true;
        } else if (itemId == R.id.menuSend) {
            showToast("Sending.... ");
            return true;
        }else if (itemId == R.id.menuExit) {
            finish();
            return true;
        }
        /* Continue with else if blocks for other menu items */
        else {
            if(itemId == R.id.menuView ||itemId == R.id.menuTool)
                showToast("Nothing to show");
            return super.onOptionsItemSelected(item);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
