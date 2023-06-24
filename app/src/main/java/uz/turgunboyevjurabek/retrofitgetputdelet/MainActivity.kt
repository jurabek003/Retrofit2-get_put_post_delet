package uz.turgunboyevjurabek.retrofitgetputdelet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.turgunboyevjurabek.retrofitgetputdelet.Adapter.OnClick
import uz.turgunboyevjurabek.retrofitgetputdelet.Adapter.RvAdapter
import uz.turgunboyevjurabek.retrofitgetputdelet.Madels.User_Post
import uz.turgunboyevjurabek.retrofitgetputdelet.Madels.User_Put
import uz.turgunboyevjurabek.retrofitgetputdelet.Madels.User_deleted
import uz.turgunboyevjurabek.retrofitgetputdelet.Madels.User_get
import uz.turgunboyevjurabek.retrofitgetputdelet.Network.ApiServis
import uz.turgunboyevjurabek.retrofitgetputdelet.Network.ClinetApi
import uz.turgunboyevjurabek.retrofitgetputdelet.databinding.ActivityMain2Binding
import uz.turgunboyevjurabek.retrofitgetputdelet.databinding.DialogItemBinding
import uz.turgunboyevjurabek.retrofitgetputdelet.databinding.ItemRvBinding

class MainActivity : AppCompatActivity(),OnClick {
    private val binding by lazy { ActivityMain2Binding.inflate(layoutInflater) }
    lateinit var list:ArrayList<User_get>
    lateinit var rvAdapter: RvAdapter
    private lateinit var apiServis: ApiServis
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        postItems()
        apiServis=ClinetApi.getRetrofitServis(this)
        apiServis.getAllItem().enqueue(object :Callback<ArrayList<User_get>>{
            override fun onResponse(call: Call<ArrayList<User_get>>,
                response: Response<ArrayList<User_get>>,
            ) {
                if(response.isSuccessful){
                    Toast.makeText(this@MainActivity, "Response isSuccessful", Toast.LENGTH_SHORT).show()
                    list=ArrayList()
                    list.addAll(response.body()!!)
                    rvAdapter=RvAdapter(list,this@MainActivity)
                    rvAdapter.notifyDataSetChanged()
                    binding.rv.adapter=rvAdapter
                }
            }
            override fun onFailure(call: Call<ArrayList<User_get>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error -> ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("Logda", t.message.toString())
            }
        })
    }

    // post (ma'lumot qo'lishish) uchun funksiya
     private fun postItems() {
     binding.btnPost.setOnClickListener {
         val dialogPost=AlertDialog.Builder(this).create()
         val dialogItemBinding=DialogItemBinding.inflate(layoutInflater)
         dialogPost.setView(dialogItemBinding.root)
         dialogPost.show()
         dialogItemBinding.btnSave.setOnClickListener {
             val userPost=User_Post(dialogItemBinding.edtSarlovha.text.toString(),dialogItemBinding.edtBatafsil.text.toString(), dialogItemBinding.edtOxirgimuddat.text.toString(),dialogItemBinding.edtZarurlik.text.toString(),true)
             ClinetApi.getRetrofitServis(this).postItem(userPost)
                 .enqueue(object :Callback<User_get>{
                     override fun onResponse(call: Call<User_get>, response: Response<User_get>) {
                         Toast.makeText(this@MainActivity, "Ureaaaaaaaaa 😊😊😊😊", Toast.LENGTH_SHORT).show()
                         dialogPost.cancel()
                     }

                     override fun onFailure(call: Call<User_get>, t: Throwable) {

                     }
                 })
         }
      }
    }

    // edit va delete qilish uchun funksiya
    override fun selectItem(userGuruh: User_get, position: Int) {
        val itemRvBinding= ItemRvBinding.inflate(layoutInflater)
        val popapmenu=PopupMenu(this,itemRvBinding.itemMore )
        popapmenu.menuInflater.inflate(R.menu.popap_menu,popapmenu.menu)
        popapmenu.show()

        popapmenu.setOnMenuItemClickListener { meniItem->
            when(meniItem.itemId){
                R.id.menu_edit->{
                    val dialog=BottomSheetDialog(this)
                    val dialogItemBinding= DialogItemBinding.inflate(layoutInflater)
                    dialog.setContentView(dialogItemBinding.root)
                    dialog.show()

                    dialogItemBinding.btnSave.setOnClickListener {
                        val userPut=User_Put(dialogItemBinding.edtSarlovha.text.toString(),dialogItemBinding.edtBatafsil.text.toString(), dialogItemBinding.edtOxirgimuddat.text.toString(),dialogItemBinding.edtZarurlik.text.toString(),false)
                        ClinetApi.getRetrofitServis(this).editItem(userGuruh.id!!,userPut)
                            .enqueue(object :Callback<User_Put>{
                                override fun onResponse(
                                    call: Call<User_Put>,
                                    response: Response<User_Put>,){
                                    if (response.isSuccessful){
                                        Toast.makeText(this@MainActivity, "Uraaaaaaaaaaaa🎉🎉🎉🎉🎉", Toast.LENGTH_SHORT).show()
                                        dialog.cancel()
                                    }
                                }
                                override fun onFailure(call: Call<User_Put>, t: Throwable) {
                                    Toast.makeText(this@MainActivity, "E kallenga 😔😔😔😔😔", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            })
                    }
                }

                R.id.menu_delet ->{
                    Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
                    ClinetApi.getRetrofitServis(this).deleteItem(userGuruh.id!!)
                        .enqueue(object :Callback<User_deleted>{
                        override fun onResponse(
                            call: Call<User_deleted>,
                            response: Response<User_deleted>,
                        ) {
                            if (response.isSuccessful){
                                Toast.makeText(this@MainActivity, "delete uraaaa 😉😉😉", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<User_deleted>, t: Throwable) {}
                    })
                }
                }
        true
        }
    }
}