package com.example.the_dagger.learnit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.adapter.SingleChoiceQuestionAdapter;
import com.example.the_dagger.learnit.model.SingleChoiceQuestion;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    public  int correctCounter = 0;
    int answer[] = new int[10];
    int position = 0;
    RecyclerView quizRecyclerView;
    TextView moduleCompletedMoney;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ArrayList<SingleChoiceQuestion> singleChoiceQuestionArrayList = getIntent().getParcelableArrayListExtra("singleChoiceQuestion");
        answer = getIntent().getIntArrayExtra("answer");
       // position = getIntent().getIntExtra("position",0);
        Log.e("POSITON", "onCreate: " + position );
        Log.e("Size", String.valueOf(singleChoiceQuestionArrayList.size()));
        for(int i = 0;i<singleChoiceQuestionArrayList.size();i++)
        Log.e("Questions", "onCreate: " + singleChoiceQuestionArrayList.get(i).getQuestion() );
        final SingleChoiceQuestionAdapter singleChoiceQuestionAdapter = new SingleChoiceQuestionAdapter(this, singleChoiceQuestionArrayList);
        quizRecyclerView = (RecyclerView) findViewById(R.id.quizRv);
        quizRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        quizRecyclerView.setAdapter(singleChoiceQuestionAdapter);
        quizRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Index", String.valueOf(singleChoiceQuestionAdapter.index));
                Log.e("Position", String.valueOf(position));
                if (position == singleChoiceQuestionArrayList.size()-1) {
                    Intent resultIntent = new Intent(QuizActivity.this, FullscreenActivity.class);
                    resultIntent.putExtra("correctAnswer", correctCounter);
                    startActivity(resultIntent);
                    QuizActivity.this.finish();
                }
                try {
                    Log.e("Correct Answer ", String.valueOf(answer[position]) );
                    if (singleChoiceQuestionAdapter.index == answer[position] && position < singleChoiceQuestionArrayList.size()) {
                        correctCounter++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("Correct counter", String.valueOf(correctCounter));
                quizRecyclerView.smoothScrollToPosition(++position);
                quizRecyclerView.animate();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        QuizActivity.this.finish();
    }
//    public View onCreateView(LayoutInflater inflater,
//                             ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.question_single_choice, container, false);
//        return rootView;
//
//    }


}
