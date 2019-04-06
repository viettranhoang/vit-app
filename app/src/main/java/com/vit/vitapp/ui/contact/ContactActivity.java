package com.vit.vitapp.ui.contact;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vit.data.features.contact.source.ContactRemote;
import com.vit.presentation.features.contact.GetContactListViewModel;
import com.vit.presentation.features.contact.model.ContactViewData;
import com.vit.vitapp.R;
import com.vit.vitapp.ui.base.BaseActivity;
import com.vit.vitapp.ui.contact.adapter.ContactAdapter;
import com.vit.vitapp.ui.contact.listener.OnClickContactItemListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnTextChanged;

public class ContactActivity extends BaseActivity implements OnClickContactItemListener {

    @BindView(R.id.list_contact)
    RecyclerView mRcvContact;

    @BindView(R.id.layout_root)
    LinearLayout mLayoutRoot;

    @BindView(R.id.input_search)
    EditText mInputSearch;

    @Inject
    ContactAdapter adapter;

    private GetContactListViewModel getContactListViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.contact_activity;
    }

    @Override
    protected void initView() {
        initToolbar();
        initRcv();


        getContactListViewModel = ViewModelProviders.of(this, mViewModelFactory).get(GetContactListViewModel.class);

        getContactList();
    }

    private void getContactList() {
        getContactListViewModel.getContactList().observe(this, resource -> {
            switch (resource.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    adapter.setList((List<ContactViewData>) resource.getData());
                    break;
                case ERROR:

                    break;
            }
        });
    }


    @OnTextChanged(R.id.input_search)
    void onClickSearch() {
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
    public void onClickContact(ContactViewData contact) {
        Toast.makeText(this, contact.toString(), Toast.LENGTH_SHORT).show();
    }
}
