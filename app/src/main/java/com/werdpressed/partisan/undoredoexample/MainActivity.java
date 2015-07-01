package com.werdpressed.partisan.undoredoexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    MyClass myClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            myClass = MyClass.newInstance();
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, myClass)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (myClass == null) {
            myClass = (MyClass) getFragmentManager().findFragmentById(R.id.fragment_container);
        }
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_undo:
                myClass.dispatchUndoEvent();
                return true;
            case R.id.action_redo:
                myClass.dispatchRedoEvent();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
