package com.vit.vitapp.ui.contact;


import com.vit.vitapp.data.model.Contact;
import com.vit.vitapp.ui.base.BasePresenter;
import com.vit.vitapp.ui.base.BaseView;

import java.util.List;

public interface ContactContract {

    interface View extends BaseView<Presenter> {

        void showContacts(List<Contact> contacts);

        void showError(Throwable e);

    }

    interface Presenter extends BasePresenter {

        /**
         * search intance using rxjava on to remote or ROOM
         * @param keyword
         */
        void searchContacts(String keyword);

        /**
         * get contact from local(ROOM) or remote(Retrofit)
         */
        void loadContacts();

    }
}
