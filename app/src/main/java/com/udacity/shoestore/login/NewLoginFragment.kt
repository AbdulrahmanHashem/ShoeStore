package com.udacity.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentNewLoginBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewLoginFragment : Fragment() {

    private lateinit var binding: FragmentNewLoginBinding

    private lateinit var viewModel: LoginRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewLoginBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(LoginRegisterViewModel::class.java)

        with(binding){

            with(viewModel){

                registerRegister.setOnClickListener { view: View ->
                    if ((registerEnterEmail.text.toString() != "")
                        and (registerEnterPassword.text.toString() == registerReEnterPassword.text.toString())
                        and (registerEnterPassword.text.toString().length > 8)){

                        registerUser(registerEnterEmail.text.toString(), registerEnterPassword.text.toString())
                        Toast.makeText(context, "Register Successful, You Can Now Login", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Wrong Email or Password", Toast.LENGTH_SHORT).show()
                    }
                }

                registerLogin.setOnClickListener { view: View ->
                    view.findNavController().navigate(NewLoginFragmentDirections.actionNewLoginFragmentToLoginFragment())
                }

            }

        }

        return binding.root
    }
}