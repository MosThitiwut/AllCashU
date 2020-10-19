package com.unitedfoods.allcashu.Sale.Sell.ProductGroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.Sale.Sell.SellActivity;
import com.unitedfoods.allcashu.Sale.Sell.SellList.SellAdapter;
import com.unitedfoods.allcashu.Sale.Sell.SellList.SellModel;

import java.util.ArrayList;

public class GummyFragment extends Fragment {
    String ProductGroupID = "4";
    String CustomerID,DocNo;
    RecyclerView recyclerProduct;
    ArrayList<SellModel> arrayList;
    public  GummyFragment(String CustomerID, String DocNo){
        this.CustomerID = CustomerID;
        this.DocNo = DocNo;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gummy,container,false);
    }
    @Override
    public void onStart() {
        super.onStart();
        recyclerProduct = getView().findViewById(R.id.recyclerProduct);
        arrayList = SellActivity.ProductByProductGroupID(getContext(),CustomerID,ProductGroupID);
        SellAdapter adapter = new SellAdapter(getContext(),arrayList);
        recyclerProduct.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerProduct.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
        recyclerProduct.setAdapter(adapter);
    }
}
