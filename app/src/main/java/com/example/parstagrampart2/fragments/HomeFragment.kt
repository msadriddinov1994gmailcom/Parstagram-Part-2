package com.example.parstagrampart2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parstagrampart2.Post
import com.example.parstagrampart2.PostAdapter
import com.example.parstagrampart2.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery


open class HomeFragment : Fragment() {

    lateinit var rv_Posts: RecyclerView
    lateinit var adapter: PostAdapter
    var allPosts: MutableList<Post> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_Posts = view.findViewById(R.id.rv_Posts)
        adapter = PostAdapter(requireContext(), allPosts)
        rv_Posts.adapter = adapter
        rv_Posts.layoutManager = LinearLayoutManager(requireContext())
        queryPosts()


    }

    open fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post>{
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {Log.e(TAG, "Error fetching posts.")}
                else {
                    if (posts != null) {
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                        for (post in posts) {
                            Log.e(TAG, "Posts: ${post.getDescription()}")
                        }
                    }
                }
            }

        })
    }

    companion object {

        const val TAG = "MainActivity"
    }
}