package adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menghitungberatbadanideal2.R;
import com.example.menghitungberatbadanideal2.Update;

import java.io.Serializable;
import java.util.ArrayList;

import db.DbHelper;
import model.Data;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private ArrayList<Data> listData = new ArrayList<>();
    private Activity activity;
    private DbHelper dbHelper;

    public DataAdapter(Activity activity) {
        this.activity = activity;
        dbHelper = new DbHelper(activity);
    }

    public ArrayList<Data> getListData() {
        return listData;
    }

    public void setListData(ArrayList<Data> listNotes) {
        if (listNotes.size() > 0) {
            this.listData.clear();
        }
        this.listData.addAll(listNotes);
        notifyDataSetChanged();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        final TextView data_name, data_ket, data_bb, data_tinggi, data_imt;
        final Button btn_edit;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

            data_name = itemView.findViewById(R.id.read_name);
            data_ket = itemView.findViewById(R.id.read_ket);
            data_bb = itemView.findViewById(R.id.read_bb);
            data_tinggi = itemView.findViewById(R.id.read_tinggi);
            data_imt = itemView.findViewById(R.id.read_imt);

            btn_edit = itemView.findViewById(R.id.read_edit);
        }
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_adapter, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        holder.data_name.setText(listData.get(position).getData_name());
        holder.data_ket.setText(listData.get(position).getData_ket());
        holder.data_bb.setText(String.valueOf(listData.get(position).getData_bb()));
        holder.data_tinggi.setText(String.valueOf(listData.get(position).getData_tinggi()));
        holder.data_imt.setText(String.valueOf(listData.get(position).getData_IMT()));

        holder.btn_edit.setOnClickListener((View v) -> {
            Intent intent = new Intent(activity, Update.class);
            intent.putExtra("user", (Serializable) listData.get(position));
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}