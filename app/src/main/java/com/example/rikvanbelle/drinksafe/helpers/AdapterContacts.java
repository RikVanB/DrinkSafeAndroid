package com.example.rikvanbelle.drinksafe.helpers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rikvanbelle.drinksafe.R;
import com.example.rikvanbelle.drinksafe.models.Beer;
import com.example.rikvanbelle.drinksafe.models.Contact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rik Van Belle on 7/12/2017.
 */

public class AdapterContacts extends ArrayAdapter<Contact> {
    private Activity currentActivity;
    private ArrayList<Contact> listContact;
    private static LayoutInflater inflater = null;

    public AdapterContacts(Activity activity, int textViewResourceId, ArrayList<Contact> _lContact) {
        super(activity, textViewResourceId, _lContact);
        this.listContact = _lContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_contact_layout, null);
        }

        Contact contact = listContact.get(position);
        TextView name = (TextView) v.findViewById(R.id.contact_name);
        TextView number = (TextView) v.findViewById(R.id.contanct_number);

        name.setText(contact.getName());
        number.setText(contact.getNumber());

        return v;
    }
}
