package com.example.parstagrampart2

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(val context: Context, public val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    lateinit var postList: MutableList<Post>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_post_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
        postList.addAll(posts)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val rv_name : TextView
        val rv_time: TextView
        val rv_image: ImageView
        val rv_description: TextView

        init {
            itemView.setOnClickListener(this)
            rv_name = itemView.findViewById(R.id.rv_name)
            rv_time = itemView.findViewById(R.id.rv_time)
            rv_image = itemView.findViewById(R.id.rv_image)
            rv_description = itemView.findViewById(R.id.rv_description)
        }

        fun bind(post: Post) {
            rv_name.text = post.getUser()?.username
            rv_time.text = post.getFormattedTimestamp()
            Glide.with(itemView.context).load(post.getImage()?.url).into(rv_image)
            rv_description.text = post.getDescription()
        }

        override fun onClick(view: View?) {
            val intent = Intent(itemView.context, DetailedActivity::class.java)
            intent.putExtra("ho", Post())
            itemView.context.startActivity(intent)
        }
    }
}