package cycu.nclab.moocs.bookkeeper2018;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;



public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
//public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnClickListener{

    private OnDialogDoneListener mListener;

    private static final String ARG_PARAM1 = "param1";

    static Calendar c;
    static SimpleDateFormat dbFormat = new java.text.SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

    public DatePickerFragment() {
        // Required empty public constructor
    }

    public static DatePickerFragment newInstance(Calendar c) {

        //DatePickerFragment.c = Calendar.getInstance();
        DatePickerFragment.c = c;

        DatePickerFragment fragment = new DatePickerFragment();

        String param1 = dbFormat.format(c.getTime());
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
//        c = Calendar.getInstance();
//        try {
//            c.setTime(dbFormat.parse(getArguments().getString(ARG_PARAM1)));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day){
//            @Override
//            public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {
//                super.onDateChanged(view, year, month, dayOfMonth);
//                Log.d("DateDialog", "dayOfMonth = " + dayOfMonth);
//
//                onDateSet(view, year, month, dayOfMonth);
//                this.dismiss();
//            }

            @Override
            public void onDateChanged(@androidx.annotation.NonNull DatePicker view, int year, int month, int dayOfMonth) {
                super.onDateChanged(view, year, month, dayOfMonth);
                onDateSet(view, year, month, dayOfMonth);
                this.dismiss();
            }
        };
    }



    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        c.set(year, month, day);
        String result = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",
                Locale.getDefault()).format(c.getTime());
        if (mListener != null) {
            mListener.onDialogDone(getTag(), 0, result);;
        }
    }



//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDialogDoneListener) {
            mListener = (OnDialogDoneListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDialogDoneListener");
        }
    }
//
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    @Override
//    public void onClick(DialogInterface dialog, int which) {
//        Log.d("DateDialog", "which = " + which);
//    }

    //
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onDateSet(Calendar c);
//    }
}
