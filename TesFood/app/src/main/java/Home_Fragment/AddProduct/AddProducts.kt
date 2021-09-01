package Home_Fragment.AddProduct

import kotlinx.android.synthetic.main.fragment_add_product.view.*

class AddProducts(val fleshname:String,
                  val fleshvarities:String,
                  val totalflesh:String,
                  val fleshprice:String,
val fleshphone:String,val fleshlat:String,val fleshlong:String,val fleshimage:String)
{
    constructor() : this("","","","","","","","")
}