package com.ums.tesbuyer.HomeActivity.Home_Fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.ums.tesbuyer.Authentication.sign_up_class
import com.ums.tesbuyer.HomeActivity.Home_Fragment.Search.ProductActivity
import com.ums.tesbuyer.HomeActivity.Home_Fragment.Search.SearchShops
import com.ums.tesbuyer.HomeActivity.Home_Fragment.Search.Search_Next_Shop_Show_Activity
import com.ums.tesbuyer.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_search__next__shop__show_.view.*
import kotlinx.android.synthetic.main.search_shops_view.view.*
import kotlinx.android.synthetic.main.text_view.*

class OfferFragment : Fragment() {
    companion object {
        var get_data: sign_up_class? = null
        var uids = FirebaseAuth.getInstance().uid.toString()
    }
    var extracts_details: ShopClass?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_search__next__shop__show_, container, false)
        getData(view)
//        if(get_data!!.district==null)
//        {
//            Log.d("GETSSS","NONE")
//        }
        return view
    }


    private fun getData(view:View) {
        val buyer_ref = FirebaseDatabase.getInstance().getReference("/BuyerAccount/$uids")
        buyer_ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                get_data = p0.getValue(sign_up_class::class.java)!!
                distrct(get_data!!.district,view)
            }

        })
    }
    private fun distrct(district:String,view:View)
    {
        val allshops =
            FirebaseDatabase.getInstance().getReference("/AllShops/$district")
        allshops.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val shop_adapter = GroupAdapter<ViewHolder>()
                for (h in p0.children) {
                    val get_shops = h.getValue(SearchShops::class.java)
                    if (get_shops != null) {
                        shop_adapter.add(ShopClass(get_shops))
                    }
                }
                shop_adapter.setOnItemClickListener { item, view ->
                    val alert=AlertDialog.Builder(view.context)
                    alert.setTitle("Choose Flesh Type")
                    val layout=LayoutInflater.from(view.context)
                    val se=layout.inflate(R.layout.text_view,null)
                    val chicken=se.findViewById<RadioButton>(R.id.chickenbtn)
                    val mutton=se.findViewById<RadioButton>(R.id.muttonbtn)
                    val fish=se.findViewById<RadioButton>(R.id.fishbtn)
                    val kaadai=se.findViewById<RadioButton>(R.id.kaadaibtn)
                    val vaankolzhi=se.findViewById<RadioButton>(R.id.vaankolzhibtn)
                    //val updatebtn=se.findViewById<Button>(R.id.updates)
                    alert.setView(se)

                    alert.setPositiveButton("Ok"){_,_->
                        if(chicken.isChecked)
                        {
                            extracts_details = item as ShopClass
                            val ind = Intent(view.context, ProductActivity::class.java)
                            ind.putExtra("pro_show", extracts_details!!.get_s)
                            Search_Next_Shop_Show_Activity.ind_buyer_data =
                                Search_Next_Shop_Show_Activity.FlesherData(
                                   extracts_details!!.get_s.photo,
                                    "Chicken",
                                    extracts_details!!.get_s.city,
                                    extracts_details!!.get_s.ownername,
                                    extracts_details!!.get_s.shopname,
                                    extracts_details!!.get_s.uid
                                )
                            startActivity(ind)
                        }
                        else if(mutton.isChecked)
                        {
                            extracts_details = item as ShopClass
                            val ind = Intent(view.context, ProductActivity::class.java)
                            ind.putExtra("pro_show", extracts_details!!.get_s)
                            Search_Next_Shop_Show_Activity.ind_buyer_data =
                                Search_Next_Shop_Show_Activity.FlesherData(
                                    extracts_details!!.get_s.photo,
                                    "Mutton",
                                    extracts_details!!.get_s.city,
                                    extracts_details!!.get_s.ownername,
                                    extracts_details!!.get_s.shopname,
                                    extracts_details!!.get_s.uid
                                )
                            startActivity(ind)
                        }
                        else if(fish.isChecked)
                        {
                            extracts_details = item as ShopClass
                            val ind = Intent(view.context, ProductActivity::class.java)
                            ind.putExtra("pro_show", extracts_details!!.get_s)
                            Search_Next_Shop_Show_Activity.ind_buyer_data =
                                Search_Next_Shop_Show_Activity.FlesherData(
                                    extracts_details!!.get_s.photo,
                                    "Fish",
                                    extracts_details!!.get_s.city,
                                    extracts_details!!.get_s.ownername,
                                    extracts_details!!.get_s.shopname,
                                    extracts_details!!.get_s.uid
                                )
                            startActivity(ind)
                        }
                        else if(kaadai.isChecked)
                        {
                            extracts_details = item as ShopClass
                            val ind = Intent(view.context, ProductActivity::class.java)
                            ind.putExtra("pro_show", extracts_details!!.get_s)
                            Search_Next_Shop_Show_Activity.ind_buyer_data =
                                Search_Next_Shop_Show_Activity.FlesherData(
                                    extracts_details!!.get_s.photo,
                                    "Kaadai",
                                    extracts_details!!.get_s.city,
                                    extracts_details!!.get_s.ownername,
                                    extracts_details!!.get_s.shopname,
                                    extracts_details!!.get_s.uid
                                )
                            startActivity(ind)
                        }
                        else if(vaankolzhi.isChecked)
                        {
                            extracts_details = item as ShopClass
                            val ind = Intent(view.context, ProductActivity::class.java)
                            ind.putExtra("pro_show", extracts_details!!.get_s)
                            Search_Next_Shop_Show_Activity.ind_buyer_data =
                                Search_Next_Shop_Show_Activity.FlesherData(
                                    extracts_details!!.get_s.photo,
                                    "VaanKolzhi",
                                    extracts_details!!.get_s.city,
                                    extracts_details!!.get_s.ownername,
                                    extracts_details!!.get_s.shopname,
                                    extracts_details!!.get_s.uid
                                )
                            startActivity(ind)
                        }
                    }
                    alert.setNegativeButton("Cancel"){_,_->

                    }
                    alert.create()
                    alert.show()

                }
                view.shop_recycle.adapter = shop_adapter
            }

        })
    }
}
class ShopClass(val get_s: SearchShops): Item<ViewHolder>()
{
    override fun getLayout(): Int {
        return R.layout.search_shops_view
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(get_s.photo).into(viewHolder.itemView.flesh_image)
        viewHolder.itemView.shopname.text=get_s.shopname
        viewHolder.itemView.ownername.text=get_s.ownername
        viewHolder.itemView.city.text=get_s.city
    }

}