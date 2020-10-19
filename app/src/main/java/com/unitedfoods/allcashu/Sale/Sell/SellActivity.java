package com.unitedfoods.allcashu.Sale.Sell;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.unitedfoods.allcashu.DB;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.AlgaeFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.BiscuitFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.CandyFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.ChocolateFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.FishChipFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.GapFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.GummyFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.LegumeFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.ToroFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.TradingFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.WaferFragment;
import com.unitedfoods.allcashu.Sale.Sell.ProductGroup.YoyoFragment;
import com.unitedfoods.allcashu.Sale.Sell.SellList.SellModel;

import java.util.ArrayList;
import java.util.List;

public class SellActivity extends AppCompatActivity {

    
    String CustomerID,DocNo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustomerID = getIntent().getStringExtra("CustomerID");
        DocNo = getIntent().getStringExtra("DocNo");

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager,CustomerID,DocNo);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    public static ArrayList<SellModel> ProductByProductGroupID(Context context, String CustomerID, String ProductGroupID)
    {
         DB database = DB.getInstance(context);
        ArrayList<SellModel> arrayList = new ArrayList<>();
        Cursor cursor = database.GetProduct(CustomerID, ProductGroupID);
        while (cursor.moveToNext()){

            SellModel sellModel = new SellModel();
            sellModel.setProductID(cursor.getString(0));
            sellModel.setProductName(cursor.getString(1));
            sellModel.setProductGroupID(cursor.getString(2));
            sellModel.setStockQty(CheckInt(cursor.getString(3)));
            sellModel.setOrderQty(CheckInt(cursor.getString(4)));
            sellModel.setStockBillOne(CheckInt(cursor.getString(5)));
            sellModel.setPOBillOne(CheckInt(cursor.getString(6)));
            sellModel.setStockBillTwo(CheckInt(cursor.getString(7)));
            sellModel.setPOBillTwo(CheckInt(cursor.getString(8)));
            arrayList.add(sellModel);

        }

        return arrayList;
    }
    private static Integer CheckInt(String Int)
    {
        int num = 0;

        if(Int != null)
        {
            num = Integer.parseInt(Int);
        }
        return num;

    }

    private void setupViewPager(ViewPager viewPager,String CustomerID,String DocNo){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new WaferFragment(CustomerID, DocNo),"เวเฟอร์");
        adapter.addFrag(new ChocolateFragment(CustomerID, DocNo),"ช็อคโกแลต");
        adapter.addFrag(new YoyoFragment(CustomerID, DocNo),"โยโย");
        adapter.addFrag(new GummyFragment(CustomerID, DocNo),"กัมมี่");
        adapter.addFrag(new ToroFragment(CustomerID, DocNo),"โตโร");
        adapter.addFrag(new CandyFragment(CustomerID, DocNo),"ลูกอม");
        adapter.addFrag(new BiscuitFragment(CustomerID, DocNo),"บิสกิต");
        adapter.addFrag(new GapFragment(CustomerID, DocNo),"แก๊ป");
        adapter.addFrag(new AlgaeFragment(CustomerID, DocNo),"สาหร่าย");
        adapter.addFrag(new LegumeFragment(CustomerID, DocNo),"ถั่ว");
        adapter.addFrag(new FishChipFragment(CustomerID, DocNo),"ปลาแผ่น");
        adapter.addFrag(new TradingFragment(CustomerID, DocNo),"Trading");
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}