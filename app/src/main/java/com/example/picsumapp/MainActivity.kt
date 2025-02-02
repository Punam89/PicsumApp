package com.example.picsumapp

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.recyclerView
import retrofit2.Call
import retrofit2.Response

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadData()

    }

    /*
    DownloadData From Server
     */
    private fun downloadData(){
        Log.v(ContentValues.TAG,"downloadData()")
        val request=ServiceBuilder.buildSerices(PicsumInterface::class.java)
        val call=request.getImages()

        call.enqueue(object : retrofit2.Callback<List<ImageData>> {
            override fun onResponse(call: Call<List<ImageData>>, response: Response<List<ImageData>>) {
                if(response.isSuccessful) {
                    //Toast.makeText(this@MainActivity, "" + response.isSuccessful, Toast.LENGTH_LONG).show()
                    //var list: List<ImageData>? =response.body()
                    // //list?.forEach {
                    //Log.v(TAG, "id="+it.id +"author="+it.author)
                    //}
                    recyclerView?.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        adapter = ImageAdapter(response.body()!!.toList())
                    }
                }
            }
            override fun onFailure(call: Call<List<ImageData>>, t: Throwable) {
                Log.v(ContentValues.TAG,"OnFailure()")
                Toast.makeText(this@MainActivity,""+t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }




}