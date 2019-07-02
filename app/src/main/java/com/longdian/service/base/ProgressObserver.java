package com.longdian.service.base;

import android.content.Context;
import android.widget.Toast;
import com.longdian.service.HoolaiException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/3/15.
 */

public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {

    private Context mContext;
    private ObserverOnNextListener<T> mObservableOnNextListener;
    private ObserverOnNextAndErrorListener<T> mObserverOnNextAndErrorListener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Disposable disposable;

    public ProgressObserver(Context context, ObserverOnNextListener<T> observableOnNextListener) {
        this(context, observableOnNextListener, true);
    }

    public ProgressObserver(Context context, ObserverOnNextListener<T> observableOnNextListener, boolean showProgressDialog) {
        this.mContext = context;
        this.mObservableOnNextListener = observableOnNextListener;
        if (showProgressDialog) {
            mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
        } else {
            mProgressDialogHandler = null;
        }
    }

    public ProgressObserver(Context context, ObserverOnNextAndErrorListener<T> observerOnNextAndErrorListener) {
        this(context, observerOnNextAndErrorListener, true);
    }

    public ProgressObserver(Context context, ObserverOnNextAndErrorListener<T> observerOnNextAndErrorListener, boolean showProgressDialog) {
        this.mContext = context;
        this.mObserverOnNextAndErrorListener = observerOnNextAndErrorListener;
        if (showProgressDialog) {
            mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
        } else {
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        if (mObservableOnNextListener != null) {
            mObservableOnNextListener.onNext(t);
        }
        if (mObserverOnNextAndErrorListener != null) {
            mObserverOnNextAndErrorListener.onNext(t);
        }
    }

    @Override
    public void onError(Throwable t) {
        dismissProgressDialog();
        if (mObserverOnNextAndErrorListener != null) {
            if (t instanceof HoolaiException) {
                mObserverOnNextAndErrorListener.onError((HoolaiException) t);
            } else {
                mObserverOnNextAndErrorListener.onError(new HoolaiException(HoolaiException.NETWORK_EXCEPTION, t));
            }
        } else {
//            Toast.makeText(mContext, "error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(mContext, "error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }
}
