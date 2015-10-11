package com.fransson.leonard.oskar.learnrussiannow;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fransson.leonard.oskar.learnrussiannow.MyClasses.Lession;
import com.fransson.leonard.oskar.learnrussiannow.MyClasses.Module;
import com.fransson.leonard.oskar.learnrussiannow.MyClasses.SPHelper;
import com.fransson.leonard.oskar.learnrussiannow.util.MyDebugger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModuleFragment extends Fragment {

    private ListView listView;

    public ModuleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View theView = inflater.inflate(R.layout.module_fragment, container, false);

        listView = (ListView)theView.findViewById(R.id.listView);



        return theView;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (MyDebugger.ON) {
            Log.i("ModuleFragment", "STOP onStop");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MyDebugger.ON) {
            Log.i("ModuleFragment", "RESUME onResume");
        }
        updateListView();
    }

    private void updateListView(){
        List<Module> moduleList = gameList();

        listView.setAdapter(new ModuleAdapter(getActivity(), moduleList));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //listView.getItemAtPosition(position);

                Log.i("HEEELOOOOO", "THIS IS MY MESSAGE");
                Module m = (Module) listView.getItemAtPosition(position);



                Intent intent = new Intent(getActivity(), LessionActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //longPressOptions((Module) listView.getItemAtPosition(position));
                return true;
            }
        });
    }

    private List<Module> gameList() {

//        Lession[] myLessions = {
//                new Lession("Hellllo"), new Lession("Second"),
//                new Lession("Second"), new Lession("third"),
//                new Lession("Four"), new Lession("Five"),
//                new Lession("Six"), new Lession("Eight")
//        };
//        List<Lession> myLessionsList = new ArrayList<>(Arrays.asList(myLessions));
//
//        List<Module> moduleList = new ArrayList<>();
//
//        Module FiveTwo = new Module("INTTT", myLessionsList);
//        Module FiveTwo1 = new Module("INTTT", myLessionsList);
//        Module FiveTwo2 = new Module("INTTT", myLessionsList);
//
//        moduleList.add(FiveTwo);
//        moduleList.add(FiveTwo1);
//        moduleList.add(FiveTwo2);

        return SPHelper.get_all_modules(getActivity());
    }


}
