package com.example.david.todoappweek1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;


public class EditItemActivity extends ActionBarActivity {

    private EditText changeItem;
    private RatingBar star;
    private String itemText;
    private Spinner spinner;

    // ActivityTwo.java

  public void onAddedItem(View v){
      changeItem = (EditText) findViewById(R.id.changeItem);
      itemText = changeItem.getText().toString();
  }


    public void onSubmit(View v) {
        onAddedItem(v);
        //EditText etName = (EditText) findViewById(R.id.changeItem);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("name", itemText);
        //data.putExtra("priority", star.getVisibility());
        int position = getIntent().getIntExtra("position", 0);
        data.putExtra("position", position);

        data.putExtra("priority", spinner.getSelectedItem().toString());
        //data.putExtra("")
        //data.putExtra("code", 200); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        itemText = "";
        finish(); // closes the activity, pass data to parent
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        spinner = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> madapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        madapter.  setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(madapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
