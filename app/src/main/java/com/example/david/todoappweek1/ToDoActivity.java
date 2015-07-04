 package com.example.david.todoappweek1;

 import android.app.Activity;
 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;
 import android.view.Menu;
 import android.view.MenuItem;
 import android.view.View;
 import android.widget.AdapterView;
 import android.widget.ArrayAdapter;
 import android.widget.EditText;
 import android.widget.ListView;
 import android.widget.RatingBar;
 import android.widget.Spinner;

 import java.util.ArrayList;


 public class ToDoActivity extends Activity {

    private ArrayList<String> todoItems;
    //CREATING THE ARRAY ADAPTER
     private ArrayAdapter<String> todoAdapter;
    ArrayList<CustomClass> todoList = new ArrayList<CustomClass>();

     private ListView lvItems;
     public EditText etNewItem;
     public RatingBar ratingBar;
     private Spinner spinner;

     Context context = getBaseContext();
     ArrayList<CustomClass> arrayOfactivities;
     CustomAdapter adapter;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do); //is this the right content view??
        etNewItem = (EditText)findViewById(R.id.etNewItem);
        lvItems = (ListView)findViewById(R.id.lvItems);
         ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        populateArrayItems();
        todoAdapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, todoItems);
        //lvItems.setAdapter(todoAdapter);
        //setupListViewListener();
         spinner = (Spinner) findViewById(R.id.spinner);
         // Create an ArrayAdapter using the string array and a default spinner layout
         ArrayAdapter<CharSequence> madapter = ArrayAdapter.createFromResource(this,
                 R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
         madapter.  setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
         spinner.setAdapter(madapter);

         // Construct the data source
      arrayOfactivities = new ArrayList<CustomClass>();
// Create the adapter to convert the array to views
         adapter = new CustomAdapter(this, arrayOfactivities);
// Attach the adapter to a ListView
         ListView listView = (ListView) findViewById(R.id.lvItems);
         lvItems.setAdapter(adapter);
         setupListViewListener();




     }


     private void setupListViewListener() {
         lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

             @Override
             public boolean onItemLongClick(AdapterView<?> hadapter, View item, int position, long id) {

                arrayOfactivities.remove(position); //array data is changed, but adapter isn't notified so fatal error is thrown
                 //ADD HERE
                 adapter.notifyDataSetChanged();
                  //refreshes data and repopulates the view of the array
                 return true;
             }

         });
         lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent i = new Intent(ToDoActivity.this, EditItemActivity.class);
                 i.putExtra("item name", etNewItem.getText().toString());
                 i.putExtra("priority", spinner.getSelectedItem().toString());
                 i.putExtra("position", position); // pass arbitrary data to launched activity
                 startActivityForResult(i, REQUEST_CODE);
                 //DOESN'T WORK WITH HIGH PRIORITY WHEN A STAR IS IN THE LAYOUT, FIX THIS

             }
         });

         }


     private void populateArrayItems() {
         todoItems = new ArrayList<String>();
     }

     private final int REQUEST_CODE = 20;

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         // REQUEST_CODE is defined above
         if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
             // Extract name value from result extras
             String name = data.getExtras().getString("name");
             String rating = data.getExtras().getString("priority");

             int position = data.getExtras().getInt("position", 0);
             arrayOfactivities.set(position, new CustomClass(name, rating));
             //todoItems.set(position, name); //array data is changed, but adapter isn't notified so fatal error is thrown
             //todoAdapter.notifyDataSetChanged();
             adapter.notifyDataSetChanged();
         }
     }



     public void onAddedItem(View v){

         String itemText = etNewItem.getText().toString();
         //todoAdapter.add(itemText); //adds to array too
         //CustomAdapter cd = new CustomAdapter(context, todoList);
         //adapter.add(this);
         //ToDoActivity newUser = new User("Nathan", "San Diego");
         //adapter.add(newUser);
         CustomClass cc = new CustomClass(itemText, spinner.getSelectedItem().toString());
         adapter.add(cc);
         adapter.notifyDataSetChanged();
         etNewItem.setText("");
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do, menu);
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
