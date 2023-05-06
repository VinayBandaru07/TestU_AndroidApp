package com.example.storage_access
import android.Manifest
import android.app.DownloadManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.os.Environment
import android.os.Environment.isExternalStorageEmulated
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.storage_access.Prepare

import java.io.File
import androidx.recyclerview.widget.LinearLayoutManager


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.mini_layout.*
import android.view.Window
import androidx.appcompat.widget.Toolbar
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.internal.Util
import java.time.LocalDateTime
import com.google.type.DateTime
import kotlinx.coroutines.delay
import java.time.LocalTime
import android.content.BroadcastReceiver
import android.content.Context
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths


class MainActivity : AppCompatActivity(), OnViewClickListener {

    private var titlesList= mutableListOf<String>()
    private var imagesList= mutableListOf<Int>()
    var firebaseStorage = FirebaseStorage.getInstance()
    var downloadID : Long? = null
    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val STORAGE_PERMISSION_CODE = 101
    }

     fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@MainActivity, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        }
    }



    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var starting : Boolean = true
         if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "WELCOME🙏", Toast.LENGTH_SHORT).show()
                starting = false
            } else {
                Toast.makeText(this@MainActivity, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.height
        actionBar?.setIcon(R.drawable.unnamed)

        var urlist = mutableListOf<String>()
        val colorDrawable = ColorDrawable(Color.parseColor("#FFFFFFFF"))
        val dirPath = Environment.getExternalStorageDirectory().toString() + "/TestU"
        var temp_file_name = " "
        var yourPdfPath = Environment.getExternalStorageDirectory().toString() + "/TestU/" + "co-bits.pdf"
        var engine : Engine = Engine(yourPdfPath)
        engine.test_read_pdf()

        actionBar!!.setBackgroundDrawable(colorDrawable)









        checkPermission(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            STORAGE_PERMISSION_CODE)
        var recyclerAdapter = RecyclerAdapter(titlesList,imagesList, this)
        recycleview.layoutManager=LinearLayoutManager(this)
        recycleview.adapter=recyclerAdapter
        recyclerAdapter.notifyDataSetChanged()



        recycleview.layoutManager = LinearLayoutManager(this)
        recycleview.adapter = RecyclerAdapter(titlesList, imagesList, this)

        // current time

//        val month: Int = DateTime.HOURS_FIELD_NUMBER// gets the current month
        val time: LocalTime = LocalTime.now()
        var hour = time.hour

        if(hour < 12){
            Toast.makeText(this, "☕Good Morning", Toast.LENGTH_SHORT).show()
        }
        if((hour > 12).and(hour < 15)){
            Toast.makeText(this, "🌞Good AfterNoom", Toast.LENGTH_SHORT).show()
        }
        if((hour > 15).and(hour <18)){
            Toast.makeText(this, "😀Good Evening", Toast.LENGTH_SHORT).show()
        }








        var Target_file_path : String="path"




//        fun searchStorage(){
//            val path = Environment.getExternalStorageDirectory().toString()+"/WhatsApp/Media/WhatsApp Documents"
//            Log.d("Files", "Path: $path")
//            val f = File(path)
//            val file: Array<File> = f.listFiles()
//            for (i in file.indices) {
//                if(file[i].getName().substringAfterLast(".", "")=="csv"){
//
//                    addToList(file[i].getName().toString())
//
//                }
//            }
//        }

//        searchStorage()
        addToList("OS QUIZ MID-1")



        fun checkFileDir(){
            if(!File(dirPath).exists()){
                Files.createDirectory(Paths.get(dirPath))
            }
        }

        fun makeName(para_name : String){
            var fI = 0
            var lI = 0
            for(i in para_name.indices){
                if(para_name[i].toString()=="%"){
                    fI=i
                    break
                }
            }
            for(i in para_name.indices){
                if(para_name[i].toString()=="?"){
                    lI=i
                    break
                }
            }
            temp_file_name = para_name.slice(fI+3..lI-1)
        }


      fun downloadFile(temp_uri : String){
            var file_name = temp_file_name
            var path_temp = Environment.getExternalStorageDirectory().toString() + "/TestU/" + temp_file_name
//            yourPdfPath = path_temp
            var the_file : File = File(path_temp)
          if(!File(path_temp).exists()){
              var request = DownloadManager.Request(Uri.parse(temp_uri))
                  .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                  .setDestinationUri(Uri.fromFile(the_file))
                  .setTitle(file_name)
                  .setDescription("Downloading..")
                  .setRequiresCharging(false)
                  .setAllowedOverMetered(true)
                  .setAllowedOverRoaming(true)
              val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
              downloadID = downloadManager.enqueue(request)
          }

        }

        fun getURL(){
            checkFileDir()
            var ref =  firebaseStorage.getReference().child("Docs")
            ref.list(100).addOnSuccessListener {
                    ref ->
                for(i in ref.items){
                    i.downloadUrl.addOnSuccessListener {
                        urii ->
                        makeName(urii.toString())
                        downloadFile(urii.toString())
                        Log.d("Hello", urii.toString())
                    }
                }

            }

        }
        getURL()










    }





    private fun addToList(title : String, image : Int = R.drawable.disk){
        titlesList.add(title)
        imagesList.add(image)


    }

    private fun postToList(){
        for(i in 1..25){
            addToList(i.toString(),R.drawable.images__1_ )
        }
    }

    private val onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            //Fetching the download id received with the broadcast
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(this@MainActivity, "Download Completed", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onViewClicked(position: Int) {
        super.onViewClicked(position)

        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
//        builder.setTitle("Choose one")
        builder.setMessage("Choose one")
        //set message for alert dialog
        builder.setPositiveButton("Prepare") { dialogInterface, which ->
            val i = Intent(this, readLogo::class.java)
            i.putExtra("SelectedPosition", position)
            startActivity(i)
        }
        builder.setNegativeButton("Take Test"){
            dialogInterface, which ->
            val i = Intent(this, quizLogo::class.java)
            i.putExtra("SelectedPosition", position)
            startActivity(i)
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

    }





    }





