package com.vit.vitapp.ui.contact;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.Toolbar;
import com.vit.presentation.features.contact.GetContactListViewModel;
import com.vit.presentation.features.contact.model.ContactViewData;
import com.vit.vitapp.R;
import com.vit.vitapp.databinding.ContactActivityBinding;
import com.vit.vitapp.ui.base.BaseActivity;
import com.vit.vitapp.ui.contact.adapter.ContactAdapter;
import com.vit.vitapp.ui.contact.adapter.listener.OnClickContactItemListener;

import javax.inject.Inject;
import java.util.List;

public class ContactActivity extends BaseActivity implements OnClickContactItemListener {

    @Inject
    ContactAdapter adapter;

    private ContactActivityBinding binding;

    private GetContactListViewModel getContactListViewModel;

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.contact_activity);

        initToolbar();

        getContactListViewModel = ViewModelProviders.of(this, mViewModelFactory).get(GetContactListViewModel.class);
        binding.setAdapter(adapter);
        getContactList();
    }

    private void getContactList() {
        getContactListViewModel.getContactList().observe(this, resource -> {
            switch (resource.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    adapter.submitList((List<ContactViewData>) resource.getData());
                    break;
                case ERROR:
                    break;
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact");
    }

    @Override
    public void onClickContact(ContactViewData contact) {
        showToast(contact.getName());
    }
}
