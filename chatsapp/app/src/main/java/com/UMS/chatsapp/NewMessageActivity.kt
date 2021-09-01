package com.UMS.chatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chatlog.*
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.new_messagelayout.view.*

class NewMessageActivity : AppCompatActivity() {
    companion object{
        val USER_KEY="USER_KEY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        supportActionBar?.title="Select User"
        selectprobar.visibility= View.VISIBLE
        fecthdata()
    }

   private fun fecthdata()
    {
        val fetchref=FirebaseDatabase.getInstance().getReference("/register")
        fetchref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                var adapter=GroupAdapter<ViewHolder>()
                p0.children.forEach {
                    var user = it.getValue(Register::class.java)
                    if (user != null && user.uid!=FirebaseAuth.getInstance().uid) {
                        adapter.add(useritem(user))
                    }

                }
                adapter.setOnItemClickListener { item, view ->
                    val users=item as useritem
                    val adapind= Intent(view.context,ChatlogActivity::class.java)
                    adapind.putExtra(USER_KEY, users.getuser)
                    startActivity(adapind)
                    finish()
                }
                selectprobar.visibility= View.GONE
                newreycle.adapter=adapter

            }

        })
    }
   class useritem(val getuser:Register):Item<ViewHolder>()
    {
        override fun getLayout(): Int {
            return R.layout.new_messagelayout
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            Picasso.get().load(getuser.photouri).into(viewHolder.itemView.newcircularimage)
            viewHolder.itemView.newname.text=getuser.name
        }

    }

}

