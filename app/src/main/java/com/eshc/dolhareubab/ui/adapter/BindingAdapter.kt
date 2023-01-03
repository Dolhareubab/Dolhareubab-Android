package com.eshc.dolhareubab.ui.adapter

import android.net.Uri
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.eshc.dolhareubab.R
import com.eshc.dolhareubab.ui.common.ext.dpToPx
import java.io.File

@BindingAdapter(value = ["imageUrl", "corner"], requireAll = false)
fun ImageView.load(
    imageUrl: String?,
    corner: Int = 0
) {
    if (imageUrl == null) return

    Glide.with(this.context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transform(if(corner > 0) CenterCrop() else FitCenter(),
            if(corner > 0) RoundedCorners(corner.dpToPx(this@load.context)) else null)
        .into(this)
}

@BindingAdapter(value = ["imageFile", "corner"], requireAll = false)
fun ImageView.load(
    imageFile: File?,
    corner: Int = 0
) {
    if (imageFile == null) return

    Glide.with(this.context)
        .load(imageFile)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transform(if(corner > 0) CenterCrop() else FitCenter(),
            if(corner > 0) RoundedCorners(corner.dpToPx(this@load.context)) else null)
        .into(this)
}

@BindingAdapter(value = ["imageUrl", "corner"], requireAll = false)
fun ImageView.load(
    imageUrl: Uri?,
    corner: Int = 0
) {
    //if (imageUrl == null) return

    Glide.with(this.context)
        .load(imageUrl ?: AppCompatResources.getDrawable(this.context, R.drawable.bg_rect_gray_light_radi_8))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transform(
            if (corner > 0) CenterCrop() else CenterCrop(), if (corner > 0) RoundedCorners(
                corner.dpToPx(this@load.context)
            ) else CenterCrop()
        )
        .into(this)
}
