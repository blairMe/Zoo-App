package com.bfa.zooapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter: AnimalAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listOfAnimals.add(
            Animal("Baboon", "Baboons live in big places with trees", R.drawable.baboon, false)
        )
        listOfAnimals.add(
            Animal("Bulldog", "Bulldogs live in cages", R.drawable.bulldog, true)
        )
        listOfAnimals.add(
            Animal("Panda", "Pandas live in a zoo", R.drawable.panda, false)
        )
        listOfAnimals.add(
            Animal("Swallow Bird", "Swallow Bird live a forest", R.drawable.swallow_bird, false)
        )
        listOfAnimals.add(
            Animal("White Tiger", "White Tigers live in homes", R.drawable.white_tiger, true)
        )
        listOfAnimals.add(
            Animal("Zebra", "Zebras live in game parks", R.drawable.zebra, false)
        )
        adapter = AnimalAdapter(this, listOfAnimals)
        tvListAnimal.adapter = adapter
    }

    //How to remove items from the list
    fun deleteItems(index: Int) {
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    //Add an item to list(in this case duplicate)
    fun addItem(index: Int) {
        listOfAnimals.add(index, listOfAnimals[index])
        adapter!!.notifyDataSetChanged()
    }

}

class AnimalAdapter : BaseAdapter {

    var listOfAnimals = ArrayList<Animal>()
    var context: Context? = null

    constructor(context: Context, listOfAnimals: ArrayList<Animal>) : super() {
        this.listOfAnimals = listOfAnimals
        this.context = context
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val animal = listOfAnimals[p0]
        if (animal.isKiller == true) {
            var inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView = inflater.inflate(R.layout.animal_killer_ticket, null)
            myView.tvName.text = animal.name!!
            myView.tvDes.text = animal.des!!
            myView.animalImage.setImageResource(animal.image!!)
            myView.animalImage.setOnClickListener{
                //deleteItems(p0)
                //addItem(p0)
                val intent = Intent(context, AnimalInfo::class.java)
                intent.putExtra("name", animal.name!!)
                intent.putExtra("des", animal.des!!)
                intent.putExtra("image", animal.image!!)
                context!!.startActivity(intent)
            }
            return myView
        } else {
            var inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView = inflater.inflate(R.layout.animal_ticket, null)
            myView.tvName.text = animal.name!!
            myView.tvDes.text = animal.des!!
            myView.animalImage.setImageResource(animal.image!!)
            myView.animalImage.setOnClickListener{
                //deleteItems(p0)
                //addItem(p0)
                val intent = Intent(context, AnimalInfo::class.java)
                intent.putExtra("name", animal.name!!)
                intent.putExtra("des", animal.des!!)
                intent.putExtra("image", animal.image!!)
                context!!.startActivity(intent)
            }
            return myView
        }
    }

   override fun getItem(p0: Int): Any {
        return listOfAnimals[p0]
    }

    override fun getCount(): Int {
        return listOfAnimals.size
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

}