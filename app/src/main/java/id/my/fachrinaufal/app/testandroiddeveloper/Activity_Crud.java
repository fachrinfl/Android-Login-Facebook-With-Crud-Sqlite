package id.my.fachrinaufal.app.testandroiddeveloper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.materialdialoglibrary.MaterialStyledDialog;
import com.example.materialdialoglibrary.enums.Style;
import com.example.sneakerlibrary.Sneaker;

import java.lang.reflect.Field;
import java.util.List;

public class Activity_Crud extends AppCompatActivity {

    private static final String TAG = Activity_Crud.class.getSimpleName();
    private SqliteDatabase mDatabase;
    Toolbar toolbar;
    EditText edt_name;
    EditText edt_phone;
    EditText edt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Crud");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable back = getResources().getDrawable(R.drawable.icn_back);
        getSupportActionBar().setHomeAsUpIndicator(back);

        TextView toolbarTitle = null;
        for (int i = 0; i < toolbar.getChildCount(); ++i) {
            View child = toolbar.getChildAt(i);

            // assuming that the title is the first instance of TextView
            // you can also check if the title string matches
            if (child instanceof TextView) {
                toolbarTitle = (TextView)child;
                toolbarTitle.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                toolbarTitle.setTextColor(Color.parseColor("#6190E8"));
                break;
            }
        }


        RecyclerView rv_crud = (RecyclerView)findViewById(R.id.rv_crud);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_crud.setLayoutManager(linearLayoutManager);
        rv_crud.setHasFixedSize(true);
        mDatabase = new SqliteDatabase(this);

        List<Item_Crud> allCrud = mDatabase.listCrud();
        if(allCrud.size() > 0){
            rv_crud.setVisibility(View.VISIBLE);
            Adapter_Crud mAdapter = new Adapter_Crud(this, allCrud);
            rv_crud.setAdapter(mAdapter);
        }else {
            rv_crud.setVisibility(View.GONE);
            Sneaker.with(this)
                    .setTitle("No data")
                    .setMessage("Please add data")
                    .setDuration(3000)
                    .sneakWarning();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.context_menu:
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.costum_view,null);
                edt_name = (EditText)customView.findViewById(R.id.edt_name);
                edt_phone = (EditText)customView.findViewById(R.id.edt_phone);
                edt_email = (EditText)customView.findViewById(R.id.edt_email);

                edt_name.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                edt_name.setTextColor(Color.parseColor("#6190E8"));

                edt_phone.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                edt_phone.setTextColor(Color.parseColor("#6190E8"));

                edt_email.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                edt_email.setTextColor(Color.parseColor("#6190E8"));

                new MaterialStyledDialog.Builder(Activity_Crud.this)
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .setHeaderDrawable(R.drawable.cover)
                        .withDialogAnimation(true)
                        .withDarkerOverlay(true)
                        .setTitle("Add new data")
                        .setDescription("Please input your data below")
                        .setCustomView(customView, 20, 20, 20, 0)
                        .setPositive("Yes", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                String name = edt_name.getText().toString();
                                String phone = edt_phone.getText().toString();
                                String email = edt_email.getText().toString();
                                if(TextUtils.isEmpty(name) | TextUtils.isEmpty(phone) | TextUtils.isEmpty(email)){
                                    Sneaker.with(Activity_Crud.this)
                                            .setTitle("Error")
                                            .setMessage("Something went wrong. Check your input values")
                                            .setDuration(3000)
                                            .sneakError();
                                }
                                else{
                                    Item_Crud newCrud = new Item_Crud(name, phone, email);
                                    mDatabase.addCrud(newCrud);
                                    startActivity(getIntent());
                                    finish();

                                    Sneaker.with(Activity_Crud.this)
                                            .setTitle("Success")
                                            .setMessage("Data was successfully added")
                                            .setDuration(3000)
                                            .sneakSuccess();

                                }
                            }
                        })
                        .setNegative("No", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
