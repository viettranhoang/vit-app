package com.vit.vitapp.ui.contact.adapter;

import com.vit.presentation.features.contact.model.ContactViewData;
import com.vit.vitapp.R;
import com.vit.vitapp.ui.base.BaseAdapter;
import com.vit.vitapp.ui.base.BaseOnClickItemListener;
import com.vit.vitapp.ui.contact.adapter.listener.OnClickContactItemListener;

import javax.inject.Inject;

public class ContactAdapter extends BaseAdapter<ContactViewData> {

//    @Inject
//    OnClickContactItemListener listener;

    @Inject
    public ContactAdapter() {
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.contact_item;
    }

    @Override
    public BaseOnClickItemListener getListener() {
        return null;
    }
}
