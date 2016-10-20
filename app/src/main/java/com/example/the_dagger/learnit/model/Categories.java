package com.example.the_dagger.learnit.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class Categories implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("theme")
    @Expose
    private String theme;
    @SerializedName("quizzes")
    @Expose
    private ArrayList<SingleChoiceQuestion> quizzes = new ArrayList<SingleChoiceQuestion>();
    @SerializedName("scores")
    @Expose
    private List<Integer> scores = new ArrayList<Integer>();
    @SerializedName("solved")
    @Expose
    private String solved;

    public Categories(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getString("id");
        this.name = jsonObject.getString("name");
        this.solved = jsonObject.getString("solved");
    }

    protected Categories(Parcel in) {
        name = in.readString();
        id = in.readString();
        theme = in.readString();
        solved = in.readString();
    }

    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     *
     * @param theme
     * The theme
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     *
     * @return
     * The quizzes
     */
    public ArrayList<SingleChoiceQuestion> getQuizzes() {
        return quizzes;
    }

    /**
     *
     * @param quizzes
     * The quizzes
     */
    public void setQuizzes(ArrayList<SingleChoiceQuestion> quizzes) {
        ArrayList<SingleChoiceQuestion> quizzes2 = new ArrayList<>(quizzes);
        this.quizzes = quizzes2;
        Log.i(TAG, "setQuizzes: "+quizzes.get(0).getQuestion());
    }

    /**
     *
     * @return
     * The scores
     */
    public List<Integer> getScores() {
        return scores;
    }

    /**
     *
     * @param scores
     * The scores
     */
    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    /**
     *
     * @return
     * The solved
     */
    public String getSolved() {
        return solved;
    }

    /**
     *
     * @param solved
     * The solved
     */
    public void setSolved(String solved) {
        this.solved = solved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(theme);
        dest.writeString(solved);
    }
}