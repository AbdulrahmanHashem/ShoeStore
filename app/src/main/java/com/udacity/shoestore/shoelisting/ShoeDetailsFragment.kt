package com.udacity.shoestore.shoelisting

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.res.stringResource
import androidx.core.view.marginBottom
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding

class ShoeDetailsFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailsBinding

    lateinit var args: ShoeDetailsFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeDetailsBinding.inflate(inflater, container, false)

        args = ShoeDetailsFragmentArgs.fromBundle(requireArguments())

        for (Image in args.shoe.images) {
            val imageView = ImageView(requireContext())
            imageView.setImageResource(Image.toInt())
            binding.LinearLayout.addView(imageView, MATCH_PARENT, 500)
        }

        val name = TextView(requireContext())
        name.setText(getString(R.string.name, args.shoe.name))
        binding.LinearLayout.addView(name)

        val size = TextView(requireContext())
        size.setText(getString(R.string.size, args.shoe.size.toInt().toString()))
        binding.LinearLayout.addView(size)

        val company = TextView(requireContext())
        company.setText(getString(R.string.company, args.shoe.company))
        binding.LinearLayout.addView(company)

        val description = TextView(requireContext())
        description.setText(getString(R.string.description, getString(args.shoe.description.toInt())))
        binding.LinearLayout.addView(description)

        return binding.root
    }
}