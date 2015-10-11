package com.fransson.leonard.oskar.learnrussiannow;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.fransson.leonard.oskar.learnrussiannow.MyClasses.Lession;
import com.fransson.leonard.oskar.learnrussiannow.MyClasses.Module;
import com.fransson.leonard.oskar.learnrussiannow.util.MyDebugger;

import java.util.List;

/**
 * Created by Oskar on 2015-06-14.
 */
public class ModuleAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private TextView tvIntro;
    private TableLayout tableLessions;

    private List<Module> modules;

    public ModuleAdapter(Context context, List<Module> modules){
        this.context = context;
        this.modules = modules;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return modules.size();
    }

    @Override
    public Module getItem(int position) {
        return modules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Module curMod = getItem(position);

        convertView = inflater.inflate(R.layout.module_adapter, parent, false);

        TextView tvIntro = (TextView)convertView.findViewById(R.id.tvIntro);

        setUpTable(convertView, curMod);

        return convertView;
    }

    private void setUpTable(View convertView, Module curMod) {
        TableLayout tableLessions = (TableLayout)convertView.findViewById(R.id.tableLessions);
        TableRow tr = new TableRow(convertView.getContext());
        CheckBox cb;

        int x = 0; //Column flag. 2 columns per row
        for (final Lession lession : curMod.getLessions()) {
            x++;
            cb = new CheckBox(convertView.getContext());
            cb.setText(lession.getTitle());
            cb.setFocusable(true);
            cb.setChecked(lession.isCompleted());

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Dont let the user change the checked state. This could be done with a
                    // setOnCheckedChangeListener and cc.setChecked(!isChecked); but i want
                    // everything in the same listener.
                    CheckBox ccc = (CheckBox) v;
                    Toast.makeText(context, ccc.getText(), Toast.LENGTH_LONG).show();

                    if (ccc.isChecked()) {
                        ccc.setChecked(false);
                    } else {
                        ccc.setChecked(true);
                    }

                    //Open the Activity lession
                    if (MyDebugger.ON) {
                        Log.i("ModuleAdapter", "Opening Lession Activity, Lession looks like " + lession.getHtmlPath());
                    }
                    Intent intent = new Intent(context, LessionActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putParcelable(LessionActivity.BUNDLE_LESSION, lession);
                    intent.putExtras(mBundle);
                    context.startActivity(intent);
                }
            });

            //Calculate how many 20dp is in px
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, convertView.getResources().getDisplayMetrics());
            cb.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, px, 1));

            tr.addView(cb);

            if ((x % 2) == 0 ) {
                tableLessions.addView(tr);
                tr = new TableRow(convertView.getContext());
            }
        }
        //If there are odd nr of lessions in a modules add the last one
        if ((x % 2) != 0) {
            tableLessions.addView(tr);
        }
    }


}
