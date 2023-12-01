package com.example.appbanhang.FragmentParent.SearchFragment

import android.content.Intent
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appbanhang.Adapter.AdapterRecommended
import com.example.appbanhang.Base.BaseFragment
import com.example.appbanhang.Data.DataRecommended
import com.example.appbanhang.FragmentParent.HomeFragment.DangBai.DangBaiActivity
import com.example.appbanhang.R
import com.example.appbanhang.databinding.FragmentSearchBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var ds1: ArrayList<DataRecommended>
    private lateinit var mAdapter: AdapterRecommended

    override val layoutId: Int
        get() = R.layout.fragment_search

    override fun setupListener() {
        super.setupListener()
        binding.btnFloat1.setOnClickListener {
            val intent = Intent(requireContext(),ListChatActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setupUI() {
        super.setupUI()
        YourPostRcv()
        binding.apply {
            rcvFavorite.setHasFixedSize(true)
            rcvFavorite.layoutManager = GridLayoutManager(requireContext(), 2)
            ds1 = arrayListOf<DataRecommended>()
            mAdapter = AdapterRecommended(ds1)
            rcvFavorite.adapter = mAdapter

            mAdapter.setonItemClickListener(object : AdapterRecommended.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(requireContext(), DetailSearchFragment::class.java)
                    intent.putExtra("key",ds1[position].key)
                    Log.d("------", "onItemClick: ${ds1[position].key}")
                    startActivity(intent)
                }
            })
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        searchFirebaseData(newText)
                    }
                    return true
                }

            })
        }
    }

    private fun searchFirebaseData(query: String) {
        val searchQuery = query.toLowerCase()
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val userId = firebaseUser?.uid
        dbRef = FirebaseDatabase.getInstance().getReference("YourPost").child(userId!!)
        val firebaseSearchQuery = dbRef.orderByChild("tenSP").startAt(searchQuery).endAt(searchQuery + "\uf8ff")

        firebaseSearchQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val searchResults = arrayListOf<DataRecommended>()
                for (snapshot in dataSnapshot.children) {
                    val yourDataModel = snapshot.getValue(DataRecommended::class.java)
                    yourDataModel?.let {
                        searchResults.add(it)
                    }
                }
                mAdapter.submitList(searchResults)
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    private fun YourPostRcv() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            val userId = firebaseUser.uid
            dbRef = FirebaseDatabase.getInstance().getReference("YourPost").child(userId)
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val newDataSet = arrayListOf<DataRecommended>()
                        for (empSnap in snapshot.children) {
                            val empData = empSnap.getValue(DataRecommended::class.java)
                            empData?.let {
                                newDataSet.add(it)
                                val key = empSnap.key
                                empData.key = key
                            }
                        }
                        mAdapter.submitList(newDataSet)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
    }
}