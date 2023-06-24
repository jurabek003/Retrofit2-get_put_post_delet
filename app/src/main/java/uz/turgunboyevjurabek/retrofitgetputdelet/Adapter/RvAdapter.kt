package uz.turgunboyevjurabek.retrofitgetputdelet.Adapter

import uz.turgunboyevjurabek.retrofitgetputdelet.Madels.User_get

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.retrofitgetputdelet.databinding.ItemRvBinding

class RvAdapter(val list: List<User_get>, val onClick: OnClick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(val itemrv: ItemRvBinding) : ViewHolder(itemrv.root) {
        fun onBind(userGuruh: User_get,position: Int) {

            itemrv.itemId.text=userGuruh.id.toString()
            itemrv.itemBajarildi.text=userGuruh.bajarildi.toString()
            itemrv.itemBatafsil.text=userGuruh.batafsil
            itemrv.itemOxirgiM.text=userGuruh.oxirgiMuddat
            itemrv.itemSarlovha.text=userGuruh.sarlavha
            itemrv.itemZarurlik.text=userGuruh.zarurlik

            // itemdegi more iconkasi bosilsa aynan o'sha itemni edit va delet qilish mukin bo'lishi uchun
            itemrv.itemMore.setOnClickListener {
                onClick.selectItem(userGuruh,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

}
interface OnClick{
    fun selectItem(userGuruh: User_get,position: Int)
}