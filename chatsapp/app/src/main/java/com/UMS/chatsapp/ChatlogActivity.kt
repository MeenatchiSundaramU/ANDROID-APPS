package com.UMS.chatsapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.UMS.chatsapp.HomeActivity.Companion.mydata
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chatlog.*
import kotlinx.android.synthetic.main.chat_to_view.view.*
import kotlinx.android.synthetic.main.chat_view.view.*
import java.util.*

class ChatlogActivity : AppCompatActivity() {
    lateinit var uid:String
    var photouri:Uri?=null
    var usersdata:Register?=null
    lateinit var adap:GroupAdapter<ViewHolder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlog)
        photouri=FirebaseAuth.getInstance().currentUser?.photoUrl
        uid=FirebaseAuth.getInstance().uid.toString()
        usersdata=intent.getParcelableExtra<Register>(NewMessageActivity.USER_KEY)
        supportActionBar?.title=usersdata?.name
        if(usersdata==null)return
        retrievemessage(uid,usersdata!!.uid)
        send.setOnClickListener {
            val message=sendmessage.text.toString()
            if(message.isEmpty())return@setOnClickListener
            if(uid==null && usersdata?.uid==null)return@setOnClickListener
            val random=UUID.randomUUID().toString()
            val ref=FirebaseDatabase.getInstance().getReference("/messages/$uid/${usersdata!!.uid}").push()
            val ref2=FirebaseDatabase.getInstance().getReference("/messages/${usersdata!!.uid}/$uid").push()
            val toref3=FirebaseDatabase.getInstance().getReference("/latestmessages/${usersdata!!.uid}/$uid")
            val fromref4=FirebaseDatabase.getInstance().getReference("/latestmessages/$uid/${usersdata!!.uid}")
            val senddata=Senddata(uid,usersdata!!.uid,message)
            ref.setValue(senddata).addOnSuccessListener {
                sendmessage.text.clear()
                Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show()
            }
            ref2.setValue(senddata).addOnSuccessListener {
                sendmessage.text.clear()
                chatrecycle.scrollToPosition(adap.itemCount-1)
                Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show()
            }
            val latestsfrom=latestMessage(usersdata!!.uid,usersdata!!.photouri,usersdata!!.name,message)
            fromref4.setValue(latestsfrom).addOnCompleteListener {
                Toast.makeText(this,"Latest Messages to added Successfully",Toast.LENGTH_SHORT).show()
            }
            val lateststo=latestMessage(uid, mydata!!.photouri, mydata!!.name,message)
            toref3.setValue(lateststo).addOnCompleteListener {
                Toast.makeText(this,"Latest Messages from added Successfully",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun retrievemessage(uidd:String,touids:String)
    {
        val retref=FirebaseDatabase.getInstance().getReference("/messages/$uidd/$touids")
        retref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
              adap=GroupAdapter<ViewHolder>()
               p0.children.forEach {
                 val send=it.getValue(Senddata::class.java)
                   if(send==null)return
                  if(send.fromid==uid) {
                      adap.add(adapfromclass(send))
                  }
                   else{
                      adap.add(adaptoclass(send,usersdata!!))
                  }
               }
                chatrecycle.adapter=adap
                chatrecycle.scrollToPosition(adap.itemCount-1)
            }

        })
    }
   class adapfromclass(val sent:Senddata): Item<ViewHolder>()
    {
        override fun getLayout(): Int {
            return R.layout.chat_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
          Picasso.get().load(mydata!!.photouri).into(viewHolder.itemView.chatimage)
            viewHolder.itemView.chatmessages.text=sent.send
        }

    }
  class adaptoclass(val sent:Senddata,val usedata:Register): Item<ViewHolder>()
  {
    override fun getLayout(): Int {
        return R.layout.chat_to_view
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(usedata.photouri).into(viewHolder.itemView.chattoimage)
        viewHolder.itemView.chattomessages.text=sent.send
    }

}
    class Senddata(val fromid:String,val toid:String,val send:String){
        constructor() : this("","","")
    }
    class latestMessage(val toid:String,val toimageurl:String,val toname:String,val text:String){
        constructor() : this("","","","")
    }
    class latestMessagefrom(val fromid:String,val fromimageurl:String,val fromname:String,val text:String){
        constructor() : this("","","","")
    }
}