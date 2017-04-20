package com.example.oliviarizkyarums.katalogbuku_oliv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oliviarizkyarums.katalogbuku_oliv.R;
import com.example.oliviarizkyarums.katalogbuku_oliv.model.Book;

import butterknife.BindView;

public class BookFormActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editBookTitle)
    EditText editBookTitle;
    @BindView(R.id.editBookAuthor)
    EditText editBookAuthor;
    @BindView(R.id.editBookGenre)
    EditText editBookGenre;
    @BindView(R.id.editIsbn)
    EditText editISBN;
    @BindView(R.id.editBookYear)
    EditText editPublisYear;
    @BindView(R.id.editBookSynopsis)
    EditText editSynopsis;
    @BindView(R.id.buttonSave)
    Button buttonSave;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            book = (Book) bundle.getSerializable("bookEdit");
            editISBN.setText(book.getISBN());
            editPublisYear.setText(book.getPublished_year() + "");
            editBookAuthor.setText(book.getBook_author());
            editBookTitle.setText(book.getBook_title());
            editBookGenre.setText(book.getBook_genre());
            editSynopsis.setText(book.getBook_synopsis());
            buttonSave.setEnabled(false);
            getSupportActionBar().setTitle(book.getBook_title());
        } else {
            book = new Book();
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    book.setISBN(editISBN.getText().toString());
                    book.setBook_title(editBookTitle.getText().toString());
                    book.setBook_author(editBookAuthor.getText().toString());
                    book.setPublished_year(Integer.parseInt(editPublisYear.getText().toString()));
                    book.setBook_genre(editBookGenre.getText().toString());
                    book.setBook_synopsis(editSynopsis.getText().toString().equals("") ? "-" : editSynopsis.getText().toString());

                    Intent i = new Intent();
                    i.putExtra("book", book);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validate() {
        boolean valid = true;

        String isbn = editISBN.getText().toString();
        String booktitle = editBookTitle.getText().toString();
        String bookauthor = editBookAuthor.getText().toString();
        String publishedYear = editPublisYear.getText().toString();

        if (isbn.isEmpty()) {
            editISBN.setError("Enter ISBN");
            valid = false;
        } else {
            editISBN.setError(null);
        }

        if (booktitle.isEmpty()) {
            editBookTitle.setError("enter book title");
            valid = true;
        } else {
            editBookTitle.setError(null);
        }

        if (bookauthor.isEmpty()) {
            editBookAuthor.setError("Enter Book Author");
            valid = true;
        } else {
            editBookAuthor.setError(null);
        }

        if (publishedYear.isEmpty() || publishedYear.length() < 4) {
            editPublisYear.setError("Publish Year empty or must in yyyy format");
            valid = false;
        } else {
            editPublisYear.setError(null);
        }
        return valid;
    }

}
