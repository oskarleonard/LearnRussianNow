package com.fransson.leonard.oskar.learnrussiannow.MyClasses;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is static stuff that i will load into SharedPref first time the app u run,
 * like the lessions.
 */
public abstract class LoadToSharedPreferences {

    public static void loadData(Context context){
        loadFirstModule(context);
    }

    public static void loadFirstModule(Context context){
        Module module;

        /*First Lession*/

        /*1 Question soft sign*/
        //Answers for question
        Answer a = new Answer("Indicates a pause between syllables", false);
        Answer a1 = new Answer("Rarely used", false);
        Answer a2 = new Answer("It is a vowel", false);
        Answer a3 = new Answer("Looks like: 'Ъ'", false);
        Answer a4 = new Answer("Looks like: 'Ь'", true);
        Answer a5 = new Answer("Makes the previous letter soft", true);
        Answer a6 = new Answer("Raises the middle of the tongue to the roof", true);
        Answer[] listOfAn = new Answer[]{a, a1, a2, a3, a4, a5, a6};
        Question q1 = new Question("What is true about the soft sign", Arrays.asList(listOfAn));
        /*End First Question*/

        /*2 Question Russian alphabet based on*/
        //Answers for question
        a = new Answer("The Cryllic script", true);
        a1 = new Answer("The pronunciations signs", false);
        a2 = new Answer("Latin script signs", false);
        a3 = new Answer("Ancient scripts", false);
        a4 = new Answer("Soft and hard letters", false);
        listOfAn = new Answer[]{a, a1, a2, a3, a4};
        Question q2 = new Question("What is the Russian alphabet based on?", Arrays.asList(listOfAn));
        /*End Second Question*/

        /*3 Question hard sign*/
        //Answers for question
        a = new Answer("Indicates a pause between syllables", true);
        a1 = new Answer("Rarely used", true);
        a2 = new Answer("It is a vowels", false);
        a3 = new Answer("Looks like: 'Ъ'", true);
        a4 = new Answer("Looks like: 'Ь'", false);
        a5 = new Answer("Makes the previous letter soft", false);
        listOfAn = new Answer[]{a, a1, a2, a3, a4, a5};
        Question q3 = new Question("What is true about the hard sign?", Arrays.asList(listOfAn));
        /*End Second Question*/

        /*4 Question Privet Means*/
        //Answers for question
        a = new Answer("Hello", true);
        a1 = new Answer("Apple", false);
        a2 = new Answer("Milk", false);
        a3 = new Answer("Ten", false);
        a4 = new Answer("10", false);
        a5 = new Answer("Hi", true);
        listOfAn = new Answer[]{a, a1, a2, a3, a4, a5};
        Question q4 = new Question("Privet means", Arrays.asList(listOfAn));
        /*End Second Question*/

        /*5 Question The letter 'з' in Latin*/
        //Answers for question
        a = new Answer("z", true);
        a1 = new Answer("zch", false);
        a2 = new Answer("chc", false);
        a3 = new Answer("zzh", false);
        a4 = new Answer("c", false);
        a5 = new Answer("ct", false);
        listOfAn = new Answer[]{a, a1, a2, a3, a4, a5};
        Question q5 = new Question("The letter 'з' in Latin letter/s", Arrays.asList(listOfAn));
        /*End Second Question*/

        /*6 Question Ребенок means*/
        //Answers for question
        a = new Answer("Child", true);
        a1 = new Answer("Apple", false);
        a2 = new Answer("Milk", false);
        a3 = new Answer("Ten", false);
        a4 = new Answer("10", false);
        a5 = new Answer("Hello", false);
        a6 = new Answer("Hi", false);
        listOfAn = new Answer[]{a, a1, a2, a3, a4, a5,a6};
        Question q6 = new Question("Ребенок means", Arrays.asList(listOfAn));
        /*End Second Question*/

        /*7 Question молоко means*/
        //Answers for question
        a = new Answer("Child", false);
        a1 = new Answer("Apple", false);
        a2 = new Answer("Milk", true);
        a3 = new Answer("Ten", false);
        a4 = new Answer("10", false);
        a5 = new Answer("Hello", false);
        a6 = new Answer("Hi", false);
        listOfAn = new Answer[]{a, a1, a2, a3, a4, a5,a6};
        Question q7 = new Question("молоко means", Arrays.asList(listOfAn));
        /*End Second Question*/

        /*8 Question яблоко means*/
        //Answers for question
        a = new Answer("Child", false);
        a1 = new Answer("Apple", true);
        a2 = new Answer("Milk", false);
        a3 = new Answer("Ten", false);
        a4 = new Answer("10", false);
        a5 = new Answer("Hello", false);
        a6 = new Answer("Hi", false);
        listOfAn = new Answer[]{a, a1, a2, a3, a4, a5,a6};
        Question q8 = new Question("яблоко means", Arrays.asList(listOfAn));
        /*End Second Question*/

        /*9 Question десять  means*/
        //Answers for question
        a = new Answer("Child", false);
        a1 = new Answer("Apple", false);
        a2 = new Answer("Milk", false);
        a3 = new Answer("Ten", true);
        a4 = new Answer("10", true);
        a5 = new Answer("Hello", false);
        a6 = new Answer("Hi", false);
        listOfAn = new Answer[]{a, a1, a2, a3, a4, a5,a6};
        Question q9 = new Question("ДЕСЯТЬ means", Arrays.asList(listOfAn));
        /*End Second Question*/

        /*10 Question What is/are true about stresses*/
        //Answers for question
        a = new Answer("Every word contains one stressed vowel", true);
        a1 = new Answer("The stressed letter is empasized greater than the rest", true);
        a2 = new Answer("The stress can cange the sound of the letter", true);
        a3 = new Answer("Every word contains one stressed consonant", false);
        a4 = new Answer("A word doenst have to have a stressed letter", false);
        a5 = new Answer("Changes the way a word is spelled", false);
        listOfAn = new Answer[]{a, a1, a2, a3, a4, a5};
        Question q10 = new Question("What is/are true about stresses", Arrays.asList(listOfAn));
        /*End Second Question*/

        /*11 Question What relates to Ё*/
        //Answers for question
        a = new Answer("Always stressed", true);
        a1 = new Answer("yo", true);
        a2 = new Answer("Is in the russian word for child", true);
        a3 = new Answer("Is in the russian word for apple", false);
        a4 = new Answer("Is in the russian word for milk", false);
        a5 = new Answer("ye", false);
        a6 = new Answer("e", false);
        Answer a7 = new Answer("ee", false);
        listOfAn = new Answer[]{a, a1, a2, a3, a4, a5, a6, a7};
        Question q11 = new Question("What relates to Ё", Arrays.asList(listOfAn));
        /*End Second Question*/

        /*12 Question Я is pronounced*/
        //Answers for question
        a = new Answer("ya (stressed)", true);
        a1 = new Answer("i (unstressed)", true);
        a7 = new Answer("Very different if stressed", true);
        a2 = new Answer("Always ya", false);
        a3 = new Answer("Always i", false);
        a4 = new Answer("Same pronounciation when stressed", false);
        a5 = new Answer("r (stressed)", false);
        a6 = new Answer("r (unstressed)", false);
        listOfAn = new Answer[]{a, a1, a2, a3, a4, a5,a6, a7};
        Question q12 = new Question("Я is pronounced", Arrays.asList(listOfAn));
        /*End Second Question*/


        Question[] questionsForFirstLession = new Question[]{
                q1, q2, q3,
                q4, q5, q6,
                q7, q8, q9,
                q10, q11, q12,

        };

        Lession firstLession = new Lession(
                "Overview",
                Arrays.asList(questionsForFirstLession),
                "file:///android_asset/Lessions/1Introduction/3Alphabet.html"
        );

        /*End First Lession*/


        /*Second Lession*/

        /*Answers for question*/
        a = new Answer("Indicates a pause between syllables", false);
        a1 = new Answer("Rarely used", false);
        a2 = new Answer("It is a vowels", false);
        a3 = new Answer("This is the soft sign 'Ъ'", false);
        a4 = new Answer("This is the soft sign 'Ь'", true);
        listOfAn = new Answer[]{a, a1, a2, a3, a4};

        q1 = new Question("What is true about the soft sign", Arrays.asList(listOfAn));
        questionsForFirstLession = new Question[]{q1};

        Lession secondLession = new Lession(
                "About",
                Arrays.asList(questionsForFirstLession),
                "file:///android_asset/Lessions/1Introduction/3Alphabet.html"
        );//Second Lession

        /*Answers for question*/
        a = new Answer("Indicates a pause between syllables", false);
        a1 = new Answer("Rarely used", false);
        a2 = new Answer("It is a vowels", false);
        a3 = new Answer("This is the soft sign 'Ъ'", false);
        a4 = new Answer("This is the soft sign 'Ь'", true);
        listOfAn = new Answer[]{a, a1, a2, a3, a4};

        q1 = new Question("What is true about the soft sign", Arrays.asList(listOfAn));
        questionsForFirstLession = new Question[]{q1};

        Lession thirdLession = new Lession(
                "Alphabet",
                Arrays.asList(questionsForFirstLession),
                "file:///android_asset/Lessions/1Introduction/3Alphabet.html"
        );//Third Lession


        /*Put all lessions in an array*/
        Lession [] lessionsForModule = new Lession[]{
                firstLession, secondLession, thirdLession};

        module = new Module("Introduction", Arrays.asList(lessionsForModule));

        SPHelper.save_model_to_sp(context, module, SPHelper.SPK_MODULE_INTRODUCTION);
    }
}
