package id.my.fachrinaufal.app.testandroiddeveloper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.materialdialoglibrary.MaterialStyledDialog;
import com.example.materialdialoglibrary.enums.Style;
import com.example.sneakerlibrary.Sneaker;

import java.util.List;

import static id.my.fachrinaufal.app.testandroiddeveloper.R.layout.item_crud;

/**
 * Created by fachrinfl on 22/07/2017.
 */

public class Adapter_Crud extends RecyclerView.Adapter<CrudViewHolder>{

    private Context context;
    private List<Item_Crud> listCrud;
    private SqliteDatabase mDatabase;

    public Adapter_Crud(Context context, List<Item_Crud> listCrud){
        this.context = context;
        this.listCrud = listCrud;
        mDatabase = new SqliteDatabase(context);
    }

    @Override
    public CrudViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item_crud, parent, false);



        return new CrudViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CrudViewHolder holder, final int position) {
        final Item_Crud singleProduct = listCrud.get(position);

        holder.tv_labelname.setTextColor(Color.WHITE);
        holder.tv_labelname.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
        holder.tv_labelname.setTextSize(18);

        holder.tv_labelphone.setTextColor(Color.WHITE);
        holder.tv_labelphone.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
        holder.tv_labelphone.setTextSize(18);

        holder.tv_labelemail.setTextColor(Color.WHITE);
        holder.tv_labelemail.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
        holder.tv_labelemail.setTextSize(18);

        holder.tv_name.setText(singleProduct.getName());
        holder.tv_name.setTextColor(Color.WHITE);
        holder.tv_name.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
        holder.tv_name.setTextSize(18);

        holder.tv_phone.setText(singleProduct.getPhone());
        holder.tv_phone.setTextColor(Color.WHITE);
        holder.tv_phone.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
        holder.tv_phone.setTextSize(18);

        holder.tv_email.setText(singleProduct.getEmail());
        holder.tv_email.setTextColor(Color.WHITE);
        holder.tv_email.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
        holder.tv_email.setTextSize(18);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View customView = inflater.inflate(R.layout.costum_view,null);

        final EditText edt_name = (EditText)customView.findViewById(R.id.edt_name);
        final EditText edt_phone = (EditText)customView.findViewById(R.id.edt_phone);
        final EditText edt_email = (EditText)customView.findViewById(R.id.edt_email);

        holder.iv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edt_name.setText(singleProduct.getName());
                edt_name.setTextColor(Color.parseColor("#6190E8"));
                edt_name.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                edt_name.setTextSize(18);

                edt_phone.setText(singleProduct.getPhone());
                edt_phone.setTextColor(Color.parseColor("#6190E8"));
                edt_phone.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                edt_phone.setTextSize(18);

                edt_email.setText(singleProduct.getEmail());
                edt_email.setTextColor(Color.parseColor("#6190E8"));
                edt_email.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/palanquin/Palanquin-Light.ttf"));
                edt_email.setTextSize(18);


                new MaterialStyledDialog.Builder(context)
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .setHeaderDrawable(R.drawable.cover)
                        .withDialogAnimation(true)
                        .withDarkerOverlay(true)
                        .setTitle("Edit new data")
                        .setCustomView(customView, 20, 20, 20, 0)
                        .setPositive("Yes", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {

                                final String name = edt_name.getText().toString();
                                final String phone = edt_phone.getText().toString();
                                final String email = edt_email.getText().toString();

                                if(TextUtils.isEmpty(name) | TextUtils.isEmpty(phone) | TextUtils.isEmpty(email)){
                                    Sneaker.with((Activity) context)
                                            .setTitle("Error")
                                            .setMessage("Something went wrong. Check your input values")
                                            .setDuration(3000)
                                            .sneakError();
                                }
                                else{
                                    mDatabase.updateCrud(new Item_Crud(singleProduct.getId(), name, phone, email));
                                    Sneaker.with((Activity) context)
                                            .setTitle("Success")
                                            .setMessage("Data was successfully updated")
                                            .setDuration(3000)
                                            .sneakSuccess();
                                    ((Activity)context).finish();
                                    context.startActivity(((Activity)context).getIntent());

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


            }


        });
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialStyledDialog.Builder(context)
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .setHeaderDrawable(R.drawable.cover)
                        .withDialogAnimation(true)
                        .withDarkerOverlay(true)
                        .setTitle("Delete")
                        .setDescription("Do you want to delete this data ?")
                        .setPositive("Yes", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {

                                mDatabase.deleteCrud(singleProduct.getId());
                                Sneaker.with((Activity) context)
                                        .setTitle("Success")
                                        .setMessage("Data was successfully deleted")
                                        .setDuration(3000)
                                        .sneakSuccess();
                                ((Activity)context).finish();
                                context.startActivity(((Activity) context).getIntent());


                            }
                        })
                        .setNegative("No", new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listCrud.size();
    }

}

