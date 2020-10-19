package com.unitedfoods.allcashu.Customer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Magnifier;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.unitedfoods.allcashu.BranchWarehouseContract;
import com.unitedfoods.allcashu.CustomerContract;
import com.unitedfoods.allcashu.DB;
import com.unitedfoods.allcashu.Home.Home;
import com.unitedfoods.allcashu.Model.CustomerModel;
import com.unitedfoods.allcashu.PDAContract;
import com.unitedfoods.allcashu.Prepare.PrepareFragment;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.ShopTypeContract;
import com.unitedfoods.allcashu.Uitls.AreaSpinners.AreaAdapter;
import com.unitedfoods.allcashu.Uitls.AreaSpinners.AreaSpinnerModel;
import com.unitedfoods.allcashu.Uitls.DistrictSpinners.DistrictAdapter;
import com.unitedfoods.allcashu.Uitls.DistrictSpinners.DistrictSpinnerModel;
import com.unitedfoods.allcashu.Uitls.MyDialog;
import com.unitedfoods.allcashu.Uitls.ProvinceSpinners.ProvinceAdapter;
import com.unitedfoods.allcashu.Uitls.ProvinceSpinners.ProvinceSpinnerModel;
import com.unitedfoods.allcashu.Uitls.ShopTypeSpinners.ShopTypeAdapter;
import com.unitedfoods.allcashu.Uitls.ShopTypeSpinners.ShopTypeSpinnerModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class AddCustomerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int PERMISSION_CODE = 1000;
    private DB database = DB.getInstance(this);
    SQLiteDatabase db;
    ArrayList<ProvinceSpinnerModel> provinceSpinnerModelArrayList;
    ArrayList<DistrictSpinnerModel> districtSpinnerModelArrayList;
    ArrayList<AreaSpinnerModel> areaSpinnerModelArrayList;
    ArrayList<ShopTypeSpinnerModel> shopTypeSpinnerModelArrayList;
    Spinner provinceSpinner,districtSpinner,areaSpinner,shopSpinner;
    ImageView imageViewAddCus;
    Uri image_uri;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    String WHID,EMPID,SalareaID,Province,District,Area,ShopType,path,AreaName,DistrictName,ProvinceName;
    TextInputEditText editText_CusName,editText_Billto,editText_Tel,editText_Village;
    CheckBox TaxCheck;
    String CustomerID;
    int p,a,d;
    int P,A,D,S;
    private static final String TAG = "AddCustomerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        provinceSpinner = findViewById(R.id.spinner_Province);
        districtSpinner = findViewById(R.id.spinner_District);
        areaSpinner = findViewById(R.id.spinner_SaleArea);
        shopSpinner = findViewById(R.id.spinner_ShopType);
        imageViewAddCus = findViewById(R.id.imageViewAddCus);
        editText_CusName = findViewById(R.id.editText_CustomerName);
        editText_Billto = findViewById(R.id.editText_Address);
        editText_Tel = findViewById(R.id.editText_Tel);
        editText_Village = findViewById(R.id.editText_Village);
        TaxCheck = findViewById(R.id.chb_Tax);

        SalareaID = getIntent().getStringExtra("SalareaID");
        CustomerID = getIntent().getStringExtra("CustomerID");
         db = database.getReadableDatabase();
         WHID = null;
        String SQL = "SELECT Setting.cfgValue, BranchWarehouse.TitleName,BranchWarehouse.FirstName FROM Setting" +
                " LEFT JOIN BranchWarehouse on Setting.cfgValue =  BranchWarehouse.WHID" +
                " Where Setting.cfgName = 'WHID' ";
        Cursor cursor = db.rawQuery(SQL,null);
        while (cursor.moveToNext()){
            WHID = cursor.getString(0);
        }

        ProvinceSpinner();
        ShopTypeSpinner();

        if (CustomerID != null){

            String SQl_Customer = "SELECT "+ CustomerContract.Columns.TASK_CustName+","
                    +CustomerContract.Columns.TASK_AddressNo+","
                    +CustomerContract.Columns.TASK_Moo+","
                    +CustomerContract.Columns.TASK_Tel+","
                    +CustomerContract.Columns.TASK_ProvinceID+","
                    +CustomerContract.Columns.TASK_AreaID+","
                    +CustomerContract.Columns.TASK_DistrictID+","
                    +CustomerContract.Columns.TASK_SHOPTYPEID+","
                    +CustomerContract.Columns.TASK_FlagBill+","
                    +CustomerContract.Columns.TASK_CustImage+
                    " From "+CustomerContract.TABLE_NAME+
                    " WHERE "+CustomerContract.Columns.TASK_CUSTOMERID+" =?";
            Cursor cursor1 = db.rawQuery(SQl_Customer,new String[]{CustomerID});
            while (cursor1.moveToNext())
            {

                editText_CusName.setText(cursor1.getString(0));
                editText_Billto.setText(cursor1.getString(1));
                editText_Village.setText(cursor1.getString(2));
                editText_Tel.setText(cursor1.getString(3));


                boolean check = Boolean.parseBoolean(String.valueOf(cursor1.getString(8)));
                if (!check){

                    TaxCheck.setChecked(false);

                }
                else {


                    TaxCheck.setChecked(true);

                }

                //Fix Bug, if can't find a image.
                path = cursor1.getString(9);
                if (path == null || path.equals(" "))
                {

                }
                else {
                    File imgFile = new  File(cursor1.getString(9));

                    if(imgFile.exists()){

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        imageViewAddCus.setImageBitmap(myBitmap);

                    }

                }


                for (int s=0; s < shopTypeSpinnerModelArrayList.size();s++)
                {
                    ShopTypeSpinnerModel shopTypeSpinnerModel = shopTypeSpinnerModelArrayList.get(s);
                    if(cursor1.getString(7).equals(shopTypeSpinnerModel.getShopTypeCode()))
                    {
                        shopSpinner.setSelection(s);
                        ShopType = shopTypeSpinnerModel.getShopTypeCode();
                        S = s;
                    }
                }
                for (p=0;p < provinceSpinnerModelArrayList.size();p++)
                {   ProvinceSpinnerModel provinceSpinnerModel =  provinceSpinnerModelArrayList.get(p);
                    if (cursor1.getString(4).equals(provinceSpinnerModel.getProvinceID())){
                        Log.d(TAG, "onCreate: "+p);
                        P = p;
                       provinceSpinner.setSelection(P);
                        Province = provinceSpinnerModel.getProvinceID();
                        AreaSpinner(Province);


                        for ( a =0; a < areaSpinnerModelArrayList.size();a++)
                        {
                            AreaSpinnerModel areaSpinnerModel = areaSpinnerModelArrayList.get(a);
                            if (cursor1.getString(5).equals(areaSpinnerModel.getAreaID()))
                            {
                                Log.d(TAG, "onCreate: "+a);
                                areaSpinner.setSelection(a);
                                Area = areaSpinnerModel.getAreaID();
                                DistrictSpinner(Area);
                                A=a;

                                for (d =0; d < districtSpinnerModelArrayList.size();d++)
                                {
                                    DistrictSpinnerModel districtSpinnerModel = districtSpinnerModelArrayList.get(d);
                                    if (cursor1.getString(6).equals(districtSpinnerModel.getDistrictID()))
                                    {
                                        Log.d(TAG, "onCreate: "+d);
                                        districtSpinner.setSelection(d);
                                        D =d;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    ProvinceSpinnerModel provinceSpinnerModel = (ProvinceSpinnerModel)parent.getItemAtPosition(position);
                    Province = provinceSpinnerModel.getProvinceID();
                    ProvinceName = provinceSpinnerModel.getProvinceName();
                    AreaSpinner(Province);
                    if (A != 0){
                        areaSpinner.setSelection(A);
                        A = 0;
                    }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    DistrictSpinnerModel districtSpinnerModel = (DistrictSpinnerModel)parent.getItemAtPosition(position);
                    District = districtSpinnerModel.getDistrictID();
                    DistrictName = districtSpinnerModel.getDistrictName();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    AreaSpinnerModel areaSpinnerModel = (AreaSpinnerModel)parent.getItemAtPosition(position);
                    Area = areaSpinnerModel.getAreaID();
                    AreaName = areaSpinnerModel.getAreaName();
                    DistrictSpinner(Area);
                    if (D != 0)
                    {
                        districtSpinner.setSelection(D);
                        D = 0;
                    }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        shopSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ShopTypeSpinnerModel shopTypeSpinnerModel = (ShopTypeSpinnerModel)parent.getItemAtPosition(position);
                ShopType = shopTypeSpinnerModel.getShopTypeCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        imageViewAddCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void openCamera() {

        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.TITLE,"New Picture");
//        values.put(MediaStore.Images.Media.DESCRIPTION,"From the Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
         startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else {
                    Toast.makeText(this,"Permission denied...",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            imageViewAddCus.setImageURI(image_uri);

             path = getRealPathFromURI(image_uri);
            System.out.println(path);

        }
    }
    public String getRealPathFromURI(Uri uri)
    {
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    private void EditCustomerDetail(){
        String BillTo = editText_Billto.getText().toString()+" ม."+editText_Village.getText().toString()+" ต."+DistrictName+" อ."+AreaName+" จ."+ProvinceName;
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerID(CustomerID);
        customerModel.setShopTypeID(ShopType);
        customerModel.setCustName(Objects.requireNonNull(editText_CusName.getText()).toString());
        customerModel.setCustShortName(Objects.requireNonNull(editText_CusName.getText()).toString());
        customerModel.setCustImage(path);
        customerModel.setBillTo(BillTo);
        customerModel.setTel(Objects.requireNonNull(editText_Tel.getText()).toString());
        customerModel.setContact(editText_Billto.getText().toString());
        customerModel.setAddressNo(Objects.requireNonNull(editText_Billto.getText()).toString());
        customerModel.setMoo(Objects.requireNonNull(editText_Village.getText()).toString());
        customerModel.setAreaID(Area);
        customerModel.setDistrictID(District);
        customerModel.setProvinceID(Province);
        customerModel.setFlagEdit("1");
        if (TaxCheck.isChecked()){

            customerModel.setFlagBill("TRUE");

        }
        else {

            customerModel.setFlagBill("FALSE");

        }
        database.UpdateCustomer(customerModel);

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable( false );
        builder.setIcon( R.drawable.ic_alert );
        builder.setTitle( "แก้ไขสำเร็จ" );
        builder.setMessage( "การแก้ไขข้อมูลร้านค้าสำเร็จ" );
        builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(getBaseContext(),CustomerActivity.class);
                startActivity(intent);

            }
        } );
        builder.show();

    }
    private void CreateNewCustomer(){

        db = database.getReadableDatabase();
        String SQL_EMPID = "SELECT  "+ BranchWarehouseContract.Columns.TASK_EMPID +
                            " FROM "+BranchWarehouseContract.TABLE_NAME+
                            " Where "+BranchWarehouseContract.Columns.TASK_WHID+" = ? ";
        Cursor cursor = db.rawQuery(SQL_EMPID,new String[]{WHID});
        while (cursor.moveToNext()){
            EMPID = cursor.getString(0);
        }
        String BillTo = editText_Billto.getText().toString()+" ม."+editText_Village.getText().toString()+" ต."+DistrictName+" อ."+AreaName+" จ."+ProvinceName;
        CustomerModel customerModel = new CustomerModel();
        customerModel.setBranchID(WHID.substring(0,3));
        customerModel.setWHID(WHID);
        customerModel.setEmpID(EMPID);
        customerModel.setSalAreaID(SalareaID);
        customerModel.setShopTypeID(ShopType);
        customerModel.setCustName(Objects.requireNonNull(editText_CusName.getText()).toString());
        customerModel.setCustShortName(Objects.requireNonNull(editText_CusName.getText()).toString());
        customerModel.setCustImage(path);
        customerModel.setBillTo(BillTo);
        customerModel.setTel(Objects.requireNonNull(editText_Tel.getText()).toString());
        customerModel.setContact(editText_Billto.getText().toString());
        customerModel.setAddressNo(Objects.requireNonNull(editText_Billto.getText()).toString());
        customerModel.setMoo(Objects.requireNonNull(editText_Village.getText()).toString());
        customerModel.setAreaID(Area);
        customerModel.setDistrictID(District);
        customerModel.setProvinceID(Province);
        customerModel.setFlagNew("1");
        customerModel.setFlagEdit("0");
        if (TaxCheck.isChecked()){

            customerModel.setFlagBill("TRUE");

        }
        else {

            customerModel.setFlagBill("FALSE");

        }
        database.CreateCustomer(customerModel);

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable( false );
        builder.setIcon( R.drawable.ic_alert );
        builder.setTitle( "บันทึกสำเร็จ" );
        builder.setMessage( "บันทึกข้อมูลร้านค้าสำเร็จ" );
        builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(getBaseContext(),CustomerActivity.class);
                startActivity(intent);

            }
        } );
        builder.show();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save){
            if (CustomerID == null)
            {
                if (Objects.requireNonNull(editText_CusName.getText()).toString().equals("")
                        | Objects.requireNonNull(editText_Billto.getText()).toString().equals("")
                        | (Objects.requireNonNull(editText_Tel.getText()).toString().equals(""))){
                    MyDialog dialog = new MyDialog(this);
                    dialog.normalDialog("แจ้งเตือน","โปรดกรอกข้อมูลให้ครบทุกช่อง");
                }
                else {

                    CreateNewCustomer();
                }
            }
            else {
                if (Objects.requireNonNull(editText_CusName.getText()).toString().equals("")
                        | Objects.requireNonNull(editText_Billto.getText()).toString().equals("")
                        | (Objects.requireNonNull(editText_Tel.getText()).toString().equals(""))){
                    MyDialog dialog = new MyDialog(this);
                    dialog.normalDialog("แจ้งเตือน","โปรดกรอกข้อมูลให้ครบทุกช่อง");
                }
                else {

                    EditCustomerDetail();
                }
            }



        }
        else if (id == R.id.action_cancel){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (id == R.id.nav_customer){
            Intent intent = new Intent(this, CustomerActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_sell){

        }
        else if (id == R.id.nav_edit_sell)
        {

        }
        else if (id == R.id.nav_change){

        }
        else if (id == R.id.nav_withdraw)
        {

        }
        else if (id == R.id.nav_conclude)
        {

        }
        else if (id == R.id.nav_announce){

        }
        else if (id == R.id.nav_stock){

        }
        else if (id == R.id.nav_send){

        }
        else if (id == R.id.nav_to_prepare){

            Intent intent = new Intent(this, Home.class);
            intent.putExtra("page","Prepare");
            startActivity(intent);

        }
        return false;
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private void ProvinceSpinner(){

        SQLiteDatabase db = database.getReadableDatabase();
        String SQL_Province = "select distinct "+ PDAContract.Columns.TASK_PROVINCEID+
                ","+PDAContract.Columns.TASK_PROVINCENAME+
                " FROM "+PDAContract.TABLE_NAME;
        Cursor cursor =  db.rawQuery(SQL_Province,null);
        provinceSpinnerModelArrayList = new ArrayList<>();
        while (cursor.moveToNext()){

            provinceSpinnerModelArrayList.add(new ProvinceSpinnerModel(cursor.getString(0),cursor.getString(1)));
        }

        ProvinceAdapter provinceAdapter = new ProvinceAdapter(this,provinceSpinnerModelArrayList);
        provinceSpinner.setAdapter(provinceAdapter);
    }
    private void DistrictSpinner(String AreaID){

        SQLiteDatabase db = database.getReadableDatabase();
        String SQL_District = "select distinct "+ PDAContract.Columns.TASK_DISTRICTID+
                ","+PDAContract.Columns.TASK_DISTRICTNAME+
                ","+PDAContract.Columns.TASK_DISTRICTCODE+
                " FROM "+PDAContract.TABLE_NAME+
                " Where "+PDAContract.Columns.TASK_AREAID+" =?";
        Cursor cursor =  db.rawQuery(SQL_District,new String[]{AreaID});
        districtSpinnerModelArrayList = new ArrayList<>();
        while (cursor.moveToNext()){

            districtSpinnerModelArrayList.add(new DistrictSpinnerModel(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
        }

        DistrictAdapter districtAdapter = new DistrictAdapter(this,districtSpinnerModelArrayList);
        districtSpinner.setAdapter(districtAdapter);
    }
    private void AreaSpinner(String ProvinceID){

        SQLiteDatabase db = database.getReadableDatabase();
        String SQL_District = "select distinct "+ PDAContract.Columns.TASK_AREAID+
                ","+PDAContract.Columns.TASK_AREANAME+
                " FROM "+PDAContract.TABLE_NAME+
                " WHERE "+PDAContract.Columns.TASK_PROVINCEID+" =?";
        Cursor cursor =  db.rawQuery(SQL_District,new String[]{ProvinceID});
        areaSpinnerModelArrayList = new ArrayList<>();
        while (cursor.moveToNext()){

            areaSpinnerModelArrayList.add(new AreaSpinnerModel(cursor.getString(0),cursor.getString(1)));
        }

        AreaAdapter areaAdapter = new AreaAdapter(this,areaSpinnerModelArrayList);
        areaSpinner.setAdapter(areaAdapter);
    }
    private void ShopTypeSpinner() {
        SQLiteDatabase db = database.getReadableDatabase();
        String SQL_ShopType = "select * from "+ ShopTypeContract.TABLE_NAME;
        Cursor cursor =  db.rawQuery(SQL_ShopType,null);
        shopTypeSpinnerModelArrayList = new ArrayList<>();
        while (cursor.moveToNext()){

            shopTypeSpinnerModelArrayList.add(new ShopTypeSpinnerModel(cursor.getString(0),cursor.getString(1)));
        }

        ShopTypeAdapter shopTypeAdapter = new ShopTypeAdapter(this,shopTypeSpinnerModelArrayList);
        shopSpinner.setAdapter(shopTypeAdapter);
    }
}
