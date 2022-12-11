package com.kuro.taxi_earnings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kuro.taxi_earnings.ui.fragment.dummyData

class HistoryAdapter(private val dummyList: List<dummyData>) : RecyclerView.Adapter<HistoryViewHolder>() {

    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.history_list_item, parent, false)
        return HistoryViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val dummyListPosition = dummyList[position]
        holder.date.text = dummyListPosition.dummyDate
        holder.time.text = dummyListPosition.dummyTime + "回"
        holder.earning.text = dummyListPosition.dummyEarning + "円"
        holder.editButton.setOnClickListener {
            listener.onItemClick(it,position,dummyList)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int, data: List<dummyData>)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun getItemCount(): Int {
       return dummyList.size
    }


}

class HistoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    val date = item.findViewById<TextView>(R.id.date_text)
    val time = item.findViewById<TextView>(R.id.times)
    val earning = item.findViewById<TextView>(R.id.earnings)
    val editButton = item.findViewById<Button>(R.id.history_edit_button)

}
