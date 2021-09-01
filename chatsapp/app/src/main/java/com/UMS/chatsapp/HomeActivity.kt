package com.UMS.chatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.UMS.chatsapp.NewMessageActivity.Companion.USER_KEY
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.latest_view.view.*

class HomeActivity : AppCompatActivity() {
    lateinit var latestadap:GroupAdapter<ViewHolder>
    companion object {
        var mydata: Register? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        verifyuser()
        getmydata()
        latestadap=GroupAdapter<ViewHolder>()
        latestactivity()
        latestrecyclerview.adapter=latestadap
        latestadap.setOnItemClickListener { item, view ->
            val ind=Intent(this,ChatlogActivity::class.java)
            val row=item as retrieval
            ind.putExtra(USER_KEY,row.user)
            startActivity(ind)

        }
    }
    val latestmessageapi=HashMap<String,latestssss>()
    private fun refreshlatestrecycle()
    {
        latestadap.clear()
          latestmessageapi.values.forEach{
              latestadap.add(retrieval(it))
          }
    }

    private fun latestactivity()
    {
        val authy=FirebaseAuth.getInstance().uid
        if(authy==null)return
        val ref=FirebaseDatabase.getInstance().getReference("/latestmessages/$authy")
        ref.addChildEventListener(object :ChildEventListener{

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {


            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                val chatmessage=p0.getValue(latestssss::class.java)?:return
                latestmessageapi[p0.key!!] = chatmessage
                refreshlatestrecycle()

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatmessage = p0.getValue(latestssss::class.java)?:return
                    latestmessageapi[p0.key!!] = chatmessage
                    refreshlatestrecycle()

            }
            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("Not yet implemented")
            }

        }
          )
    }
    class retrieval(val retryinf:latestssss): Item<ViewHolder>()
    {
        var user:Register?=null
        override fun getLayout(): Int {
            return R.layout.latest_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {


            viewHolder.itemView.latestname.text=retryinf.toname
            viewHolder.itemView.latestmessage.text=retryinf.text
            Picasso.get().load(retryinf.toimageurl).into(viewHolder.itemView.latestimage)
            val checkref=FirebaseDatabase.getInstance().getReference("/register/${retryinf.toid}")
            checkref.addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                   user=p0.getValue(Register::class.java)
                }

            })
        }

    }
    class latestssss(val text:String,val toid:String,val toimageurl:String,val toname:String)
    {
        constructor() : this("","","","")
    }
    private fun getmydata()
    {
        val auth=FirebaseAuth.getInstance().uid
        val ref=FirebaseDatabase.getInstance().getReference("/register/$auth")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                mydata=p0.getValue(Register::class.java)
            }

        })
    }
    private fun verifyuser()
    {
        val check= FirebaseAuth.getInstance().uid
        if(check==null)
        {
            val ind=Intent(this,LoginActivity::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(ind)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id=item.itemId
        when(id)
        {
            R.id.signout->{
                val ind=Intent(this,LoginActivity::class.java).apply {
                    flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(ind)
            }
            R.id.new_message->{
                    val regind=Intent(this,NewMessageActivity::class.java)

                startActivity(regind)
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
}