package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HIstoryFragment extends Fragment {
    List<model> history= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_h_istory, container, false);
        Bundle args = getArguments();
        history = (List<model>) args.getSerializable("valuesArray");
        // Retrieve the RecyclerView from the layout
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize and set the adapter for the RecyclerView
        HistoryAdapter adapter = new HistoryAdapter(history);
        recyclerView.setAdapter(adapter);

        // Check if history is not empty
        if (history != null && !history.isEmpty()) {
            Toast.makeText(requireContext(), "Number of items: " + history.size(), Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}