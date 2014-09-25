package com.tjm.crushr;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by cymak on 9/24/14.
 */
public class crushrWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new crushrRemoteViewFactory(this.getApplicationContext(), intent));
    }

}
