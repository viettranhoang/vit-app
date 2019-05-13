package com.vit.vitapp.ui.contact.adapter.listener;

import com.vit.presentation.features.contact.model.ContactViewData;
import com.vit.vitapp.ui.base.BaseOnClickItemListener;

public interface OnClickContactItemListener extends BaseOnClickItemListener {

    void onClickContact(ContactViewData contact);
}
