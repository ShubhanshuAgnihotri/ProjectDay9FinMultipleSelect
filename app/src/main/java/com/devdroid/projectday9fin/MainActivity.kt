package com.devdroid.projectday9fin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var mainMenu: Menu? = null
    private lateinit var mAdapter: MultiSelectAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataset = mutableListOf(
            DataSet("1",false),
            DataSet("Shubhanshu",false),
            DataSet("3",false),
            DataSet("Hemant",false),
            DataSet("5",false),
            DataSet("Apple",false),
            DataSet("University",false),
            DataSet("8",false),
            DataSet("9",false),
            DataSet("10",false),
            DataSet("11",false),
            DataSet("12",false),
        )
        mAdapter = MultiSelectAdapter(dataset){show -> showDeleteMenu(show)}
        val rv:RecyclerView = findViewById(R.id.recyclerView)
        rv.adapter = mAdapter
    }

    private fun showDeleteMenu(show:Boolean){
        mainMenu?.findItem(R.id.mDelete)?.isVisible = show
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        mainMenu = menu
        menuInflater.inflate(R.menu.custom_menu,mainMenu)
        showDeleteMenu(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.mDelete ->{ delete()}
        }
        return super.onOptionsItemSelected(item)
    }
    private fun delete(){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete")
        alertDialog.setMessage("Do you want to delete the items?")
        alertDialog.setPositiveButton("Delete"){_,_ ->
            mAdapter.deleteSelectedItem()
            showDeleteMenu(false)
        }
        alertDialog.setNegativeButton("Cancel"){_,_ ->}
        alertDialog.show()
    }
}