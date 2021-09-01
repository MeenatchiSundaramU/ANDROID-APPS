package com.UMS.chatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Script
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_forgetwork.*

class ForgetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetwork)
        supportActionBar?.title="Forget Password"
        forbtn.setOnClickListener {
            forgetprobar.visibility= View.VISIBLE
            reset()
        }

    }
    private fun reset()
    {
        val name=forgetname.text.toString()
        checks(name)
    }
    private fun checks(namess:String)
    {
        val auth=FirebaseDatabase.getInstance().getReference("/forget/$namess")
        auth.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val gets=p0.getValue(forget::class.java)
                if(gets==null){
                    forgetprobar.visibility= View.GONE
                    Toast.makeText(this@ForgetworkActivity,"Name is Invalid",Toast.LENGTH_SHORT).show()
                    return
                }
                else {
                    val s = gets.uids
                    upload(s)
                }

            }


        })
    }
    private fun upload(ss:String)
    {
        Toast.makeText(this,ss,Toast.LENGTH_SHORT).show()
        val ref2=FirebaseDatabase.getInstance().getReference("/register/$ss")
        ref2.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
             var retrieves=p0.getValue(Register::class.java)
                if(retrieves==null)return
                uploadfinal(retrieves)
            }

        })
    }
    private fun uploadfinal(ssd:Register)
    {
        val pass=forgetpassword.text.toString()
        val conpass=forgetconfirmpassword.text.toString()
        if(pass.length<7 || pass.compareTo(conpass)!=0|| pass.isEmpty()||conpass.isEmpty())
        {
            forgetprobar.visibility= View.GONE
            forgetpassword.requestFocus()
            forgetconfirmpassword.requestFocus()
            forgetpassword.error="Invalid Password"
            forgetconfirmpassword.error="Invalid Password"
            return
        }
        val reffinal=FirebaseDatabase.getInstance().getReference("/register/${ssd.uid}")
        val j=Register(ssd.uid,ssd.name,ssd.email,pass,ssd.photouri)
        reffinal.setValue(j).addOnSuccessListener {
            resetoriginal(j)
            forgetprobar.visibility= View.GONE
            val ind= Intent(this,ForgetsplashActivity::class.java).apply {
                flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(ind)
            Toast.makeText(this,"Changed Successfully",Toast.LENGTH_SHORT).show()

        }
            .addOnFailureListener {
                Toast.makeText(this,it.message.toString(),Toast.LENGTH_SHORT).show()
            }


    }
    private fun resetoriginal(ok:Register)
    {
        val auth1= FirebaseAuth.getInstance()
        auth1.sendPasswordResetEmail(ok.email).addOnSuccessListener {
            Toast.makeText(this,"Email Verification Sent and type same password",Toast.LENGTH_SHORT).show()
        }
    }
}