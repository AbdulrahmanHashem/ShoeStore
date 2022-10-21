package com.udacity.shoestore.shoelisting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {
    val Adidas_BRAVADA_ImageList = mutableListOf<String>(
        R.drawable.adidas_bravada_bottom.toString(),
        R.drawable.adidas_bravada_side.toString(),
        R.drawable.adidas_bravada_top.toString())
    val Adidas_NOVA_COURT_ImageList = mutableListOf<String>(
        R.drawable.adidas_nova_court_bottom.toString(),
        R.drawable.adidas_nova_court_side.toString(),
        R.drawable.adidas_nova_court_top.toString()
    )
    val Reebok_Female_Shoes_ImageList = mutableListOf<String>(
        R.drawable.reebok_female_shoes_bottom.toString(),
        R.drawable.reebok_female_shoes_side.toString(),
        R.drawable.reebok_female_shoes_top.toString()
    )
    val Reebok_PRINCESS_ImageList = mutableListOf<String>(
        R.drawable.reebok_princess_bottom.toString(),
        R.drawable.reebok_princess_side.toString(),
        R.drawable.reebok_princess_top.toString(),
    )

    val Adidas_BRAVADA = Shoe("BRAVADA", 38.0, "Adidas", R.string.Adidas_BRAVADA.toString(), Adidas_BRAVADA_ImageList)
    val Adidas_NOVA_COURT = Shoe("NOVA_COURT", 37.0, "Adidas", R.string.Adidas_NOVA_COURT.toString(), Adidas_NOVA_COURT_ImageList)
    val Reebok_Female_Shoes = Shoe("Female_Shoes", 35.0, "Reebok", R.string.Reebok_Female_Shoes.toString(), Reebok_Female_Shoes_ImageList)
    val Reebok_PRINCESS = Shoe("PRINCESS", 42.0, "Reebok", R.string.Reebok_PRINCESS.toString(), Reebok_PRINCESS_ImageList)

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = mutableListOf(
            Adidas_BRAVADA,
            Adidas_NOVA_COURT,
            Reebok_Female_Shoes,
            Reebok_PRINCESS
        )
    }
}