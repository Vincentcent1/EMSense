package com.example.android.emsense3.Fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import static com.example.android.emsense3.Activity.MainActivity.bundle;

public class MyAlertDialogFragment extends DialogFragment {
    public EditText editText;
    public String serialNumber = "default";

    /* The activity that creates an instance of this dialog fragment must
 * implement this interface in order to receive event callbacks.
 * Each method passes the DialogFragment in case the host needs to query it. */
    //Use this instance of the interface to deliver action events
    MyAlertDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //Verify that the host activity implement the callback interface
        try {
            //Instantiate the MyAlertDialogListener so we can send events to the host
            mListener = (MyAlertDialogListener) activity;
        } catch (ClassCastException e) {
            //The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement MyAlertDialogListener");
        }


    }

    //Override the Fragment.onAttach() method to instantiate the MyAlertDialogListener

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String dialogType = getArguments().getString("dialogType");
        switch (dialogType) {
            case "1A":
//                GIFView view = new GIFView(getActivity(), "C:\\Users\\setia\\AndroidStudioProjects\\EMSense3\\app\\src\\main\\assets\\gif_items.gif");
                editText = new EditText(getActivity());
                editText.setText("");
                editText.setSingleLine();
                builder.setTitle("ITEMS")
                        .setMessage("Enter the serial number:")
                        .setView(editText)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Send the positive button event back to the host Activity
                                serialNumber = editText.getText().toString();
                                bundle.putString("serialNumber", serialNumber);
                                mListener.onDialogPositiveClick(MyAlertDialogFragment.this);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Send the negative button event back to the host Activity
                                mListener.onDialogNegativeClick(MyAlertDialogFragment.this);

                            }
                        });
                break;
            case "1B":
                builder
                        .setTitle("ITEMS")
                        .setMessage("Touch the object with your phone and hold it there.\nPress Ok when you are done.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mListener.onDialogPositiveClick(MyAlertDialogFragment.this);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mListener.onDialogNegativeClick(MyAlertDialogFragment.this);
                            }
                        });
                break;
            case "2A":
                String objectName = getArguments().getString("objectName");
                String modelName = getArguments().getString("modelName");

                String title = objectName + " detected";
                String msg1 = "Model: " + modelName;
                String msg2 = "Save this object?";
                builder
                        .setTitle(title)
                        .setMessage(msg1 + "\n" + "\n" + msg2)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Save the object
                                mListener.onDialogPositiveClick(MyAlertDialogFragment.this);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mListener.onDialogNegativeClick(MyAlertDialogFragment.this);
                            }
                        });
                break;
            default:
                break;
        }

        final AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            }
        });


        return builder.create();

    }

    public interface MyAlertDialogListener {
        public void onDialogPositiveClick(DialogFragment dialogFragment);

        public void onDialogNegativeClick(DialogFragment dialogFragment);
    }


}

