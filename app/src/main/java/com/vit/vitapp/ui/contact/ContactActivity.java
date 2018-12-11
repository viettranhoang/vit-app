package com.vit.vitapp.ui.contact;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vit.vitapp.R;
import com.vit.vitapp.data.model.Contact;
import com.vit.vitapp.ui.base.BaseActivity;
import com.vit.vitapp.ui.contact.adapter.ContactAdapter;
import com.vit.vitapp.ui.contact.listener.OnClickContactItemListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnTextChanged;

public class ContactActivity extends BaseActivity implements ContactContract.View,
        OnClickContactItemListener {


    @BindView(R.id.list_contact)
    RecyclerView mRcvContact;

    @BindView(R.id.layout_root)
    LinearLayout mLayoutRoot;

    @BindView(R.id.input_search)
    EditText mInputSearch;


    @Inject
    ContactAdapter adapter;

    @Inject
    ContactContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.contact_activity;
    }

    @Override
    protected void initView() {
        initToolbar();
        initRcv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @OnTextChanged(R.id.input_search)
    void onClickSearch() {
        presenter.searchContacts(mInputSearch.getText().toString());
    }

    @Override
    public void showContacts(List<Contact> contacts) {
        adapter.setList(contacts);
    }

    @Override
    public void showError(Throwable e) {
        showSnackbar(mLayoutRoot, e.getMessage());
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact");
    }

    private void initRcv() {
        mRcvContact.setLayoutManager(new LinearLayoutManager(this));
        mRcvContact.setHasFixedSize(true);
        mRcvContact.setItemAnimator(new DefaultItemAnimator());
        mRcvContact.setAdapter(adapter);
    }

    @Override
    public void onClickContact(Contact contact) {
        Toast.makeText(this, contact.toString(), Toast.LENGTH_SHORT).show();
    }
}
