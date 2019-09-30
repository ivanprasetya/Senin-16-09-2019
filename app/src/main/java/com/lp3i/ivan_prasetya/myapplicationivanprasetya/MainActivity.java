package com.lp3i.ivan_prasetya.myapplicationivanprasetya;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //persiapan variable object yang ada pada XML
    EditText etNama, etAlamat;
    Spinner spProdi;
    CheckBox cbValid;
    RadioGroup rgLulusan;
    Button btSimpan, btHapus, btToast, btNotifikasi, btExit, btSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //definisi dan sambungkan objek yang ada pada XML
        etNama = (EditText) findViewById(R.id.et_nama);
        etAlamat = (EditText) findViewById(R.id.et_alamat);
        spProdi = (Spinner) findViewById(R.id.sp_jk);
        rgLulusan = (RadioGroup) findViewById(R.id.rg_lulusan);
        cbValid = (CheckBox) findViewById(R.id.cb_valid);
        btSimpan = (Button) findViewById(R.id.bt_simpan);
        btHapus = (Button) findViewById(R.id.bt_hapus);
        btToast = (Button) findViewById(R.id.bt_toast);
        btNotifikasi = (Button) findViewById(R.id.bt_notifikasi);
        btExit = (Button) findViewById(R.id.bt_exit);
        btSnackbar = (Button) findViewById(R.id.bt_snack_bar);

        final RadioButton rbSMA = (RadioButton) findViewById(R.id.rb_SMA);
        rbSMA.setChecked(true);

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, alamat, prodi, lulusan;
                boolean valid;

                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                prodi = spProdi.getSelectedItem().toString();


                //Toast.makeText(
                //getApplicationContext(),
                //"Hai " + etNama.getText().toString(),
                //Toast.LENGTH_SHORT)
                //.show();

                //atau bisa juga di tampilkan pada log

                //ambil nilai radio grup
                int selectedId = rgLulusan.getCheckedRadioButtonId();
                RadioButton rbLulus = (RadioButton) findViewById(selectedId);

                lulusan = rbLulus.getText().toString();

                //ambil nilai dari checkbox
                valid = cbValid.isChecked();

                if (!valid) { //kalo ga valid maka gagal
                    Toast.makeText(getApplicationContext(),
                            "Formulir belum terkonfirmasi",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                //kalo belum ngisi nama dan alamat, maka gagal
                if(nama.length() == 0 || alamat.length() ==0){
                    Toast.makeText(getApplicationContext(),
                            "Lengkapi Formulir",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i = new Intent(MainActivity.this,
                        DetailActivity.class);
                //selipkan data
                i.putExtra("i_nama", nama);
                i.putExtra("i_alamat", alamat);
                i.putExtra("i_prodi", prodi);
                i.putExtra("i_lulusan", lulusan);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);


                //Log.d("test",
                        //etAlamat.getText().toString() + " " + spProdi.getSelectedItem().toString());
            }
        });

        btToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getApplicationContext(),
                        "Hai ini toast",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        btNotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notifikasi
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), "notify_001");
                Intent ii = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle("Aplikasi buatanku");
                bigText.setSummaryText("Ini adalah notifikasi dariku");

                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
                mBuilder.setContentTitle("Aplikasi buatanku");
                mBuilder.setContentText("Notifikasi ini menggunakan versi terbaru");
                mBuilder.setPriority(Notification.PRIORITY_MAX);
                mBuilder.setStyle(bigText);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND); //suara
                mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000}); //getar

                NotificationManager mNotificationManager =
                        (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("notify_001",
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    mNotificationManager.createNotificationChannel(channel);
                }

                mNotificationManager.notify(0, mBuilder.build());
            }
        });

        btHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.ic_delete_forever)
                        .setTitle("Hapus data ini")
                        .setMessage("Ini dialog box")
                        .setCancelable(true)
                        .setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "Ok ditekan",
                                                Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })
                        .setNegativeButton(
                                "Batal",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "Batal ditekan",
                                                Toast.LENGTH_SHORT)
                                                .show();

                                    }
                                }).show();
            }
        });

        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = findViewById(R.id.main_layout_id);
                String pesan = "Hai, Aku snack bar";
                int durasi = Snackbar.LENGTH_SHORT;
                Snackbar.make(view, pesan, durasi).show();
            }
        });
    }

    //oncreate menunya alt + insert --> override methods --> oncreateoptionsmenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_ku, menu);

        //definisi searchview agar dapat diolah text nya
        MenuItem menuItem = menu.findItem(R.id.mn_cari);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(),
                        newText, Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        
        return super.onCreateOptionsMenu(menu);
    }



    /*
        menu perlu diberikan aksi
        alt + insert --> override methods --> onOptionsItemSelected
         */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_alert:
            //masukkan alert
            return true;
            case R.id.mn_snack:
            //masukkan snack
            return true;
            case R.id.mn_toast:
            //masukkan toast
                Toast.makeText(
                        getApplicationContext(),
                        "Ok ditekan",
                        Toast.LENGTH_SHORT)
                        .show();
            return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }


}