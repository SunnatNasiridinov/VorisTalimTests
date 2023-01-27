package com.isystem.tests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResultAdapter extends BaseAdapter {
    private ArrayList<UserData> data=new ArrayList<>();

    public void setData(ArrayList<UserData> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public UserData getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View dataView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_result,viewGroup,false);

        TextView name=dataView.findViewById(R.id.name_users);
        TextView trueAnswer= (TextView) dataView.findViewById(R.id.true_answer_result);
        TextView falseAnswer=(TextView) dataView.findViewById(R.id.false_answer_result);

        name.setText(getItem(position).getName());
        trueAnswer.setText(String.valueOf("True: "+getItem(position).getTrueAnswers()));
        falseAnswer.setText(String.valueOf("False: "+getItem(position).getFalseAnswers()));


        return dataView;
    }
}
