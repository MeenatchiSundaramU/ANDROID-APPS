package Home_Fragment.AddProduct

import Home_Fragment.AddProduct.RequirementsFrag.RequirementFragment.Companion.get_data
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ums.tesfood.R
import kotlinx.android.synthetic.main.activity_flesh_image.*
import kotlinx.android.synthetic.main.activity_flesh_image.view.*

class FleshImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.activity_flesh_image, container, false)
        view.chicken_image.setOnClickListener {
            val ind=Intent(Intent(it.context,ChickenAdd::class.java))
            ind.putExtra("CLICK","Chicken")
            startActivity(ind)
        }
        view.mutton_image.setOnClickListener {
            val ind=Intent(Intent(it.context,ChickenAdd::class.java))
            ind.putExtra("CLICK","Mutton")
            startActivity(ind)
        }
        view.fish_image.setOnClickListener {
            val ind=Intent(Intent(it.context,ChickenAdd::class.java))
            ind.putExtra("CLICK","Fish")
            startActivity(ind)
        }
        view.kaadai_image.setOnClickListener {
            val ind=Intent(Intent(it.context,ChickenAdd::class.java))
            ind.putExtra("CLICK","Kaadai")
            startActivity(ind)
        }
        view.vaankolzhi_image.setOnClickListener {
            val ind=Intent(Intent(it.context,ChickenAdd::class.java))
            ind.putExtra("CLICK","VaanKolzhi")
            startActivity(ind)
        }
        return view
    }
}