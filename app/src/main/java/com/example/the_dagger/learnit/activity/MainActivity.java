package com.example.the_dagger.learnit.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.adapter.CategoryAdapter;
import com.example.the_dagger.learnit.model.Categories;
import com.example.the_dagger.learnit.model.SingleChoiceQuestion;
import com.example.the_dagger.learnit.utility.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.example.the_dagger.learnit.R.raw.questions;

public class MainActivity extends AppCompatActivity {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_wallet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case R.id.action_logout:
                // Set the text color to green
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Do you want to log out ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog a = builder.create();

                a.show();
                Button bqe = a.getButton(DialogInterface.BUTTON_POSITIVE);
                Button bq = a.getButton(DialogInterface.BUTTON_NEGATIVE);
                bq.setTextColor(Color.BLUE);
                bqe.setTextColor(Color.BLUE);
                builder.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayList<Categories> categoryList = getCategories();
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList,this);
        RecyclerView categoryRv = (RecyclerView) findViewById(R.id.categoryRv);
        categoryRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        categoryRv.setAdapter(categoryAdapter);
        Log.e("List", String.valueOf(categoryList.size()));
    }

    private ArrayList<Categories> getCategories() {
        JSONArray jsonArray;
        ArrayList<Categories> categories = new ArrayList<>();
        int j;
        try {
            InputStream is = getResources().openRawResource(questions);
            jsonArray = new JSONArray(convertStreamToString(is));
            for (j = 0; j < jsonArray.length(); j++)
            {
                JSONObject singleCategory;
                ArrayList<SingleChoiceQuestion> singleChoiceQuestions = new ArrayList<>();
                singleCategory = jsonArray.getJSONObject(j);
                categories.add(new Categories(singleCategory));
                for(int i = 0 ; i < singleCategory.getJSONArray("quizzes").length() ; i++)
                {
                    singleChoiceQuestions.add(new SingleChoiceQuestion(singleCategory.getJSONArray("quizzes").getJSONObject(i)));
                    Log.i("TEST", "question of " + categories.get(j).getName().toString() +  " : " + singleChoiceQuestions.get(i).getQuestion());
                }
                categories.get(j).setQuizzes(singleChoiceQuestions);
                singleChoiceQuestions.clear();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categories;

    }



    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
