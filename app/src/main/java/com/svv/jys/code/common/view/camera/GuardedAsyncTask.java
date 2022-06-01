package com.svv.jys.code.common.view.camera;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by js on 2018/5/23.
 */

public abstract class GuardedAsyncTask <Params, Progress>
        extends AsyncTask<Params, Progress, Void> {

    private final Context mReactContext;

    protected GuardedAsyncTask(Context reactContext) {
        mReactContext = reactContext;
    }

    @Override
    protected final Void doInBackground(Params... params) {
        try {
            doInBackgroundGuarded(params);
        } catch (RuntimeException e) {
        }
        return null;
    }

    protected abstract void doInBackgroundGuarded(Params... params);
}
