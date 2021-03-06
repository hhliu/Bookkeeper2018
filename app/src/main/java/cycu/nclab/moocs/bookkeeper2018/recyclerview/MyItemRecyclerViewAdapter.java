package cycu.nclab.moocs.bookkeeper2018.recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import cycu.nclab.moocs.bookkeeper2018.recyclerview.AccountListFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

import cycu.nclab.moocs.bookkeeper2018.R;
import cycu.nclab.moocs.bookkeeper2018.room.DB_r;
import cycu.nclab.moocs.bookkeeper2018.room.MoneyEntity;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MoneyEntity} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> implements ItemTouchHelperListener {

    final String TAG = this.getClass().getSimpleName();
    private final List<MoneyEntity> mValues;
    private final Context mListener;

    public MyItemRecyclerViewAdapter(List<MoneyEntity> accounts, Context listener) {
        mValues = accounts;
        mCursor = null;
        mListener = listener;
    }

    private Cursor mCursor ;
    public MyItemRecyclerViewAdapter(Cursor cursor, Context listener) {
        mCursor = cursor;
        mValues = null;
        mListener = listener;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        mCursor.moveToPosition(position);
        return mCursor.getInt(0);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_account_cell, parent, false);
        view.setOnClickListener(mItemAction);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mValues != null) {
            holder.mAccount = mValues.get(position);
            holder.mCategory.setText(mValues.get(position).getCategory());
            holder.mItem.setText(mValues.get(position).getItem());
            holder.mPrice.setText(String.valueOf(mValues.get(position).getPrice()));
        } else {
            holder.mAccount = null;
            String[] columnNames = mCursor.getColumnNames();
            mCursor.moveToPosition(position);
            holder.mCategory.setText(mCursor.getString(1));
            holder.mItem.setText(mCursor.getString(2));
            holder.mPrice.setText(String.valueOf(mCursor.getDouble(3)));
        }

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mAccount);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (mValues != null)
            return mValues.size();
        else
            return mCursor.getCount();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
    }

    @Override
    public void onItemDismiss(int position) {
        if (mValues != null) {
            mValues.remove(position);
        } else {
            mCursor.moveToPosition(position);
            int id = mCursor.getInt(0);
            MoneyEntity mAccount = new MoneyEntity();
            mAccount.setId(id);
            DB_r.delete(mListener, mAccount);
            mCursor = DB_r.getAll(mListener);
        }
        notifyItemRemoved(position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCategory;
        public final TextView mItem;
        public final TextView mPrice;
        public MoneyEntity mAccount;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCategory = (TextView) view.findViewById(R.id.categoryLabel);
            mItem = (TextView) view.findViewById(R.id.subCategory);
            mPrice = (TextView) view.findViewById(R.id.itemCost);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mPrice.getText() + "'";
//        }
        @Override
        public String toString() {
            return "mPrice = '" + mPrice.getText() + "'";
        }
    }

    private View.OnClickListener mItemAction = new View.OnClickListener() {

        boolean qq = true;
        @Override
        public void onClick(View view) {
            Log.d(TAG, view.toString());
            if (qq) {
                view.animate().rotation(360);
                qq = false;
            }
            else {
                view.animate().rotation(0);
                qq = true;
            }
        }
    };
}
