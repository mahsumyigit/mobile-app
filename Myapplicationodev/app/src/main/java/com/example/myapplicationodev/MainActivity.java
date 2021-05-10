package com.example.myapplicationodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ=1;
    public static final String EXTRA_CATEGORY_ID="extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME="extraCategoryName";
    public static final String EXTRA_DIFFICULTY="extraDifficulty";
    public static final String SHARED_PREFS=" sharedPrefs ";



    private Spinner spinnerCategory;
    private Spinner spinnerDifficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerCategory=findViewById(R.id.spinner_category);
        spinnerDifficulty=findViewById(R.id.spinner_difficulty);
        QuizDbHelper db=new QuizDbHelper(this);
        String[] difficultyLevels=Question.getAllDifficultyLevels();

        ArrayAdapter<String> adapterDifficulty=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);

        loadCategories();
        loadDifficultyLevels();



        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startQuiz();
            }
        });
    }
    private void startQuiz(){

        Category selectedCategory=(Category)spinnerCategory.getSelectedItem();
        int categoryID=selectedCategory.getId();
        String categoryName=selectedCategory.getName();
        String difficulty=spinnerDifficulty.getSelectedItem().toString();


        Intent intent=new Intent(MainActivity.this,QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID,categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME,categoryName);
        intent.putExtra(EXTRA_DIFFICULTY,difficulty);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);
    }

    private void loadCategories(){
        QuizDbHelper dbHelper=QuizDbHelper.getInstance(this);
        List<Category> categories=dbHelper.getAllCategories();


        ArrayAdapter<Category> adapterCategories=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategories);
    }

    private  void loadDifficultyLevels(){
        String[] difficultyLevels=Question.getAllDifficultyLevels();

        ArrayAdapter<String> adapterDifficulty=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);


    }

    }

