package id.my.fachrinaufal.app.testandroiddeveloper;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fachrinfl on 22/07/2017.
 */

public class CrudViewHolder extends RecyclerView.ViewHolder{

    public TextView tv_name,tv_phone,tv_email,tv_labelname,tv_labelphone,tv_labelemail;
    public ImageView iv_update, iv_delete;;
    public CardView cv;

    public CrudViewHolder(View itemView) {
        super(itemView);

        cv = (CardView) itemView.findViewById(R.id.cv);
        cv.setBackgroundResource(R.drawable.background_item);

        tv_labelname = (TextView)itemView.findViewById(R.id.tv_labelname);
        tv_labelphone = (TextView)itemView.findViewById(R.id.tv_labelphone);
        tv_labelemail = (TextView)itemView.findViewById(R.id.tv_labelemail);

        tv_name = (TextView)itemView.findViewById(R.id.tv_name);
        tv_phone = (TextView)itemView.findViewById(R.id.tv_phone);
        tv_email = (TextView)itemView.findViewById(R.id.tv_email);

        iv_update = (ImageView)itemView.findViewById(R.id.iv_update);
        iv_delete = (ImageView)itemView.findViewById(R.id.iv_delete);



    }
}

