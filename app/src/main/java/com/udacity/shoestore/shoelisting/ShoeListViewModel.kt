package com.udacity.shoestore.shoelisting

import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {
    val Adidas_BRAVADA_ImageList = mutableListOf<String>(
        R.drawable.adidas_bravada_side.toString(),
        R.drawable.adidas_bravada_bottom.toString(),
        R.drawable.adidas_bravada_top.toString())
    val Adidas_NOVA_COURT_ImageList = mutableListOf<String>(
        R.drawable.adidas_nova_court_side.toString(),
        R.drawable.adidas_nova_court_bottom.toString(),
        R.drawable.adidas_nova_court_top.toString()
    )
    val Reebok_Female_Shoes_ImageList = mutableListOf<String>(
        R.drawable.reebok_female_shoes_side.toString(),
        R.drawable.reebok_female_shoes_bottom.toString(),
        R.drawable.reebok_female_shoes_top.toString()
    )
    val Reebok_PRINCESS_ImageList = mutableListOf<String>(
        R.drawable.reebok_princess_side.toString(),
        R.drawable.reebok_princess_bottom.toString(),
        R.drawable.reebok_princess_top.toString(),
    )

    val Adidas_BRAVADA = Shoe("BRAVADA", 38.0, "Adidas", "Bravada Lifestyle Skateboarding Floral-Print Shoes", Adidas_BRAVADA_ImageList)
    val Adidas_NOVA_COURT = Shoe("NOVA_COURT", 37.0, "Adidas", "Maybe you hit the court once a week. Maybe you just love tennis style. Either way, these adidas tennis-inspired shoes have got you.", Adidas_NOVA_COURT_ImageList)
    val Reebok_Female_Shoes = Shoe("Female_Shoes", 35.0, "Reebok", "RBK CLASS FTW WOM CORE CLASS SHOES (LOW)", Reebok_Female_Shoes_ImageList)
    val Reebok_PRINCESS = Shoe("PRINCESS", 42.0, "Reebok", "Sleek, comfortable style rules with the women’s Princess shoe.", Reebok_PRINCESS_ImageList)

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _shoe_name = MutableLiveData<String>()
    val shoe_name: MutableLiveData<String>
        get() = _shoe_name

    private val _shoe_size = MutableLiveData<String>()
    val shoe_size: MutableLiveData<String>
        get() = _shoe_size

    private val _shoe_company = MutableLiveData<String>()
    val shoe_company: MutableLiveData<String>
        get() = _shoe_company

    private val _shoe_description = MutableLiveData<String>()
    val shoe_description: MutableLiveData<String>
        get() = _shoe_description

//    private val _shoe_images = MutableLiveData<List<Shoe>>()
//    val shoe_images: LiveData<List<Shoe>>
//        get() = _shoe_images

    private val _default_shoe_image = mutableListOf(R.drawable.ic_launcher_foreground.toString())

    init {
        _shoeList.value = mutableListOf(
            Adidas_BRAVADA,
            Adidas_NOVA_COURT,
            Reebok_Female_Shoes,
            Reebok_PRINCESS
        )
        _shoe_name.value = ""
        _shoe_size.value = "0.0"
        _shoe_company.value = ""
        _shoe_description.value = ""
    }

    fun convert(): Boolean {
        try {
            _shoe_size.value?.toDouble()
            return true
        } catch (e :NumberFormatException) {
            return false
        }
    }

    fun addShoe(
        name: String? = shoe_name.value,
        size: String? = shoe_size.value,
        company: String? = shoe_company.value,
        description: String? = shoe_description.value): Boolean {
        if (convert()){
            val newShoe = Shoe(name!!, size!!.toDouble(), company!!, description!!, _default_shoe_image)
            _shoeList.value?.add(newShoe)
            return true
        } else {
            return false
        }
    }
}