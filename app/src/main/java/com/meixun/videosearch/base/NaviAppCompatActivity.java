package com.meixun.videosearch.base;
/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trello.navi2.Event;
import com.trello.navi2.Listener;
import com.trello.navi2.NaviComponent;
import com.trello.navi2.internal.NaviEmitter;

/**
 * 参照Rxlifecycle2中NaviAppCompatActivity(com.trello.navi2.component.support)的写法,
 * 只是去除了其中带有PersistableBundle类的参数，PersistableBundle android5.0以后加入,
 * 用此参数会导致eventbus报错
 * http://greenrobot.org/eventbus/documentation/faq/
 */
public abstract class NaviAppCompatActivity extends AppCompatActivity implements NaviComponent {

    private final NaviEmitter base = NaviEmitter.createActivityEmitter();

    @Override
    public final boolean handlesEvents(Event... events) {
        return base.handlesEvents(events);
    }

    @Override
    public final <T> void addListener(@NonNull Event<T> event, @NonNull Listener<T> listener) {
        base.addListener(event, listener);
    }

    @Override
    public final <T> void removeListener(@NonNull Listener<T> listener) {
        base.removeListener(listener);
    }

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base.onCreate(savedInstanceState);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        base.onStart();
    }

    @Override
    @CallSuper
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        base.onPostCreate(savedInstanceState);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        base.onResume();
    }

    @Override
    @CallSuper
    protected void onPause() {
        base.onPause();
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        base.onStop();
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        base.onDestroy();
        super.onDestroy();
    }

    @Override
    @CallSuper
    protected void onRestart() {
        super.onRestart();
        base.onRestart();
    }

    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        base.onSaveInstanceState(outState);
    }

    @Override
    @CallSuper
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        base.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    @CallSuper
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        base.onNewIntent(intent);
    }

    @Override
    @CallSuper
    public void onBackPressed() {
        super.onBackPressed();
        base.onBackPressed();
    }

    @Override
    @CallSuper
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        base.onAttachedToWindow();
    }

    @Override
    @CallSuper
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        base.onDetachedFromWindow();
    }

    @Override
    @CallSuper
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        base.onConfigurationChanged(newConfig);
    }

    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        base.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    @CallSuper
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        base.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
