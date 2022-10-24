package com.udacity.shoestore.shoelisting

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentShoeListBinding.inflate(inflater, container, false)

        registryViewModel = ViewModelProvider(requireActivity()).get(LoginRegisterViewModel::class.java)

        shoeListViewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        navController = (requireActivity().supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment).navController

        with(binding){

            val FloatingActionButton = FloatingActionButton(requireContext())
            FloatingActionButton.setImageResource(R.drawable.ic_add)
            viewLinearLayout.addView(FloatingActionButton)

            with(navController){

                with(registryViewModel){

                    requireActivity().addMenuProvider(object : MenuProvider {
                        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                            menuInflater.inflate(R.menu.nav_drawer, menu)
                        }

                        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                            return when (menuItem.itemId) {
                                R.id.loginFragment -> {
                                    logout()
                                    true
                                }
                                else -> false
                            }
                        }
                    }, viewLifecycleOwner, Lifecycle.State.RESUMED)

                    if (currentUser.value.isNullOrEmpty()){
                        navigate(R.id.loginFragment)
                    }
                }

                FloatingActionButton.setOnClickListener { view: View ->
                    navigate(ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment())
                }

                with(shoeListViewModel){
                    shoeList.observe(viewLifecycleOwner, Observer { newList ->
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


                            viewLinearLayout.addView(FloatingCardView, WRAP_CONTENT, WRAP_CONTENT)

                            FloatingCardView.setOnClickListener { view: View ->
                                graph.get(R.id.shoeDetailsFragment).label = Shoe.name
                                navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(Shoe))
                            }
                        }
                    })
                }

            }
        }

        return binding.root
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}