package cycu.nclab.moocs.bookkeeper2018;
/*
 * An interface implemented typically by an activity
 * so that a dialog can report back
 * on what happened.
 */
public interface OnDialogDoneListener {
  public void onDialogDone(String tag, int choice, CharSequence message);
}
