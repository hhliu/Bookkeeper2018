package cycu.nclab.moocs.bookkeeper2018.recyclerview;

public interface ItemTouchHelperListener {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}