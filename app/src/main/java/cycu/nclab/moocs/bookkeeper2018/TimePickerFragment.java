/* Usage:
      // 使用現在時間當作初始時間
  1.  DialogFragment timeFragment = TimePickerFragment.newInstance();
      timeFragment.show(getSupportFragmentManager(), "timePicker");
      // 輸入參數作為初始時間
  2.  DialogFragment timeFragment = TimePickerFragment.newInstance(Calendar c);
      timeFragment.show(getSupportFragmentManager(), "timePicker");


 */

package cycu.nclab.moocs.bookkeeper2018;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Locale;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    final String TAG = this.getClass().getSimpleName();

    private OnDialogDoneListener mListener;
    private static final String ARG_PARAM1 = "param1";

    Calendar c;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        c = Calendar.getInstance();
        Bundle args = getArguments();
        if (args != null) {
            c.setTimeInMillis(args.getLong(ARG_PARAM1));
        }

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity())) {
//            @Override
//            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                super.onTimeChanged(view, hourOfDay, minute);
//                onTimeSet(view, hourOfDay, minute);
//                dismiss();
//            }

            @Override
            public void onClick(DialogInterface dialog, int which) {
                super.onClick(dialog, which);
                Log.d(TAG, "TimePickerDialog, onClick, which = " + which);
            }
        };
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        String result = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",
                Locale.getDefault()).format(c.getTime());
        if (mListener != null) {
            mListener.onDialogDone(getTag(), 0, result);;
        }
    }


    public static TimePickerFragment newInstance(Calendar c) {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, c.getTimeInMillis());
        fragment.setArguments(args);
        return fragment;
    }

    public static TimePickerFragment newInstance() {
        TimePickerFragment fragment = new TimePickerFragment();
        return fragment;
    }



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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link TimePickerFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link TimePickerFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class TimePickerFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public TimePickerFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment TimePickerFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static TimePickerFragment newInstance(String param1, String param2) {
//        TimePickerFragment fragment = new TimePickerFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
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
//        void onFragmentInteraction(Uri uri);
//    }
//}
