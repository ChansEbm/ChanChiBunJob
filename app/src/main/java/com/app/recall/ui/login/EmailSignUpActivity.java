package com.app.recall.ui.login;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.contract.SignUpContract;
import com.app.recall.presenter.EmailSignUpPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class EmailSignUpActivity extends BaseActivity<EmailSignUpPresenterImpl> implements
                                                                           SignUpContract.View,
                                                                           LoaderManager
                                                                                   .LoaderCallbacks<Cursor> {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private static final int REQUEST_READ_CONTACTS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale,
                          Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok,
                                                                new View.OnClickListener() {
                                                                    @Override
                                                                    @TargetApi(Build
                                                                            .VERSION_CODES.M)
                                                                    public void onClick(View v) {
                                                                        requestPermissions(
                                                                                new String[]{READ_CONTACTS},
                                                                                REQUEST_READ_CONTACTS);
                                                                    }
                                                                });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    @Override
    protected EmailSignUpPresenterImpl initPresenter() {
        return new EmailSignUpPresenterImpl();
    }


    @Override
    protected void initViews() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        populateAutoComplete();

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {
        switch (id) {
            case R.id.confirm_email_bottom:
                attemptLogin();
                break;
        }
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        View focusView = null;
        if (!presenter.isEmailValid(email) && TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
        }
        if (!presenter.isPasswordValid(password) && TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
        }
        if (focusView != null) {
            focusView.requestFocus();
        } else {

        }

    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_email_sign_up;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return false;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader: " + id);
        return new CursorLoader(this,
                                // Retrieve data rows for the device user's 'profile' contact.
                                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                                                     ContactsContract.Contacts.Data
                                                             .CONTENT_DIRECTORY),
                                ProfileQuery.PROJECTION,

                                // Select only email addresses.
                                ContactsContract.Contacts.Data.MIMETYPE + " = ?",
                                new String[]{ContactsContract.CommonDataKinds.Email
                                        .CONTENT_ITEM_TYPE},

                                // Show primary email addresses first. Note that there won't be
                                // a primary email address if the user hasn't specified one.
                                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
        addEmailsToAutoComplete(emails);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(EmailSignUpActivity.this,
                                                          android.R.layout
                                                                  .simple_dropdown_item_1line,
                                                          emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    private interface ProfileQuery {
        String[] PROJECTION = {ContactsContract.CommonDataKinds.Email.ADDRESS, ContactsContract
                .CommonDataKinds.Email.IS_PRIMARY,};

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

}
