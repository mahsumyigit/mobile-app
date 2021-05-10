package com.example.myapplicationodev;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myapplicationodev.*;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import static com.example.myapplicationodev.QuizContract.*;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Quizdtaaaaaa";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public static synchronized QuizDbHelper getInstance(Context context){
        if(instance==null){
            instance=new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = " CREATE TABLE " +
                CategoriesTable.TABLE_NAME + " ( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = " CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT ," +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER," +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY (" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + " ON DELETE CASCADE " +
                ")";
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Hardware and Software");
        insertCategory(c1);
        Category c2 = new Category("Football");
        insertCategory(c2);
        Category c3 = new Category("Country Flag");
        insertCategory(c3);
    }
    public void addCategory(Category category){
        db=getWritableDatabase();
        insertCategory(category);
    }
    public void addCategories(List<Category>categories){
        db=getWritableDatabase();
        for(Category category:categories){
            insertCategory(category);
        }
    }
    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);

    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Which of the following is true?",
                " 1 KB = 1024 Byte ", " 1 Kilobyte = 8 Bit ", " 1024 MB = 1 KB ", 1, Question.DIFFICULTY_DIFFICULTY, 1);
        insertQuestion(q1);
        Question q2 = new Question("Which country won the first World Cup?",
                " Brazil ", " Uruguay ", " Germany ", 2, Question.DIFFICULTY_DIFFICULTY, 2);
        insertQuestion(q2);
        Question q3 = new Question("Which country has the moon and star in the flag? ",
                " Spain ", " Germany ", " Turkey ", 3, Question.DIFFICULTY_DIFFICULTY, 3);
        insertQuestion(q3);
        Question q4 = new Question("Which country is only the flag red black ?",
                " Angola ", " Spain ", " Germany ", 1, Question.DIFFICULTY_SIMPLE, 3);
        insertQuestion(q4);
        Question q5 = new Question("Who is the player who scored the most goals in the Champions League with a club uniform? ",
                " Lionel Messi", " Cristiano Ronaldo ", " Karim Benzema ", 1, Question.DIFFICULTY_SIMPLE,2);
        insertQuestion(q5);
        Question q6 = new Question("UEFA Champions League final between which teams and when was played in Istanbul? ",
                " Liverpool-Milan(2004) ", " Liverpool-Milan(2005)", " Liverpool-Milan(2003) ", 2, Question.DIFFICULTY_MIDDLE,2);
        insertQuestion(q6);
        Question q7 = new Question("Which is not one of the countries with the moon star? ",
                        "Algeria ", " Cameroon ","Tunisian", 2, Question.DIFFICULTY_MIDDLE,3);
        insertQuestion(q7);
        Question q8 = new Question("Which of the following is not a programming language? ",
                " Delphi ", " Windows XP ", " Python ", 2, Question.DIFFICULTY_MIDDLE,1);
        insertQuestion(q8);
        Question q9 = new Question("What is the name given to the entire software and hardware? ",
                " Information ", " Computer ", " Tecnology ", 2, Question.DIFFICULTY_SIMPLE,1);
        insertQuestion(q9);
        Question q10 = new Question("Which of the following is not a software variant?",
                        "Information highway ","Operating systems  ", " Application software ", 1, Question.DIFFICULTY_DIFFICULTY, 1);
        insertQuestion(q10);
        Question q11 = new Question("Which country wins the most World Cups?",
                " Uruguay", " Brazil", " France ", 2, Question.DIFFICULTY_DIFFICULTY, 2);
        insertQuestion(q11);
        Question q12 = new Question("Which European country's flag in 3 colors means '' Equality, Brotherhood, Freedom ''? ",
                " Germany ", " Italy ", " France ", 3, Question.DIFFICULTY_DIFFICULTY, 3);
        insertQuestion(q12);
        Question q13 = new Question("How many different countries' flags are in the Norwegian flag?",
                " Seven", "Six  ", "Five", 2, Question.DIFFICULTY_SIMPLE, 3);
        insertQuestion(q13);
        Question q14 = new Question("Ronaldinho, one of the best players in football history, has not played for which team in his career? ",
                "Paris Saint-Germain ", " AS Roma ", " Milan ", 2, Question.DIFFICULTY_SIMPLE,2);
        insertQuestion(q14);
        Question q15 = new Question("Uefa Cup, formerly Uefa Cup What is the most winning team in the Uefa Europa League? ",
                " Juventus ", "Milan  ", " Sevilla", 3, Question.DIFFICULTY_MIDDLE,2);
        insertQuestion(q15);
        Question q16 = new Question("Which country's flag is rectangular? ",
                "Sri lanka"," Switzerland ","Nepal", 1, Question.DIFFICULTY_MIDDLE,3);
        insertQuestion(q16);
        Question q17 = new Question("Which of the following is system software? ",
                " Adobe Photoshop ", "Google Chrome  ", " Windows XP ", 3, Question.DIFFICULTY_MIDDLE,1);
        insertQuestion(q17);
        Question q18 = new Question("What is called hand-held and visible parts of the computer? ",
                        "System Software", " Hardware ","Application Software ", 2, Question.DIFFICULTY_SIMPLE,1);
        insertQuestion(q18);
        Question q19 = new Question("What is the name of the only readable, non-erasable, and non-modifiable part of the main memory?",
                        "RAM Memory  ", " ROM Memory", " CPU ", 2, Question.DIFFICULTY_DIFFICULTY, 1);
        insertQuestion(q19);
        Question q20 = new Question("Euro by signing a huge success in 2008, rising matches played in Turkey, which is given to the semifinals stylish in the correct order?",
                " Portugal-Switzerland-Croatia-Czech republic-Germany ", " Portugal-Switzerland-Czech republic-Croatia-Germany  ", " Portugal-Spain-Czech republic-Croatia-Germany ", 2, Question.DIFFICULTY_DIFFICULTY, 2);
        insertQuestion(q20);
        Question q21 = new Question("What is the oldest flag in the world since 1219? ",
                        "Denmark ", " Cuba ", " United Kingdom ", 1, Question.DIFFICULTY_DIFFICULTY, 3);
        insertQuestion(q21);
        Question q22 = new Question("What is the only country in the world with a flag that is different on both sides?",
                        "Paraguay ", " Sweden ", " Italy ", 1, Question.DIFFICULTY_SIMPLE, 3);
        insertQuestion(q22);
        Question q23 = new Question("Who is the player who paid the highest price for the testimonial at once?",
                " Paul Pogba", " Cristiano Ronaldo ", " Gareth Bale ", 1, Question.DIFFICULTY_SIMPLE,2);
        insertQuestion(q23);
        Question q24 = new Question("Who was France facing in the final, which was memorized in the 2006 World Cup by Zidane's head? ",
                " Germany ", " Italy", " Portugal ", 2, Question.DIFFICULTY_MIDDLE,2);
        insertQuestion(q24);
        Question q25 = new Question("What is the most common color among country flags? ",
                "Black ", " Red ","White", 2, Question.DIFFICULTY_MIDDLE,3);
        insertQuestion(q25);
        Question q26 = new Question("Which of the following is one of the input units on the computer? ",
                "Screen ", " Speaker ","Mouse ", 3, Question.DIFFICULTY_MIDDLE,1);
        insertQuestion(q26);
        Question q27 = new Question("Which of the following is not located between both the input and output units? ",
                " Screen ", "CD-ROM ", "Scanner ", 1, Question.DIFFICULTY_SIMPLE,1);
        insertQuestion(q27);

    }
    public void addQuestion(Question question){
        db=getWritableDatabase();
        insertQuestion(question);
    }
    public void addQuestions(List<Question>questions){
        db=getWritableDatabase();

        for(Question question : questions){
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);

    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT*FROM " + CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT*FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));

                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getAQuestions(int categoryID,String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection= QuestionsTable.COLUMN_CATEGORY_ID + " = ? "+
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";

        String[]selectionArgs=new String[]{String.valueOf(categoryID),difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId((c.getInt(c.getColumnIndex(QuestionsTable._ID))));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
