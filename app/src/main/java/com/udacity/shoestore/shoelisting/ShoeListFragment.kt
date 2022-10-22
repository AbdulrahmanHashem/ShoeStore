package com.udacity.shoestore.shoelisting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.get
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.login.LoginRegisterViewModel

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private lateinit var registryViewModel: LoginRegisterViewModel

    private lateinit var shoeListViewModel: ShoeListViewModel

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShoeListBinding.inflate(inflater, container, false)

        registryViewModel = ViewModelProvider(requireActivity()).get(LoginRegisterViewModel::class.java)

        shoeListViewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        navController = (requireActivity().supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment).navController

        val FloatingActionButton = FloatingActionButton(requireContext())
        FloatingActionButton.setImageResource(R.drawable.ic_add)
        binding.LinearLayout.addView(FloatingActionButton)

        FloatingActionButton.setOnClickListener { view: View ->
            navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment())
        }

        shoeListViewModel.shoeList.observe(viewLifecycleOwner, Observer { newList ->
            for (Shoe in newList){

                val FloatingCardView = CardView(requireContext())
                FloatingCardView.setContentPadding(50,50,50,50)
                FloatingCardView.setCardBackgroundColor(0)
                FloatingCardView.setCardElevation(0.0.toFloat())

                val FloatingCardViewLinearLayout = LinearLayout(requireContext())
                FloatingCardView.addView(FloatingCardViewLinearLayout)
                FloatingCardViewLinearLayout.setOrientation(LinearLayout.VERTICAL)

                val scrollView = HorizontalScrollView(requireContext())
                FloatingCardViewLinearLayout.addView(scrollView, MATCH_PARENT,500)

                val ImagesLayout = LinearLayout(requireContext())
                scrollView.addView(ImagesLayout, MATCH_PARENT, MATCH_PARENT)
                ImagesLayout.setOrientation(LinearLayout.HORIZONTAL)

                for (Image in Shoe.images){
                    val FloatingImageView = ImageView(requireContext())
                    FloatingImageView.setImageResource(Image.toInt())
                    ImagesLayout.addView(FloatingImageView, 1000, MATCH_PARENT)
                }

                val name = TextView(requireContext())
                name.setText(getString(R.string.name_f, Shoe.name))
                FloatingCardViewLinearLayout.addView(name, MATCH_PARENT, WRAP_CONTENT)

                val size = TextView(requireContext())
                size.setText(getString(R.string.name_f, Shoe.size.toInt().toString()))
                FloatingCardViewLinearLayout.addView(size, MATCH_PARENT, WRAP_CONTENT)

                val company = TextView(requireContext())
                company.setText(getString(R.string.company_f, Shoe.company))
                FloatingCardViewLinearLayout.addView(company, MATCH_PARENT, WRAP_CONTENT)

                val description = TextView(requireContext())
                description.setText(getString(R.string.description_f, Shoe.description))
                FloatingCardViewLinearLayout.addView(description, MATCH_PARENT, WRAP_CONTENT)


                binding.LinearLayout.addView(FloatingCardView, WRAP_CONTENT, WRAP_CONTENT)

                FloatingCardView.setOnClickListener { view: View ->
                    navController.graph.get(R.id.shoeDetailsFragment).label = Shoe.name
                    navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(Shoe))
                }
            }
        })

        if (registryViewModel.currentUser.value.isNullOrEmpty()){
            navController.navigate(R.id.loginFragment)
        }
        return binding.root
    }
}