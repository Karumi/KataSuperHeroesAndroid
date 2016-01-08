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

package com.github.katasuperheroes.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.github.katasuperheroes.model.SuperHero;
import com.github.katasuperheroes.model.SuperHeroesRepository;
import com.github.katasuperheroes.ui.presenter.SuperHeroDetailPresenter;
import com.github.katasuperheroes.usecase.GetSuperHeroByName;
import github.com.katasuperheroes.R;

public class SuperHeroDetailActivity extends AppCompatActivity implements SuperHeroDetailPresenter.View {

  private static final String SUPER_HERO_NAME_KEY = "super_hero_name_key";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.super_hero_detail_activity);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    GetSuperHeroByName getSuperHeroByName = new GetSuperHeroByName(new SuperHeroesRepository());
    SuperHeroDetailPresenter superHeroDetailPresenter =
        new SuperHeroDetailPresenter(getSuperHeroByName);
    superHeroDetailPresenter.setView(this);
    superHeroDetailPresenter.initialize();
  }

  @Override public void showSuperHero(SuperHero superHero) {

  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }

  public static void open(Context context, String superHeroName) {
    Intent intent = new Intent(context, SuperHeroDetailActivity.class);
    intent.putExtra(SUPER_HERO_NAME_KEY, superHeroName);
    context.startActivity(intent);
  }
}
