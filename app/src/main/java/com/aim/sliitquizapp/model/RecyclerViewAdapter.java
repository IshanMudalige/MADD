package com.aim.sliitquizapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aim.sliitquizapp.AnswerActivity;
import com.aim.sliitquizapp.R;



import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    AnswerActivity context;
    List<Question> questionList;

    public RecyclerViewAdapter(AnswerActivity context, List<Question> TempList) {

        this.questionList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Question qus= questionList.get(position);

        holder.tvQuestion.setText(" "+qus.getQuestion());

        holder.tvCAnswer.setText(qus.getAnswer());

    }

    @Override
    public int getItemCount() {

        return questionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvQuestion;
        public TextView tvCAnswer;

        public ViewHolder(View itemView) {

            super(itemView);

            tvQuestion = (TextView) itemView.findViewById(R.id.tvQuestion);

            tvCAnswer = (TextView) itemView.findViewById(R.id.tvCAnswer);
        }
    }
}
