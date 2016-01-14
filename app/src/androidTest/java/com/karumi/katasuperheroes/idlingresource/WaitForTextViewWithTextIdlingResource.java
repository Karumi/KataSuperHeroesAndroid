/*
 * Copyright (C) 2015 Karumi.
 *
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

package com.karumi.katasuperheroes.idlingresource;

import android.app.Activity;
import android.support.test.espresso.IdlingResource;
import android.widget.TextView;

public class WaitForTextViewWithTextIdlingResource implements IdlingResource {

  private final Activity activity;
  private final int textViewId;
  private final String expectedText;

  public WaitForTextViewWithTextIdlingResource(Activity activity, int viewId, String expectedText) {
    this.activity = activity;
    this.textViewId = viewId;
    this.expectedText = expectedText;
  }

  @Override public String getName() {
    return "WaitForTextViewWithTextIdlingResource";
  }

  @Override public boolean isIdleNow() {
    TextView textView = (TextView) activity.findViewById(textViewId);
    CharSequence text = textView.getText();
    return expectedText.equals(text);
  }

  @Override public void registerIdleTransitionCallback(ResourceCallback callback) {

  }
}
