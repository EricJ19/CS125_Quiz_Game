package com.example.cs125finalproject;

public class QuestionsLibrary {
    private String question;
    private String firstAnswer;
    private String secondAnswer;
    private String thirdAnswer;
    private String fourthAnswer;
    private int correctAnswer;

    public QuestionsLibrary(String question, String firstAnswer, String secondAnswer,
                           String thirdAnswer, String fourthAnswer, int correctAnswer) {
        this.question = question;
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion(int questionNumb) {
        question = "a";
        return question;
    }

}
