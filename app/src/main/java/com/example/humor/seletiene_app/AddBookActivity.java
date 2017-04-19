package com.example.humor.seletiene_app;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddBookActivity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase dbSeLeTieneApp;
    ContentValues dataBD;

    EditText nameBook,authorBook,descripBook;
    Button   save;

    String name,author,description;

    private void captureData(){
        name = nameBook.getText().toString();
        author = authorBook.getText().toString();
        description = descripBook.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        sqLiteHelper = new SQLiteHelper(this,"dbSeLeTieneApp",null,1);
        dbSeLeTieneApp = sqLiteHelper.getWritableDatabase();

        nameBook = (EditText) findViewById(R.id.nameBook);
        authorBook = (EditText) findViewById(R.id.authorBook);
        descripBook = (EditText) findViewById(R.id.descripBook);
    }

    public void saveBook(View view){
        // Opción 1
        /*dataBD = new ContentValues();
        captureData();
        dataBD.put("libro",name);
        dataBD.put("autor",author);
        dataBD.put("descripcion",description);
        dbSeLeTieneApp.insert("ibros",null,dataBD);
        Toast.makeText(getApplicationContext(),"Libro Guardado",Toast.LENGTH_SHORT).show();
        */

        // Opción 2
        //dbSeLeTieneApp.execSQL("INSERT INTO libros VALUES (null,'"+name+"','"+author+"','"+description+"')");
        //Toast.makeText(getApplicationContext(),"Libro Guardado",Toast.LENGTH_SHORT).show();

        addBook();
    }

    public void searchBook(View view){

    }

    public void updateBook(View view){
        // Opcion 1
        captureData();
        dataBD.put("autor",author);
        dataBD.put("descripcion",description);
        dbSeLeTieneApp.update("libros",dataBD,"libro='"+name+"'",null);
        Toast.makeText(getApplicationContext(),"Libro Actualizado",Toast.LENGTH_SHORT).show();

        // Opcion 2
        //dbSeLeTieneApp.execSQL("UPDATE libros SET autor='"+author+"'" +
        //        ",descripcion='"+description+"' WHERE libro = '"+name+"'");
        //Toast.makeText(getApplicationContext(),"Libro Actualizado",Toast.LENGTH_SHORT).show();
    }

    public void deleteBook(View view){
        name = nameBook.getText().toString();
        dbSeLeTieneApp.delete("libros","libro='"+name+"'",null);
        Toast.makeText(getApplicationContext(),"Libro Eliminado",Toast.LENGTH_SHORT).show();
    }

    private void addBook(){

        class AddBook extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddBookActivity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> params = new HashMap<>();
                params.put("libro",name);
                params.put("autor",author);
                params.put("descripcion",description);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD,params);
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddBookActivity.this,"Libro añadido",Toast.LENGTH_SHORT).show();
            }
        }

        AddBook ae = new AddBook();
        ae.execute();
    }
}
