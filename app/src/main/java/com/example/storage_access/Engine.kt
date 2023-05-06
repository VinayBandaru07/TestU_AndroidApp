package com.example.storage_access

import android.util.Log
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import java.lang.Exception
import kotlin.math.log

class Engine {
    private lateinit var path : String

    constructor(para_path : String){
        path = para_path
    }
    var parsedList = mutableListOf<String>()
    var questionList = mutableListOf<String>()
    var answerList = mutableListOf<String>()
    var optionList = mutableListOf<String>()
    fun test_read_pdf(){
        try {
            var parsedText = ""
            val reader = PdfReader(path)
            val n: Int = reader.getNumberOfPages()
            for (i in 0 until n) {
                parsedText =
                    """
            $parsedText${PdfTextExtractor.getTextFromPage(reader, i + 1).trim().toString()}
            
            """.trimIndent() //Extracting the content from the different pages
                parsedList.add(parsedText)
                parsedText=""
            }
            for(i in parsedList){
//                Log.d("Matter", i.toString())
            }
            reader.close()
        } catch (e: Exception) {
            Log.d("Matter", e.toString())
        }
        split()
    }

    fun split(){
        var ch =0
        var multiLine = 0
        for(i in parsedList) {
            for (j in i.lines()) {
                if (!(j.contains("Subject Name") || j.contains("Page No.") || j.isEmpty() || j.isBlank())) {
//                    Log.d("Max", j.toString()+"............................")

                    if (j.contains("[")) {
                        answerList.add(j);
                        multiLine = 0
                    } else if (j.contains("A)") || j.contains("a)")) {
                        optionList.add(j);
                        multiLine = 0
                    } else {
                        if(multiLine == 0) {
                            questionList.add(j)
                            multiLine=1
                        }
                        else{
                            questionList[questionList.size-1] = questionList[questionList.size-1] + " " +j.toString()
                        }
                    }

//                    when(ch){
//                        0 -> {
//                            if(j.contains("[")){
//                                answerList.add(j);
//                                ch=1
//                            }
//                            else if(j.contains("A)") || j.contains("a)")){
//                                optionList.add(j);
//                                ch=0
//                            }
//                            else{
//                                questionList.add(j);
//                                ch=2
//                            }
//
//                        }
//
//                        1 -> {
//                            if(j.contains("A)") || j.contains("a)")){
//                                optionList.add(j);
//                                ch=0
//                            }
//                            else if(j.contains("[")){
//                                answerList.add(j);
//                                ch=1
//                            }
//                            else{
//                                questionList.add(j)
//                                ch=2
//                            }
//                        }
//
//                        2-> {
//                            optionList.add(j)
//                            ch=0
//                        }
//                    }
                }
            }
        }
            for(i in questionList){
                Log.d("Qs", i.toString())
            }
            Log.d("Len", questionList.size.toString())


    }


}