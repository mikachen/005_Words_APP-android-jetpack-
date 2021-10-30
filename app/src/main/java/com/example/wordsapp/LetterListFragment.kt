package com.example.wordsapp


import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {

    // initial value should be null.Because you can't inflate the layout until onCreateView() is called.
    private var _binding:FragmentLetterListBinding? = null
    //after assigned in onCreateView(), _binding will have a value. making a var nullable using !! where the value won't be null
    private val binding get() = _binding!!


    //display the options menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    //inflates the layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    // create a property for the recycler view.
    private lateinit var recycleView: RecyclerView

    //default layoutmanager
    private var isLinearLayoutManager = true

    // set the value of the recyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycleView = binding.recyclerView
        chooseLayout()
    }

    //when Destroy, reset the _binding property to null
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //create OptionsMenu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu,menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    private fun chooseLayout(){
        when(isLinearLayoutManager){
            true ->{
                recycleView.layoutManager = LinearLayoutManager(context)
                recycleView.adapter = LetterAdapter()
            }
            false->{
                recycleView.layoutManager = GridLayoutManager(context,4)
                recycleView.adapter = LetterAdapter()
            }
        }
    }

    private fun setIcon(menuItem: MenuItem?){
        if (menuItem == null)
            return

        menuItem.icon =
            if(isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_linear_layout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_switch_layout ->{
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }











}