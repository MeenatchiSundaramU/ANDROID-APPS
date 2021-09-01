package Home_Fragment.AddProduct.RequirementsFrag


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ums.tesfood.Authentication.sign_up_class
import com.ums.tesfood.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_requirement.*
import kotlinx.android.synthetic.main.fragment_requirement.view.*
import kotlinx.android.synthetic.main.req_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class RequirementFragment : Fragment() {
    companion object {
        var get_data: sign_up_class? = null
        val uids = FirebaseAuth.getInstance().uid.toString()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_requirement, container, false)
        getData(view)
        return view
    }
    private fun getData(view:View)
    {
        val flesher_ref=
            FirebaseDatabase.getInstance().getReference("/FlesherAccount/${FirebaseAuth.getInstance().uid}")
        flesher_ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {
                get_data = p0.getValue(sign_up_class::class.java)
                if(get_data!=null) {
                    req_data(get_data!!.district, get_data!!.city, view)
                }
                else
                {
                    Toast.makeText(view.context,"Null Value",Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
    @SuppressLint("SimpleDateFormat")
    private fun req_data(district:String,city:String,view:View) {
        val calen=Calendar.getInstance()
        val date=SimpleDateFormat("dd-MM-y").format(calen.time).toString()
        val req_ref=FirebaseDatabase.getInstance().getReference("/FleshOrder/${district}/${city}/$date/${FirebaseAuth.getInstance()
            .uid}")
        req_ref.addValueEventListener(object :ValueEventListener{
            var req_adap=GroupAdapter<ViewHolder>()
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for(h in p0.children) {
                    val get_datas = h.getValue(Requirements::class.java)
                    if(get_datas!=null)
                    {
                        req_adap.add(ReqAdapter(get_datas))
                    }
                }
                view.req_recycle.adapter=req_adap
            }

        })
    }




    class ReqAdapter(val req:Requirements):Item<ViewHolder>()
    {
        override fun getLayout(): Int {
            return R.layout.req_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
          viewHolder.itemView.req_flesh_name.text=req.fleshtype
            viewHolder.itemView.req_vari_value.text=req.fleshvari
            viewHolder.itemView.req_quan_value.text=req.totalquan
            viewHolder.itemView.req_otp_value.text=req.otp
            viewHolder.itemView.id_value.text=req.orderid

        }

    }

}