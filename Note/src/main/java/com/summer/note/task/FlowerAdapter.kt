package com.summer.note.task

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.summer.note.R

private const val TAG = "FlowerAdapter"
class FlowerAdapter :
    BaseQuickAdapter<FlowerItem, BaseViewHolder>(R.layout.item_flower, FlowerItem.getTestData()) {
    override fun convert(holder: BaseViewHolder, item: FlowerItem) {
        val imageFlower = holder.getView<ImageView>(R.id.item_flower_image)
        val imageHeader = holder.getView<ImageView>(R.id.item_flower_header)
        Glide.with(context).load(item.imageSrc).transform(CenterCrop()).into(imageFlower)
        Glide.with(context).load(item.imgHeader)
            .transform(MultiTransformation(CenterCrop(), CircleCrop())).into(imageHeader)
        holder.setText(R.id.item_flower_address, item.address)
            .setText(R.id.item_flower_starNum, item.starNum)
            .setText(R.id.item_flower_name, item.name)
            .setText(R.id.item_flower_title, item.brief)

    }
}

