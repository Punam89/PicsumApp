package com.example.picsumapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.image_layout.view.imgvImage
import kotlinx.android.synthetic.main.image_layout.view.txtvAuthor

//ImageAdapter for recycleView
class ImageAdapter(private  val ListImageData: List<ImageData>) : RecyclerView.Adapter<ViewHolder>(){

    //Creates view to set data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.image_layout, parent,false)
        return ViewHolder(view)
    }

    //set data to view created in inCreateViewHolder function
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        return viewHolder.bindView(ListImageData[position])
    }

    //Determines how many times loop will continue
    override fun getItemCount(): Int {
        return ListImageData.size
    }

}

//ViewHolder for image layout
class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    //Binds view to the adapter
    fun bindView(imageData: ImageData){

        if(imageData.id != null){
            Glide.with(itemView.context)
                    .load(ServiceBuilder.IMAGE_URL + imageData.id)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .override(300,300)
                    .into(itemView.imgvImage)
        }

        if(imageData.author != null && imageData.author.isNotEmpty()){
            itemView.txtvAuthor.text = imageData.author
        }


    }


}