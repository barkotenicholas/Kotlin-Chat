package com.example.kotlinchat

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlinchat.databinding.FragmentChatListBinding


class ChatListFragment : Fragment() {

    private lateinit var binding:FragmentChatListBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentChatListBinding.inflate(layoutInflater)

        val menuHost:MenuHost = requireActivity()



        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.chat_list_menu, menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                return when (menuItem.itemId) {
                    R.id.action_settings -> {
                        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
                        val navController = navHostFragment.navController
                        val action = ChatListFragmentDirections.actionChatListToSettings()
                        navController.navigate(action)
                        true
                    }
                    else -> false
                }
            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED)
        return binding.root
    }

}