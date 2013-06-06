package edu.calpoly.csc409.crave.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.activities.MainActivity;
import edu.calpoly.csc409.crave.activities.SearchActivity;
import edu.calpoly.csc409.crave.dbmanagement.USDADatabaseManager;
import edu.calpoly.csc409.crave.pojos.NutritionFacts;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlternativesFragment extends ListFragment {
	NutritionFacts m_nutFacts;
	protected ListView m_vwListAlt;
	protected ArrayAdapter<String> adapter;
	protected ArrayList<String> alts;
	protected int m_numAlts = 7;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_alternatives,
				container, false);

		//rootView.setBackgroundResource(R.drawable.alt_foods_screen);
		//m_vwListAlt = (ListView) this.getActivity().findViewById(R.id.alternatives_list);
		
		m_nutFacts = USDADatabaseManager.getNutFacts();
		alts = new ArrayList<String>();
		getAlternatives();
		adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, alts);
		setListAdapter(adapter);
		
		return rootView;
	}
	
	@Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
	    String search = l.getItemAtPosition(position).toString();
	    
	    Intent intent = new Intent(this.getActivity(), MainActivity.class);
		intent.putExtra(SearchActivity.SEARCH_STRING_KEY, search);
		
		this.getActivity().startActivity(intent);
	  }
	
	@SuppressLint("DefaultLocale")
	private ArrayList<String> getAlternatives() {	
		double sweetThreshold = 20;
		double fatThreshold = 10;
		double saltThreshold = 20;
		
		// Generic Healthy Foods
		// Almonds
		alts.add("Nuts");
		//alts.add("Almonds"); // Uncomment when database is fixed
		
		// Apples
		alts.add("Apples");
				
		//Artichokes
		alts.add("Artichokes");
				
		//Avocado
		alts.add("Avocados");
		
		//Beets
		alts.add("Beets");
		
		//Beans
		alts.add("Beans");
		
		//Bell Peppers
		alts.add("Peppers");
		
		//Blackberries
		alts.add("Blackberries");
				
		//Raspberries
		alts.add("Raspberries");
		
		//Blueberries
		alts.add("Blueberries");
				
		//Broccoli
		alts.add("Broccoli");
		
		//Brussels Sprouts
		alts.add("Brussels Sprouts");
		
		//Cherries
		alts.add("Cherries");
				
		//Chia Seeds
		alts.add("Seeds");
		//alts.add("Chia Seeds"); // Uncomment when database is fixed
		
		//Cranberries
		alts.add("Cranberries");
				
		//Dark Chocolate
		alts.add("Chocolate");
		//alts.add("Dark Chocolate"); // Uncomment when database is fixed
		
		//Edamame
		alts.add("Edamame");
				
		//Eggs
		alts.add("Eggs");
				
		//Flax Seed
		//alts.add("Seeds");
		//alts.add("Flaxseed"); // Uncomment when database is fixed
		
		//Greek Yogurt
		//alts.add("Greek");
		//alts.add("Greek Yogurt"); // Uncomment when database is fixed
		
		//Lentils
		alts.add("Lentils");
				
		//Oatmeal (We have no oatmeal entries??
		//alts.add("Nuts");
		//alts.add("Almonds"); // Uncomment when database is fixed
		
		//Oranges
		alts.add("Oranges");
		
		//Pistachios
		alts.add("Nuts");
		//alts.add("Pistachio"); // Uncomment when database is fixed
		
		//Pomegranate
		alts.add("Pomegranate");
		
		//Quinoa
		alts.add("Quinoa");
		
		//Salmon
		alts.add("Fish");
		//alts.add("Salmon"); // Uncomment when database is fixed
		
		//Seaweed
		alts.add("Seaweed");
				
		//Shiitake Mushrooms
		alts.add("Mushrooms");
		//alts.add("Shiitake Mushrooms"); // Uncomment when database is fixed
		
		//Spinach
		alts.add("Spinach");
		
		//Strawberries
		alts.add("Strawberries");
				
		//Sweet Potatoes
		alts.add("Sweet Potato");
				
		//Tomatoes
		alts.add("Tomatoes");
		
		//Tuna
		alts.add("Fish");
		//alts.add("Tuna Fish"); // Uncomment when database is fixed
		
		//Walnuts
		//alts.add("Nuts");
		//alts.add("Walnuts"); // Uncomment when database is fixed
		
		if (m_nutFacts.getSugar() > sweetThreshold) {
			alts.add("Grape");
			alts.add("Cheese");
			alts.add("Beans");
			alts.add("Chicken");
			alts.add("Fruit");
			alts.add("Beef");
			alts.add("Kale");
			alts.add("Cabbage");
			alts.add("Lamb");
			alts.add("Raisins");
		}
		
		if (m_nutFacts.getFat() > fatThreshold) {
			alts.add("Turnip Greens");
			alts.add("Mustard Greens");
			alts.add("Kale");
			alts.add("Cheese");
		}
		
		if (m_nutFacts.getSalt() > saltThreshold) {
			alts.add("Fish");
		}
		
		if (m_nutFacts.getFoodName().toLowerCase().contains("chocolate")) {
			alts.add("Fruit");
		}
		
		// Remove Duplicates
		HashSet<String> h = new HashSet<String>(alts);
		ArrayList<String> temp = new ArrayList<String>();
		alts.clear();
		temp.addAll(h);
		Collections.shuffle(temp);
		for (int i = 0; i < m_numAlts; i++) {
			alts.add(temp.get(i));
		}
		
		return alts;
	}
}