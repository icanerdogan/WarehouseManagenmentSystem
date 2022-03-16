package com.icanerdogan.warehousemanagementsystem.adapter

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.icanerdogan.warehousemanagementsystem.databinding.StockRowLayoutBinding
import com.icanerdogan.warehousemanagementsystem.model.Product


class RecyclerViewStockAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<RecyclerViewStockAdapter.RowHolder>() {
    private lateinit var clipboard: ClipboardManager
    private lateinit var clip: ClipData

    class RowHolder(val stockRowLayoutBinding: StockRowLayoutBinding) :
        RecyclerView.ViewHolder(stockRowLayoutBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        return RowHolder(
            StockRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.stockRowLayoutBinding.stockproduct = productList[position]

        holder.itemView.apply {

            setOnClickListener {
                Toast.makeText(
                    this.context,
                    "Barcode Number: ${holder.stockRowLayoutBinding.textViewProductBarcodeNumber.text}",
                    Toast.LENGTH_LONG
                ).show()

            }
            setOnLongClickListener{
                val text = holder.stockRowLayoutBinding.textViewProductBarcodeNumber.text.toString()
                clipboard = it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clip = ClipData.newPlainText("BarcodeNumber", text)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(it.context, "Barkod Numarası Kopyalandı!", Toast.LENGTH_SHORT).show();
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(newProductList: List<Product>) {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }
}