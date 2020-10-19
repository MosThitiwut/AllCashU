package com.unitedfoods.allcashu.Customer.CustomerList;

import android.widget.Filter;

import com.unitedfoods.allcashu.Model.CustomerModel;

import java.util.ArrayList;

public class CustomerFilter extends Filter {

    CustomerAdapter adapter;
    ArrayList<CustomerModel> filterList;

    public CustomerFilter(ArrayList<CustomerModel> filterList,CustomerAdapter adapter){

        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults results =  new FilterResults();

        if (constraint != null && constraint.length() >0)
        {
            constraint = constraint.toString().toUpperCase();

            ArrayList<CustomerModel> filteredCustomer = new ArrayList<>();

            for (int i = 0; i<filterList.size(); i++)
            {
//
                if (filterList.get(i).getCustName().toUpperCase().contains(constraint))
                {
                    filteredCustomer.add(filterList.get(i));
                }

            }

            results.count =  filteredCustomer.size();
            results.values = filteredCustomer;
        }
        else {

            results.count =  filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.customerModels = (ArrayList<CustomerModel>) results.values;

        adapter.notifyDataSetChanged();

    }
}
