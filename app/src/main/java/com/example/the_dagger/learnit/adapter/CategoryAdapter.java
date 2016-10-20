package com.example.the_dagger.learnit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.activity.QuizActivity;
import com.example.the_dagger.learnit.model.Categories;
import com.example.the_dagger.learnit.model.SingleChoiceQuestion;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static android.media.CamcorderProfile.get;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by the-dagger on 1/10/16.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {
    private ArrayList<Categories> listCategories;
    private ArrayList<SingleChoiceQuestion> singleChoiceQuestionArrayList;
    private int position;
    //private final View.OnClickListener mOnClickListener  = new View.OnClickListener();
    private final Context context;
    int answer[] = new int[10];

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_category_item, parent, false);
        return new ViewHolder(itemView);
    }

    public CategoryAdapter(ArrayList<Categories> categories, Context context) {
        this.listCategories = categories;
        this.context = context;


    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.ViewHolder holder, final int position) {
        this.position = position;

        Log.e("onBindViewHolder: Ans",String.valueOf(answer ) + String.valueOf(position) );
        Categories singleCategory = listCategories.get(holder.getAdapterPosition());
        if (getItemCount() == -1) {
            holder.title.setText("No Categories at the moment");
            holder.title.setGravity(View.TEXT_ALIGNMENT_CENTER);
        } else {

            holder.title.setText(singleCategory.getName());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int i = 0;
                while (i < listCategories.get(position).getQuizzes().size()) {

                    answer[i] = listCategories.get(position).getQuizzes().get(i).getAnswer();
                    i++;
                }
                Intent intent = new Intent(context, QuizActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("singleAdapter", listCategories.get(position));
                intent.putParcelableArrayListExtra("singleChoiceQuestion", listCategories.get(position).getQuizzes());
                intent.putExtra("answer", answer);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView title;
        public ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image =(ImageView) itemView.findViewById(R.id.background);
            title = (TextView) itemView.findViewById(R.id.category_title);
        }
    }

}
